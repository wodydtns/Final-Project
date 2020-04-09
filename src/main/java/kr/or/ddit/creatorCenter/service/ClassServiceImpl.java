package kr.or.ddit.creatorCenter.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.creatorCenter.dao.IClassAttatchDAO;
import kr.or.ddit.creatorCenter.dao.IClassDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CreatorVO2;
import kr.or.ddit.vo.CurriculumVO;
import kr.or.ddit.vo.PaymentVO;
import kr.or.ddit.vo.PiAttVO;

@Service
public class ClassServiceImpl implements IClassService {

	@Value("#{appInfo.classVideoPath}")
	String saveFolderUrl;
	
	@Inject
	IClassDAO classDAO;
	
	@Inject
	IClassAttatchDAO attatchDAO;
	
	File saveFolder; 
	
	@Inject
	WebApplicationContext context; //얘주입후에 서블릿컨텍스트를 잡아야한다.
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
	
	
	
	//첨부파일처리
	private void processAttatchList(ClassVO cl) {
		List<CurriculumVO> curriList = new ArrayList<>();
		List<List<CurriculumVO>> currigroups = cl.getGroups();
		boolean check = false;
		int max_seq = 0;
			try {
				for(List<CurriculumVO> currigroup : currigroups) {
					for(CurriculumVO curri : currigroup) {
						curri.setCl_cd(cl.getCl_cd());//클래스코드 먼저 넣어주고
						if(curri.getCurri_cd()!=null) {//상위커리큘럼제목이라면
							if(check) {//새로운 상위커리큘럼이 시작할 때 그 전의 커리큘럼 insert하기
								max_seq = attatchDAO.selectCurri_SEQ();
								cl.setStartAttNo(max_seq);
								cl.setCurriculumList(curriList);//vo로 넘겨주기위해
								attatchDAO.insertCurriculum(cl);//하위값 insertAll
							}
							curriList.clear();//재활용위해 clear
							max_seq = attatchDAO.selectCurri_SEQ()+1; //다음 시퀀스 값 구함
							//max_seq치환
							curri.setCurri_cd(max_seq);//나의 커리큘럼번호 set
							createTitle(curri);//상위커리큘럼제목 insert
						}else {//상위커리큘럼에 대한 하위커리큘럼이 시작될 때
							check = true;
							curri.setCurri_cd2(max_seq);//참조하고있는 커리큘럼번호 set
							curriList.add(curri);
							if(curri.getClass_video()!=null) {//비디오 첨부파일이 있다면
								curri.genClass_video(curri.getClass_video()); //메타데이터 생성
								curri.saveFile(saveFolder);
							}
						}
						
					}
					
				}
				//마지막커리큘럼은 for문 나와서 insert
				max_seq = attatchDAO.selectCurri_SEQ();
				cl.setStartAttNo(max_seq+1);
				cl.setCurriculumList(curriList);//vo로 넘겨주기위해
				attatchDAO.insertCurriculum(cl);//하위값 insertAll
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	
	
	@Transactional
	private void createTitle(CurriculumVO curri){
		attatchDAO.insertCurriculumTitle(curri);//상위값 insert
	}

	/**
	 * 첨부파일 삭제
	 * @param pi 삭제할 파일의 번호를 가진 BoardVO
	 * @param sqlSession
	 * @param savedBoard 2진 데이터 삭제를 위해 저장 파일명을 가진 BoardVO
	 */
	private void deleteAttachList(ClassVO cl, ClassVO savedCl) {
		int[] delAttNos = cl.getDelAttNos();
		if(delAttNos!=null && delAttNos.length>0) {
			Arrays.sort(delAttNos);
			String[] filePaths = new String[delAttNos.length];
			List<CurriculumVO> attatchList = savedCl.getCurriculumList();
			int idx = 0;
			for(CurriculumVO delAttatch : attatchList) {
				if(Arrays.binarySearch(delAttNos, delAttatch.getCurri_cd())>=0) {
					filePaths[idx++]=delAttatch.getCurri_temp_nm();
				}
			}
			// 메타 삭제
			attatchDAO.deleteCurriculum(cl);
			// 2진 삭제
			for(String filePath : filePaths) {
				File delFile = new File(saveFolder, filePath);
				delFile.delete();
			}
		}
	}

	
	private ClassVO authenticate(ClassVO cl) {
		ClassVO savedClass = readMyClass(cl.getCl_cd());
		return savedClass;
	}

	
	@Transactional
	@Override
	public ServiceResult createClass(ClassVO cl) {
		int cnt = classDAO.insertClass(cl);
		ServiceResult result = ServiceResult.FAIL;
		if(cnt > 0) {
			processAttatchList(cl);
			result = ServiceResult.OK;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult modifyClass(ClassVO cl) {
		ClassVO savedCl = authenticate(cl);
		ServiceResult result = ServiceResult.FAIL;
		int cnt = classDAO.updateClass(cl);
		if(cnt>0) {
			
			deleteAttachList(cl, savedCl);
			processAttatchList(cl);
			
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public List<ClassVO> readMyClassList(String mem_email) {
		List<ClassVO> classList = classDAO.selectMyClassList(mem_email);
		if(classList==null) {
			throw new CommonException("진행중인 클래스가 없음"); //예외의 형태로 호출자에게 넘김
		}
		return classList;
	}


	@Override
	public ClassVO readMyClass(int cl_cd) {
		return classDAO.selectMyClass(cl_cd);
	}
	
	@Override
	public List<CurriculumVO> readMyCurri(int cl_cd) {
		return classDAO.selectMyCurri(cl_cd);
	}
	@Override
	public CreatorVO readCreator(int cl_cd) {
		 CreatorVO creator = classDAO.selectCreator(cl_cd);
		return creator;
	}
	
	@Transactional
	@Override
	public ServiceResult createCreator(CreatorVO2 crea) {
		ServiceResult result = null;
		int cnt = classDAO.insertCreator(crea);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}



	@Override
	public ServiceResult readPaidCheck(PaymentVO pay) {
		ServiceResult result = null;
		int cnt = classDAO.selectPaidCheck(pay);
		if(cnt > 0) {
			result = ServiceResult.EXIST;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}



	@Override
	public CreatorVO creatorCheck(String mem_email) {
		CreatorVO creator = classDAO.creatorCheck(mem_email);
		return creator;
	}



	
}
