package kr.or.ddit.creatorCenter.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.creatorCenter.dao.IPIDAO;
import kr.or.ddit.creatorCenter.dao.IPiAttatchDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PIVO;
import kr.or.ddit.vo.PiAttVO;
@Service
public class PIServiceImpl implements IPIService {

	@Inject
	IPIDAO ccDAO;
	
	@Inject
	IPiAttatchDAO attatchDAO;
	
	@Value("#{appInfo.piImgPath}")
	String saveFolderUrl;
	
	//@Value("#{appInfo.piImgrealPath}")
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
	
	//커버이미지 처리
	private void processAttatchList(PIVO pi) {
		PiAttVO attatch = pi.getPi_att();
		if(attatch!=null) {
			attatchDAO.insertAttach(pi);
			try {
				attatch.saveFile(saveFolder);
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else {//커버이미지 수정안할때
			
		}
	}

	@Transactional
	@Override
	public ServiceResult createPI(PIVO pi) {
		int cnt = ccDAO.insertPI(pi);
		ServiceResult result = ServiceResult.FAIL;
		if(cnt > 0) {
			processAttatchList(pi);
			result = ServiceResult.OK;
		}
		return result;
	}


	@Override
	public List<PIVO> read_My_PI_wait_List(String mem_email) {
		List<PIVO> pi = ccDAO.select_My_PI_wait_List(mem_email);
		return pi;
	}
	
	@Override
	public List<PIVO> read_My_PI_ing_List(String mem_email) {
		List<PIVO> pi = ccDAO.select_My_PI_ing_List(mem_email);
			return pi;
	}

	@Override
	public List<PIVO> read_My_PI_end_List(String mem_email) {
		List<PIVO> pi = ccDAO.select_My_PI_end_List(mem_email);
			return pi;
	}

	@Override
	public PIVO read_My_pi(int pi_cd) {
		System.out.println("사전조사 번호 : " + pi_cd);
		PIVO pi = ccDAO.select_My_pi(pi_cd);
		return pi;
	}

	@Transactional
	@Override
	public ServiceResult modifyPI(PIVO pi) {
		PIVO savedPi = authenticate(pi);
		ServiceResult result = ServiceResult.FAIL;
		int cnt = ccDAO.updatePI(pi);
		if(cnt>0) {
			
			deleteAttach(pi, savedPi);
			processAttatchList(pi);
			
			result = ServiceResult.OK;
		}
		return result;
	}

	
	
	/**
	 * 첨부파일 삭제
	 * @param pi 삭제할 파일의 번호를 가진 BoardVO
	 * @param sqlSession
	 * @param savedBoard 2진 데이터 삭제를 위해 저장 파일명을 가진 BoardVO
	 */
	private void deleteAttach(PIVO pi, PIVO savedPi) {//첨부파일삭제 (수정용)
		if(pi.getPi_att()!=null && savedPi.getPi_att()!=null) {
			PiAttVO cover = savedPi.getPi_att();
			String filePath = cover.getTemp_nm();
			// 메타 삭제
			attatchDAO.deleteAttatch(savedPi.getPi_cd());
			// 2진 삭제
			File delFile = new File(saveFolder, filePath);
			delFile.delete();
		}
	}

	
	private PIVO authenticate(PIVO pi) {
		PIVO savedPi = read_My_pi(pi.getPi_cd());
		return savedPi;
	}
	
	
	@Transactional  // AOP
	@Override
	public ServiceResult removePi(PIVO pi) {//pi와 첨부파일 함께삭제
		PIVO savedPi = authenticate(pi);
		ServiceResult result = null;
		result = ServiceResult.FAIL;
		ccDAO.deletePI(pi);
		int rowcnt = pi.getRowcnt(); //프로시저로 인해 자동으로 set됨
		if(rowcnt>0) {
			PiAttVO cover = savedPi.getPi_att();
			String filePath = cover.getTemp_nm();
			File delFile = new File(saveFolder, filePath);
			delFile.delete();
			result = ServiceResult.OK;
		}
		return result;
	}


	@Transactional
	@Override
	public ServiceResult modifyClassYN(int pi_cd) {
		ServiceResult result = ServiceResult.FAIL;
		int cnt = ccDAO.updateClassYN(pi_cd);
		if(cnt>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult modifyPIStatus(Map<String, Object> pMap) {
		ServiceResult result = ServiceResult.FAIL;
		int cnt = ccDAO.updatePIstatus(pMap);
		if(cnt>0) {
			result = ServiceResult.OK;
		}
		return result;
	}


}
