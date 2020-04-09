package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
/**
 * @author 작성자명
 * @since 2020. 3. 31.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 31.      김혜정       최초작성 creatorVO에 다른 프로퍼티들 제약조건때문에 따로 뺌
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@Alias("creatorVO2")
public class CreatorVO2 implements Serializable{
	private String crea_email;
	@NotBlank
	private String mem_intro;
	private String crea_date;
}
