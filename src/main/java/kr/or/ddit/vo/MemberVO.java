package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
 * 2020. 3. 11.      작성자명 김혜정      최초작성 김혜정
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Data
public class MemberVO implements Serializable{
	
	
	
	public MemberVO(String mem_email, String mem_pass) {
		super();
		this.mem_email = mem_email;
		this.mem_pass = mem_pass;
	}

	public MemberVO() {
		super();
	}

	@NotBlank(groups = InsertHint.class)
	private String mem_email;

	@Length(min=8, max=20)
	private String mem_pass;
	
	
	@Length(min=5,max=10)
	private String mem_nick;

	@Length(min=11,max=13)
	private String mem_hp;
	
	private String reg_date;
	
	private String yn_code;
	
	private String mem_salt; //비밀번호 암호화를 위한 salt값
	
	private int cp_cd;

	// has many (1:N)
	private List<PIVO> piList;
	
	// has many (1:N)
	private List<ClassVO> classList;
	
	private String cl_cd;
		
}
