package kr.or.ddit.pmsproject.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.pmsproject.dao.IProjectBoardDAO;
import kr.or.ddit.pmsproject.dao.IProjectWorkReplyDAO;
import kr.or.ddit.pmsproject.dao.IProjectboardAttatchDAO;
import kr.or.ddit.vo.PMSAttVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwBoardVO;

@Service
public class ProjectBoardServiceImpl implements IProjectBoardService {
	
	@Inject
	IProjectBoardDAO pbDAO;
	
	// 첨부파일 처리를 위한 DAO 추가
	@Inject
	IProjectboardAttatchDAO pbAttDAO;
	
	@Value("#{appInfo['projectBoardAttatchPath']}")
	String saveFolderUrl;
	
	// 첨부파일의 2진 데이터를 저장하기 위한 경로 설정
	File saveFolder;
	
	@Inject
	WebApplicationContext context;	// context Path를 가져오기 위함
	ServletContext application;
	
	@PostConstruct
	private void init() {
		application = context.getServletContext();
		String path = application.getRealPath(saveFolderUrl); 
		saveFolder = new File(path);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs(); 
		}
	}

	@Override
	public List<PwBoardVO> readProjcetBoardList(PagingVO<PwBoardVO> pagingVO) {
		return pbDAO.selectProjcetBoardList(pagingVO);
	}

	@Override
	public int readProjcetBoardCount(PagingVO<PwBoardVO> pagingVO) {
		return pbDAO.selectProjcetBoardCount(pagingVO);
	}

	@Override
	public PwBoardVO readProjcetBoard(int stu_post_cd) {
		PwBoardVO pbVO = pbDAO.selectProjcetBoard(stu_post_cd); 
		if(pbDAO==null) {
			throw new CommonException(stu_post_cd+"에 해당하는 글이 없음.");
		}
		return pbVO;
	}
	
	// 작업의 첨부파일 업로드를 위해 만든 메서드
	private void processProjectWorkAttatchList(PwBoardVO pbList) {
		// 핸들러 어댑터가 파싱해준 클라이언트의 첨부파일 리스트 불러오기
		List<PMSAttVO> attatchList = pbList.getAttatchList();
		if(attatchList!=null && attatchList.size()>0) {	// 첨부파일이 있을 경우
//				if(1==1) throw new RuntimeException("강제 발생 예외");
			// 첨부파일 업로드(DB)
			pbAttDAO.insertProjectBoardAttatch(pbList);
			try {
				// 2진 데이터 생성. 파일처리 DB보다 뒤에 해주는 이유는 DB는 커밋이라는 저장방식이 있기 때문.
				for(PMSAttVO attatch : attatchList) {	
					// 지정된 세이브 폴더로 첨부파일 저장
					attatch.saveFile(saveFolder);
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Transactional
	@Override
	public ServiceResult createProjcetBoard(PwBoardVO pbVO) {
		int rowcnt = pbDAO.insertProjcetBoard(pbVO);
		ServiceResult result = ServiceResult.FAIL;
		if(rowcnt>0) {
			// 첨부파일 추가 부분
			processProjectWorkAttatchList(pbVO);
			result = ServiceResult.OK;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult modifyProjcetBoard(PwBoardVO pbVO) {
		PwBoardVO savedProjectWork = readProjcetBoard(pbVO.getStu_post_cd());
		ServiceResult result = null;
		result = ServiceResult.FAIL;
		if(savedProjectWork!=null) {
			int rowcnt = pbDAO.updateProjcetBoard(pbVO);
			
			// 지우려고 하는 파일의 정보
			if(rowcnt>0) {
				processProjectWorkAttatchList(pbVO);
				deleteAttachList(pbVO, savedProjectWork);
				
				result = ServiceResult.OK;
			}
		}
		return result;
	}
	
	// 첨부파일 삭제
	private void deleteAttachList(PwBoardVO pbVO, PwBoardVO PbBoardVO) {
		// 지우려고 하는 파일의 정보
		int[] delAttNos = pbVO.getDelAttNos();
		if(delAttNos!=null && delAttNos.length>0) {
			// 바이너리 어레이.. 를 사용하기 위해
			Arrays.sort(delAttNos);
			// 지우려고 하는 경로 스트링 배열, 지우려는 데이터 크기만큼
			String[] filePaths = new String[delAttNos.length];
			// 이미 DB에 있는 파일들
			List<PMSAttVO> attList = PbBoardVO.getAttatchList();
			int idx = 0;
			for(PMSAttVO delAttatch : attList) {
				// delAttNos 에 포함이 되어 있다면, 삭제
				if(Arrays.binarySearch(delAttNos, delAttatch.getSt_cd())>=0) {
					// 찾았으면 filePaths 에 경로를 증가시켜줘야 한다.
					filePaths[idx++]=delAttatch.getTemp_nm();
				}
			}
			// 메타 삭제
			// 이렇게 지우면 메타데이터만 지워진다.
			pbAttDAO.deleteProjectBoardAttatch(pbVO);
			// 2진 삭제
			// 트랜잭션이 롤백되는 건 DB. 파일은 되돌릴 수 없다. DB 다음에만 해주자.
			for(String filePath : filePaths) {
				File delFile = new File(saveFolder, filePath);
				delFile.delete();
			}
		}
	}

	@Transactional
	@Override
	public ServiceResult removeProjcetBoard(PwBoardVO pbVO) {
		PwBoardVO savedProjectBoard = pbDAO.selectProjcetBoard(pbVO.getStu_post_cd());
		if(savedProjectBoard==null) return ServiceResult.NOTEXIST;
		ServiceResult result = null;
		result = ServiceResult.FAIL;
		
		pbDAO.deleteProjcetBoard(pbVO);
		int rowcnt = pbVO.getRowcnt();
		
		if(rowcnt>0) {	// 1개 이상 삭제 됐을 경우
			List<PMSAttVO> attList = savedProjectBoard.getAttatchList();
			if(attList!=null) {
				for(PMSAttVO delAttatch : attList) {
					File delFile = new File(saveFolder, delAttatch.getTemp_nm());
					delFile.delete();
				}
			}
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public void incrementHit(int stu_post_cd) {
		pbDAO.incrementHit(stu_post_cd);
	}

	@Override
	public int readProjectMember(PwBoardVO pbVO) {
		return pbDAO.selectProjectMember(pbVO);
	}

	@Override
	public PMSAttVO selectBoardAttDownload(int st_cd) {
		return pbAttDAO.selectProjectBoardAttatch(st_cd);
	}

}
