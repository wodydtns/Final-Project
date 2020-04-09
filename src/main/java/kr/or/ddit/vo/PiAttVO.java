package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 작성자명
 * @since 2020. 3. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 20.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@ToString(exclude="part")
@NoArgsConstructor
public class PiAttVO implements Serializable{
	
	public PiAttVO(MultipartFile part){
		this.part = part;
		ori_nm = part.getOriginalFilename();
		temp_nm = UUID.randomUUID().toString();
		rev_size = part.getSize();
		fancy = FileUtils.byteCountToDisplaySize(rev_size);
		mime = part.getContentType();
	}
	
	@JsonIgnore
	private MultipartFile part;
	private Integer pi_cd;
	private String temp_nm;
	private String ori_nm;
	private Long rev_size;
	private String fancy;
	private String mime;
	
	public void saveFile(File saveFolder) throws IOException {
		part.transferTo(new File(saveFolder, temp_nm));
	}
}
