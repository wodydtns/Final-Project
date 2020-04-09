package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
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
 * 2020. 3. 11.      작성자명  박재욱   최초작성  박재욱
 * 2020. 3. 28.      최효은   			첨부파일을 위한 생성자 및 메서드 추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of="st_cd")
@ToString(exclude="part")
public class PMSAttVO implements Serializable{
	
	// MultipartFile를 파라미터로 받았을 경우 첨부파일의 정보를 생성하는 생성자
	public PMSAttVO(MultipartFile part){
		this.part = part;
		ori_nm = part.getOriginalFilename();
		temp_nm = UUID.randomUUID().toString();
		stu_size = part.getSize();
		fancy = FileUtils.byteCountToDisplaySize(stu_size);
		mime = part.getContentType();
	}
	
	// 첨부파일의 Multipart를 받기 위한 프라퍼티
	@JsonIgnore
	private MultipartFile part;
	
	private Integer st_cd;
	private Integer stu_post_cd;
	private Integer comm_cd;
	private Integer pw_cd;
	private String temp_nm;
	private String ori_nm;
	private Long stu_size;
	private String fancy;
	private String mime;
	
	// 2진 데이터 저장
	public void saveFile(File saveFolder) throws IOException {
		part.transferTo(new File(saveFolder, temp_nm));
	}
}
