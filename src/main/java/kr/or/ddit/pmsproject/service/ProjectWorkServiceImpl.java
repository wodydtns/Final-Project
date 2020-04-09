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
import kr.or.ddit.pmsproject.dao.IProjectAttatchDAO;
import kr.or.ddit.pmsproject.dao.IProjectWorkDAO;
import kr.or.ddit.vo.PMSAttVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwCalendarVO;
import kr.or.ddit.vo.PwListVO;

@Service
public class ProjectWorkServiceImpl implements IProjectWorkService {
	
	@Inject
	IProjectWorkDAO pwDAO;
	
	// 첨부파일 처리를 위한 DAO 추가
	@Inject
	IProjectAttatchDAO pwAttDAO;
	
	// 첨부파일의 2진 데이터를 저장하기 위한 경로 설정
	@Value("#{appInfo['projectWorkAttatchPath']}")
	File saveFolder;
	@Value("#{appInfo['projectWorkAttatchPath']}")
	String saveFolderUrl;
	
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
	
	// 작업의 첨부파일 업로드를 위해 만든 메서드
	private void processProjectWorkAttatchList(PwListVO pwList) {
		// 핸들러 어댑터가 파싱해준 클라이언트의 첨부파일 리스트 불러오기
		List<PMSAttVO> attatchList = pwList.getAttatchList();
		if(attatchList!=null && attatchList.size()>0) {	// 첨부파일이 있을 경우
//			if(1==1) throw new RuntimeException("강제 발생 예외");
			// 첨부파일 업로드(DB)
			pwAttDAO.insertProjectWorkAttatch(pwList);
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

	@Transactional	// 트랜잭션 관리를 위함.
	@Override
	public ServiceResult createProjectWork(PwListVO proj) {
		int rowcnt = pwDAO.insertProjectWork(proj);
		ServiceResult result = ServiceResult.FAIL;
		if(rowcnt>0) {
			// 첨부파일 추가 부분
			processProjectWorkAttatchList(proj);
			result = ServiceResult.OK;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult modifyProjectWork(PwListVO pwList) {
		PwListVO savedProjectWork = readProjectWorkAtt(pwList.getPw_cd());
		ServiceResult result = null;
		result = ServiceResult.FAIL;
		if(savedProjectWork!=null) {
			if(!savedProjectWork.getMem_nick().equals(pwList.getMem_nick())) {
				pwDAO.updateProjectWorkAgreeNo(pwList.getPw_cd());
			}
			int rowcnt = pwDAO.updateProjectWork(pwList);
			
			// 지우려고 하는 파일의 정보
			if(rowcnt>0) {
				processProjectWorkAttatchList(pwList);
				deleteAttachList(pwList, savedProjectWork);
				
				result = ServiceResult.OK;
			}
		}
		return result;
	}
	

	// 첨부파일 삭제
	private void deleteAttachList(PwListVO pwList, PwListVO pwListVO) {
		// 지우려고 하는 파일의 정보
		int[] delAttNos = pwList.getDelAttNos();
		if(delAttNos!=null && delAttNos.length>0) {
			// 바이너리 어레이.. 를 사용하기 위해
			Arrays.sort(delAttNos);
			// 지우려고 하는 경로 스트링 배열, 지우려는 데이터 크기만큼
			String[] filePaths = new String[delAttNos.length];
			// 이미 DB에 있는 파일들
			List<PMSAttVO> attList = pwListVO.getAttatchList();
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
			pwAttDAO.deleteProjectWorkAttatch(pwList);
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
	public ServiceResult removeProjectWork(PwListVO pwList) {
		PwListVO savedProjectWork = pwDAO.selectProjectWorkAtt(pwList.getPw_cd());
		if(savedProjectWork==null) return ServiceResult.NOTEXIST;
		ServiceResult result = null;
		result = ServiceResult.FAIL;
		
		pwDAO.deleteProjectWork(pwList);
		int rowcnt = pwList.getRowcnt();
		
		if(rowcnt>0) {	// 1개 이상 삭제 됐을 경우
			List<PMSAttVO> attList = savedProjectWork.getAttatchList();
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
	public List<PwListVO> readProjectWorkList(PagingVO<PwListVO> pagingVO) {
		return pwDAO.selectProjectWorkList(pagingVO);
	}

	@Override
	public int readProjectWorkCount(PagingVO<PwListVO> pagingVO) {
		return pwDAO.selectProjectWorkCount(pagingVO);
	}

	@Override
	public List<PwListVO> readProjectWorkAllList(PagingVO<PwListVO> pagingVO) {
		return pwDAO.selectProjectWorkAllList(pagingVO);
	}

	@Override
	public int readProjectWorkAllCount(PagingVO<PwListVO> pagingVO) {
		return pwDAO.selectProjectWorkAllCount(pagingVO);
	}

	@Override
	public PwListVO readProjectWork(int pw_cd) {
		return pwDAO.selectProjectWork(pw_cd);
	}

	@Override
	public List<PwListVO> readNewProjectWorkList(String mem_email) {
		return pwDAO.selectNewProjectWorkList(mem_email);
	}

	@Override
	public int modifyNewProjectWork(String peed_cd) {
		return 0;
	}

	@Override
	public PMSAttVO projectWorkAttDownload(int st_cd) {
		PMSAttVO attatch = pwAttDAO.selectProjectWorkAttatch(st_cd);
		if(attatch==null) {
			throw new CommonException(st_cd+" 해당 파일 없음.");
		}
		return attatch;
	}

	@Override
	public PwListVO readProjectWorkAtt(int pw_cd) {
		return pwDAO.selectProjectWorkAtt(pw_cd);
	}

	@Override
	public List<PwCalendarVO> readProjectWorkingCalender(PwCalendarVO pwCalendarVO) {
		
		return pwDAO.ProjectWorkingList(pwCalendarVO);
	}

}
