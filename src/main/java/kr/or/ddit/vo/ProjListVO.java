package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.hint.InsertHint;
import lombok.Data;
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
 * 2020. 3. 16. 	 최효은			VO 관계 프로퍼티 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Alias("projListVO")
@Data
public class ProjListVO implements Serializable{
	private Integer proj_cd;
	@NotNull(groups=InsertHint.class)
	private String proj_leader;
	private String cate_cd;
	@NotNull(groups=InsertHint.class)
	private String proj_nm;
	private String proj_exp;
	@NotNull(groups=InsertHint.class)
	private String proj_start;
	@NotNull(groups=InsertHint.class)
	private String proj_end;
	
	private int rnum;			// 순번
	private String mem_email;	// 회원이메일
	private String mem_nick;	// 회원닉네임
	
	private String cate_nm;		// 카테고리 이름
	
	private int prog_cnt;		// 진행 카운트
	private int comp_cnt;		// 완료 카운트
	private int pw_ext_time;	// 소요시간
	
	private PMListVO pmList;	// 프로젝트 회원 추가 VO 관계
	
//	private List<PMListVO> pmList;
	private List<MemberVO> memList;
}
