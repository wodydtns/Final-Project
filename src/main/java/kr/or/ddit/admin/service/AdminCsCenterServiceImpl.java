package kr.or.ddit.admin.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.admin.dao.IAdminCenterAttachDAO;
import kr.or.ddit.admin.dao.IAdminCsCenterDAO;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.vo.CsCenterAttVO;
import kr.or.ddit.vo.CsCenterVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 작성자명
 * @since 2020. 3. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2020. 3. 13.      작성자명   박재욱    최초작성   박재욱
 * 2020. 3. 18.              박재욱               페이징 처리한 고객센터, FAQ 게시글
 * 2020. 3. 19.      작성자명   박재욱              공지사항, FAQ 상세 글 조회
 * 2020. 3. 20.      작성자명   박재욱              공지사항 글 작성 로직 중
 * 2020. 3. 23.      작성자명   박재욱              첨부파일 추가
 * 2020. 3. 24.      작성자명   박재욱              조회 수 추가
 * Copyright (c) 2020 by DDIT All right reserved
 *      </pre>
 */
@Service
public class AdminCsCenterServiceImpl implements IAdminCsCenterService {

	@Inject
	IAdminCsCenterDAO dao;

	@Inject
	IAdminCenterAttachDAO attachDAO;

	@Value("#{appInfo['attatchPath']}")
	File saveFolder;

	private void processAttachList(CsCenterVO center) {
		List<CsCenterAttVO> attachList = center.getAttachList();
		if (attachList != null && attachList.size() > 0) {
			attachDAO.insertAttach(center);
			try {
				for (CsCenterAttVO attatch : attachList) {
					attatch.saveFile(saveFolder);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public List<CsCenterVO> readCsCenterBoard(PagingVO pagingVO) {
		return dao.selectCsCenterBoardList(pagingVO);
	}

	@Override
	public int readSelectCSCenterBoardTotal(PagingVO pagingVO) {
		return dao.selectCSCenterBoardTotal(pagingVO);
	}

	@Override
	public CsCenterVO readSelectCSCenterBoard(int cs_cd) {
		CsCenterVO CSCenterBoard = dao.selectCsCenterBoard(cs_cd);
		if (CSCenterBoard == null) {
			// 게시글이 없으면
			throw new UserNotFoundException();
		}
		return CSCenterBoard;
	}

	@Override
	public List<CsCenterVO> readSelectHeaderList() {
		return dao.selectHeaderList();
	}

	@Transactional
	@Override
	public int createCsCenterBoard(CsCenterVO csCenterVO) {
		int cnt = 0;
		int rowcnt = dao.writeCsCenterBoard(csCenterVO);
		if (rowcnt > 0) {
			processAttachList(csCenterVO);
			cnt = 1;
		} else {
			throw new RuntimeException();
		}
		return cnt;
	}

	@Transactional
	@Override
	public int modifyUpdateCsCenterBoard(CsCenterVO csCenterVO) {
		int cnt = 0;
		int rowcnt = dao.updateCsCenterBoard(csCenterVO);
		System.out.println(cnt + " 게시글 INSERT");
		if (rowcnt > 0) {
			processAttachList(csCenterVO);
			deleteAttachList(csCenterVO);
			cnt = 1;
			System.out.println("파일 insert");
		} else {
			System.out.println("insert 실패");
		}
		return cnt;
	}

	@Override
	public CsCenterAttVO attDownload(int st_cd) {
		CsCenterAttVO attach = attachDAO.selectAttach(st_cd);
		if (attach == null) {
			throw new RuntimeException(st_cd + "에 해당하는 파일 없음");
		}
		return attach;
	}

	@Override
	public int incrementHit(int cs_cd) {
		return dao.incrementHit(cs_cd);
	}
	// 파일 삭제
	private void deleteAttachList(CsCenterVO center) {
		int[] delAttNos = center.getDelStNos();
		if(delAttNos!=null && delAttNos.length>0) {
			Arrays.sort(delAttNos);
			String[] filePaths = new String[delAttNos.length];
			List<CsCenterAttVO> attList = center.getAttachList();
			int idx = 0;
			for(CsCenterAttVO delAttatch : attList) {
				if(Arrays.binarySearch(delAttNos, delAttatch.getSt_cd())>=0) {
					filePaths[idx++]=delAttatch.getTemp_nm();
				}
			}
			// 메타 삭제
			attachDAO.deleteAttach(center);
			// 2진 삭제
			for(String filePath : filePaths) {
				File delFile = new File(saveFolder, filePath);
				delFile.delete();
			}
		}
	}
	@Transactional
	@Override
	public int removeCsCenterBoard(CsCenterVO center) {
		dao.deleteCsCenterBoard(center);
		int rowcnt = center.getRowcnt();
		if(rowcnt >0) {
			List<CsCenterAttVO> attList = center.getAttachList();
			if(attList!=null) {
				for(CsCenterAttVO delAttatch : attList) {
					File delFile = new File(saveFolder, delAttatch.getTemp_nm());
					delFile.delete();
				}
			}
		}
		 
		return rowcnt;
	}

}
