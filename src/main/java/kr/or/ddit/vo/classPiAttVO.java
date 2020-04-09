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

@Data
@NoArgsConstructor
public class classPiAttVO implements Serializable{

	/*클래스vo*/
	private Integer cl_cd; // 클래스 코드
	private Integer pre_cd; // 사전조사 코드
	private String cl_start; // 강의시작 날짜
	private String cl_end; // 강의종료 날짜
	private Integer cl_fee; // 수강료
	private Integer cl_like; // 좋아요 수
	private Integer rnum;
	
	/*pivo*/
	private String cate_cd;
	private String lod_cd;
	private String lod_nm;
	private String crea_email;
	private String pi_nm;
	private String pi_intro;
	private String pi_add_info;
	private String pi_start;
	private String pi_end;
	private String pi_open;
	private Integer pi_cnt;
	private String yn_code;
	
	/*piattvo*/
	public classPiAttVO(MultipartFile part){
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
