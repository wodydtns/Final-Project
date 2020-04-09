package kr.or.ddit.hobby.service;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hobby.dao.IHobbyDetailAttatchDAO;
import kr.or.ddit.hobby.dao.IHobbyDetailDAO;
import kr.or.ddit.vo.ClassCommunityVO;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.CreatorVO;

@Service
public class HobbyDetailServiceImpl implements IHobbyDetailService {

	@Inject
	IHobbyDetailDAO hobbyDetaildao;
	
	@Inject
	IHobbyDetailAttatchDAO attatchDAO;
	
	WebApplicationContext context;
	ServletContext application;
	@Inject
	public void setContext(WebApplicationContext context) {
		this.context = context;
		application = context.getServletContext();
	}
	
	@Value("#{appInfo['replyImgPath']}") // 문자열이 파싱되어서 saveFolder에 들어감.
	String saveFolderUrl;
	
	File saveFolder;
	
	@PostConstruct
	public void init(){
		String realPath = application.getRealPath(saveFolderUrl);
		saveFolder = new File(realPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	

	@Override
	public CreatorVO selectCreator(int cl_cd) {
		return hobbyDetaildao.selectCreator(cl_cd);
	}
	@Override
	public List<CreatorVO> selectCreatorList(int cl_cd) {
		return hobbyDetaildao.selectCreatorList(cl_cd);
	}
	@Override
	public List<ClassCommunityVO> selectCommunityList(int cl_cd) {
		return hobbyDetaildao.selectCommunityList(cl_cd);
	}
	@Override
	public List<ClassCommunityVO> selectCommunityList2(int cl_cd) {
		return hobbyDetaildao.selectCommunityList2(cl_cd);
	}
	@Override
	public List<CommReplyVO> selectComunityReply(int cl_cd) {
		return hobbyDetaildao.selectComunityReply(cl_cd);
	}
	
	
	
		
		private void processAttatchList(CommReplyVO commReplyVO) {
//			List<hobbyDetailAttatchVO> attatchList = commReplyVO.getAttachList();
//			if(attatchList != null && attatchList.size() > 0) {
//				if(1==1) throw new RuntimeException("강제 발생 예외"); // 500 에러.
//				attatchDAO.insertAttatch(commReplyVO);
//				try {
//						for(hobbyDetailAttatchVO attatch : attatchList) {
//							attatch.saveFile(saveFolder);
//						}
//				}catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			}
			
			
			
		}
	
		
	@Transactional
	@Override
	public int createBoard(CommReplyVO commReplyVO) {
		int cnt = 0;
		int rowcnt = hobbyDetaildao.insertBoard(commReplyVO);
		if(rowcnt > 0) {
//			processAttatchList(commReplyVO);
			cnt = 1;
		}else {
			throw new RuntimeException();
		}
		return cnt;
	}
	
	
	
	//댓글 첨부파일 업로드를 위해서 사용
	private void processImage(CommReplyVO comm) {
		MultipartFile img = comm.getReply_img();
		if(img!=null) {
			try {
				comm.saveFile(saveFolder);
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else if(img==null) {
			
		}
	}
	
	@Transactional
	@Override
	public ServiceResult createReply(CommReplyVO commReply) {
		CommReplyVO comm = null;
		int rowcnt= 0;
		ServiceResult result = ServiceResult.FAIL;
		if(commReply.getReply_img()!=null &&
			StringUtils.isNotBlank(commReply.getReply_img().getOriginalFilename())) {
			comm = new CommReplyVO(commReply.getReply_img());
			comm.setCommu_cd(commReply.getCommu_cd());
			comm.setMem_email(commReply.getMem_email());
			comm.setComr_comm_cont(commReply.getComr_comm_cont());
			rowcnt = hobbyDetaildao.insertReply(comm);
			if(rowcnt > 0) {
				processImage(comm);
				result = ServiceResult.OK;
			}
		}else {
			rowcnt = hobbyDetaildao.insertReply2(commReply);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}
		}
		
		return result;
	}
	
	
	



	


	

}
