package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * 2020. 3. 23.      작성자명 박재욱       파일 첨부 기능 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@ToString(exclude="part")
@EqualsAndHashCode(of="st_cd")
@Alias("centerAttVO")
public class CsCenterAttVO implements Serializable{
	public CsCenterAttVO(MultipartFile part){
		this.part = part;
		ori_nm = part.getOriginalFilename();
		temp_nm = UUID.randomUUID().toString();
		cs_size = part.getSize();
		fancy = FileUtils.byteCountToDisplaySize(cs_size);
		mime = part.getContentType();
	}
	
	@JsonIgnore
	private MultipartFile part;
	private Integer st_cd;
	private Integer cs_cd;
	private String temp_nm;
	private String ori_nm;
	private long cs_size;
	private String fancy;
	private String mime;
	
	public void saveFile(File saveFolder) throws IOException {
		part.transferTo(new File(saveFolder, temp_nm));
	}
	
	
}
