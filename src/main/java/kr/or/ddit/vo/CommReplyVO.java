package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.hint.InsertHint;
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
 * 2020. 3. 11.      작성자명  박재욱     최초작성 박재욱
 * 2020. 4. 01. 김혜정 수정
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@Alias("commReplyVO")
@ToString(exclude="reply_img")
@NoArgsConstructor
public class CommReplyVO implements Serializable{
	
	public CommReplyVO(MultipartFile reply_img) {
		this.reply_img = reply_img;
		comr_ori_nm = reply_img.getOriginalFilename(); // reply_img.getFilename(); 원본명 필요하니 reply_img.get~~로 바꿈
		comr_temp_nm = UUID.randomUUID().toString(); //reply_img.getSavename(); 저장명은 우리가 만들어야함.
		comr_size = reply_img.getSize();  // reply_img.getFilesize();
		comr_fancy = FileUtils.byteCountToDisplaySize(comr_size); // reply_img.getFancySize
		comr_mime = reply_img.getContentType();// reply_img.getMime()
	}
	
	private MultipartFile reply_img;
	
//	public void setReply_img(MultipartFile reply_img) {
//		this.reply_img = reply_img;
//		if(reply_img!=null) {
//			if(StringUtils.isNotBlank(reply_img.getOriginalFilename())) {
////			2) 메타
//			CommReplyVO comm = new CommReplyVO(reply_img);
//		}
//	}
//	}
	
	private Integer comr_cd;
	@NotBlank(groups = InsertHint.class)
	private Integer commu_cd;
	@NotBlank(groups = InsertHint.class)
	private String mem_email;
	@NotBlank(groups = InsertHint.class)
	private String comr_comm_cont;
	private String comr_comm_date;
	
	private String comr_temp_nm;
	private String comr_ori_nm;
	private Long comr_size;
	private String comr_fancy;
	private String comr_mime;
	private String mem_nick;
	
//	
//	private int rnum;
//	
//	private List<hobbyDetailAttatchVO> attachList;
//	private int startSt_cd;
//	
//	private int[] delStNos;
//	private Integer rowcnt;
	
	public void saveFile(File saveFolder) throws IOException {
		reply_img.transferTo(new File(saveFolder, comr_temp_nm)); // reply_img.saveFile(saveFolder);
	}
}
