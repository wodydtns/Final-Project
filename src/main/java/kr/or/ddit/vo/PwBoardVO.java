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
 * @since 2020. 4. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 1.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Data	
@EqualsAndHashCode(of="stu_post_cd")
@ToString(exclude= {"pb_file","attatchList"})
@NoArgsConstructor
@AllArgsConstructor
public class PwBoardVO implements Serializable{
	
	private int rnum;				// 행 번호
	
	private Integer stu_post_cd;	// 게시글 번호
	private Integer proj_cd;		// 프로젝트 번호
	private Integer pm_cd;			// 회원 번호
	private Integer post_parent_cd;	// 상위 부모글
	private String pw_title;		// 게시글 제목
	private String pw_cont;			// 게시글 내용
	private String pw_date;			// 작성일자
	private Integer pw_hit;			// 조회수
	
	private String mem_email;		// 멤버 이메일
	private String mem_nick;		// 멤버 닉네임
	
	private boolean recommended;	// 조회수
	private int rowcnt;				// 프로시저 반환값
	
	// PMS 첨부파일과 1:N 관계 형성
	private List<PMSAttVO> attatchList;
	private List<ProjCommentVO> projCommentVO;	// 댓글
	
	private int startAttNo;
	
	// 삭제할 첨부파일 리스트를 위해 사용
	private int[] delAttNos;
	
	// MultipartFile 파일이 들어왔을 경우 첨부파일 리스트에 넣어줄 수 있게끔 setter 호출
	private List<MultipartFile> pb_file;
	public void setPb_file(List<MultipartFile> pb_file) {
		this.pb_file = pb_file;
		if(pb_file!=null && pb_file.size()>0) {
			attatchList = new ArrayList<>();
			for(MultipartFile tmp : pb_file) {
				if(StringUtils.isBlank(tmp.getOriginalFilename())) {
					continue;
				}
				PMSAttVO attatchVO = new PMSAttVO(tmp);
				attatchList.add(attatchVO);
			}
		}
	}
	
}