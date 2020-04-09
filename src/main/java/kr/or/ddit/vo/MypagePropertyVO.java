package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.hint.InsertHint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author 작성자명
 * @since 2020. 3. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 27.      작성자명   박재욱              최초작성, 마이페이지 용   
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of="mem_email")
@AllArgsConstructor
@NoArgsConstructor
public class MypagePropertyVO implements Serializable{
	@NotBlank(groups = InsertHint.class)
	private String mem_email;
	@Length(min=5,max=10)
	private String mem_nick;
	@Length(min=11,max=11)
	private String mem_hp;
	private String reg_date;
	
	private Integer favor_cnt;
	private Integer pay_cnt;
	private Integer cu_cnt;

}
