package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

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
 * 2020. 3. 11.      작성자명   박재욱    최초작성 박재욱
 * 2020. 3. 25.      작성자명   박재욱            이력서를 조회할 수 있는 형태로 변경
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="res_cd")
@ToString
public class ResumeVO implements Serializable {
	/*  PK  */
	private Integer res_cd;
	
	private String res_title;
	private String res_temp_nm;
	private String res_ori_nm;
	private Integer res_rec_size;
	private String res_fancy;
	private String res_mime;
	private String res_nm;
	private String res_bir;
	private String res_gen;
	private String res_tel;
	private String res_hp;
	private String res_port_url;
	private String res_hope_sal;
	private String res_hope_loc;
	private String addr_detail;  //지역코드
	
	/*  FK */
	private String mem_email;
	
	private Integer job_cd;      //직업코드
	private String emp_type_cd;  //경력코드
	private Integer area_cd;     //지역코드
	private String yn_code;      //사용유무

	// 학력 상세
	private List<AcaDetailVO> acaDetailVO;

	//award
	private List<AwardVO> awardVO;
	
	//edu
	private List<EduCorpVO> eduCorpVO;
	
	//ext
	private List<ExtVO> extVO;
	
	// emp_mil
	private List<EmpMilVO> empmilVO;
	// lang
	private List<LanguageVO> languageVO;
	// career
	private List<CareerVO> careerVO;
	// area
	private List<AreaVO> areaVO;
	
	// lic
	private List<LICVO> licVO;
	
	
}
