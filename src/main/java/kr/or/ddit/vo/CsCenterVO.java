package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
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
 * 2020. 3. 11.      작성자명  박재욱     최초작성  박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@Alias("CSCenterVO")
@EqualsAndHashCode(of="cs_cd")
@ToString(exclude= {"center_file","attachList"})
@NoArgsConstructor
@AllArgsConstructor
public class CsCenterVO implements Serializable{
	
	// 게시글 번호
	private Integer rn;
	private Integer cs_cd;
	private String admin_id;
	private String cs_bo_cd;
	private String header_cd;
	private String cs_title;
	private String cs_cont;
	private String cs_date;
	private Integer cs_hit;
	
	// 게시판 종류명
	private String cs_bo_nm;
	
	// 말머리 명
	private String header_nm;
	// 첨부 파일
	private List<MultipartFile> center_file;
	public void setCenter_file(List<MultipartFile> center_file) {
		this.center_file = center_file;
		if(center_file!=null && center_file.size()>0) {
			attachList = new ArrayList<>();
			for(MultipartFile tmp : center_file) {
				if(StringUtils.isBlank(tmp.getOriginalFilename()))
					continue;
//				2) 메타
				CsCenterAttVO attatchVO = new CsCenterAttVO(tmp);
				attachList.add(attatchVO);
			}
		}
	}
	
	private List<CsCenterAttVO> attachList;
	private int startSt_cd;
	
	private int[] delStNos;
	private Integer rowcnt;
	
	
}
