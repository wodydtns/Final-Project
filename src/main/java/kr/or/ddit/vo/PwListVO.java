package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      작성자명 박재욱      최초작성 박재욱
 * 2020. 3. 16.		 최효은			VO 조인 프로퍼티 추가
 * 2020. 3. 28.		 최효은			첨부파일 작업을 위한 PMS_BOARD_ATT VO 추가
 * 2020. 3. 31. 	 최효은			ProjCommentVO 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="pw_cd")
@ToString(exclude= {"pw_file","attatchList"})
@NoArgsConstructor
@AllArgsConstructor
public class PwListVO<T> implements Serializable{
	private Integer pw_cd;
	private Integer proj_cd;
	private String prog_cd;
	private String prior_cd;
	private Integer pm_cd;
	private String pw_nm;
	private String pw_end;
	private String pw_start;
	private Integer pw_level;
	private Integer pw_ext_time;
	private String pw_exp;
	private String yn_code;
	private String pw_modi_date;
	private String pw_img;
	
	private String mem_email;
	
	private int rnum;
	private String proj_nm;		// 프로젝트명
	private String prog_nm;		// 진행상태명
	private String prior_nm;	// 우선순위명
	private String mem_nick;	// 작업 담당자 닉네임
	
	private ProjectSearchVO psVO;	// 검색을 위한 VO 관계 형성
	
	private SearchVO searchVO; 	// 일반 검색
	private T searchDetail;		// 검색을 위한 VO 관계 형성
	
	// PMS 첨부파일과 1:N 관계 형성
	private List<PMSAttVO> attatchList;
	private List<ProjCommentVO> projCommentVO;	// 댓글
	
	private int startAttNo;
	
	// 삭제할 첨부파일 리스트를 위해 사용
	private int[] delAttNos;
	// 프로시저의 아웃바운드 변수를 위한 프로퍼티
	private int rowcnt;
	
	// MultipartFile 파일이 들어왔을 경우 첨부파일 리스트에 넣어줄 수 있게끔 setter 호출
	private List<MultipartFile> pw_file;
	public void setPw_file(List<MultipartFile> pw_file) {
		this.pw_file = pw_file;
		if(pw_file!=null && pw_file.size()>0) {
			attatchList = new ArrayList<>();
			for(MultipartFile tmp : pw_file) {
				if(StringUtils.isBlank(tmp.getOriginalFilename())) {
					continue;
				}
				PMSAttVO attatchVO = new PMSAttVO(tmp);
				attatchList.add(attatchVO);
			}
		}
	}
	
	
}















