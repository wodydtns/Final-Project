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
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      작성자명   박재욱   최초작성 박재욱
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@ToString(exclude="class_video")
@NoArgsConstructor
public class CurriculumVO implements Serializable{
	
	//public CurriculumVO(MultipartFile class_video){
	public void genClass_video(MultipartFile class_video) {
		this.class_video = class_video;
		curri_ori_nm = class_video.getOriginalFilename();
		curri_temp_nm = UUID.randomUUID().toString();
		curri_size = class_video.getSize();
		curri_fancy = FileUtils.byteCountToDisplaySize(curri_size);
		curri_mime = class_video.getContentType();
	}
	@JsonIgnore
	private MultipartFile class_video;
	private Integer curri_cd;
	private Integer cl_cd;
	private String curri_nm;
	private Integer curri_cd2;
	private Integer curri_order;
	private String curri_temp_nm;
	private String curri_ori_nm;
	private Long   curri_size;
	private String curri_fancy;
	private String curri_mime;
	
	public void saveFile(File saveFolder) throws IOException {
		class_video.transferTo(new File(saveFolder, curri_temp_nm));
	}
	
	
	
	//클래스 커뮤니티
		private Integer commu_cd;
		private String mem_email;
		private String commu_cont;
		private String commu_date;
		
		//커뮤니티 댓글
		private Integer comr_cd;
		private String comr_comm_cont;
		private String comr_comm_date;
		private String comr_temp_nm;
		private String comr_ori_nm;
		private Integer comr_size;
		private String comr_fancy;
		private String comr_mime;
}
