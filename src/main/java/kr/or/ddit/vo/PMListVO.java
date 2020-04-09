package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.hint.InsertHint;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      작성자명   박재욱   최초작성  박재욱
 * 2020. 3. 18.      최효은   		생성자 추가
 * 2020. 3. 24.      최효은   		프로젝트 회원 관리 관계 형성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Alias("pmListVO")
@Data
@NoArgsConstructor
public class PMListVO implements Serializable{
	
	// ProjectList에 관계로 추가할 수 있는 생성자 추가
	public PMListVO(String mem_email, String yn_code2, String yn_code) {
		super();
		this.mem_email = mem_email;
		this.yn_code2 = yn_code2;
		this.yn_code = yn_code;
	}
	
	private int rnum;			// 순번 추가
	
	private Integer pm_cd;		// 프로젝트 회원코드
	@NotNull(groups=InsertHint.class)
	private Integer proj_cd;	// 프로젝트 식별코드
	private String mem_email;	// 프로젝트 회원
	private String pm_date;		// 초대날짜
	private String yn_code2;	// 승낙여부
	private String yn_code;		// 탈퇴여부
	
	@NotNull(groups=InsertHint.class)
	private String[] mem_email_list;	// 프로젝트 멤버 다수 초대를 위함	
	private List<Integer> mem_list;
	
	private ProjectSearchVO psVO;	// 검색을 위한 VO 관계 형성
	
	private String mem_nick;	// 멤버관리용 회원 닉네임
	private String proj_leader;	// 프로젝트 리더인지 구분용
	
	private int rowcnt;		// 프로시저 확인용
	
//	private MemberVO member;		// mem_nick
//	private ProjListVO projList;	// proj_leader
}
