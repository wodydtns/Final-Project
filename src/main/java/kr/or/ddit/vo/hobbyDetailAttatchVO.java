package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude="part")
@EqualsAndHashCode(of="cl_cd")
@Alias("hobbyDetailAttVO")
public class hobbyDetailAttatchVO implements Serializable{

	public hobbyDetailAttatchVO(MultipartFile part) {
		this.part = part;
		comr_ori_nm = part.getOriginalFilename(); // part.getFilename(); 원본명 필요하니 part.get~~로 바꿈
		comr_temp_nm = UUID.randomUUID().toString(); //part.getSavename(); 저장명은 우리가 만들어야함.
		comr_size = part.getSize();  // part.getFilesize();
		comr_fancy = FileUtils.byteCountToDisplaySize(comr_size); // part.getFancySize
		comr_mime = part.getContentType();// part.getMime()
	}
	private MultipartFile part;
	private String comr_temp_nm;
	private String comr_ori_nm;
	private long comr_size;
	private String comr_fancy;
	private String comr_mime;
	
	public void saveFile(File saveFolder) throws IOException {
		part.transferTo(new File(saveFolder, comr_temp_nm)); // part.saveFile(saveFolder);
	}
}
