package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude="part")
@NoArgsConstructor
public class ClassPiVO implements Serializable{
	/*piVO*/
	// 클래스 순위를 위한 property
	private Integer rank;
	@NotBlank(groups = InsertHint.class)
	private String cate_cd;
	@NotBlank(groups = InsertHint.class)
	private String lod_cd;
	private String lod_nm;
	@NotBlank(groups = InsertHint.class)
	private String crea_email;
	@NotBlank(groups = InsertHint.class)
	private String pi_nm;
	@NotBlank(groups = InsertHint.class)
	private String pi_intro;
	@NotBlank(groups = InsertHint.class)
	private String pi_add_info;
	private String pi_start;
	private String pi_end;
	private String pi_open;
	//@NotNull(groups = InsertHint.class)
	private Integer pi_cnt;
	//@NotBlank(groups = InsertHint.class)
	private String yn_code;
	//@NotBlank(groups = InsertHint.class)
	private String class_yn;
	
	private ClassVO classVO;
	
	private String cate_nm;
	
	@NotNull
	private MultipartFile cover_img; //커버이미지 1:1
	
	public void setCover_img(MultipartFile cover_img) {
		this.cover_img = cover_img;
		if(cover_img!=null) {
				if(StringUtils.isNotBlank(cover_img.getOriginalFilename())) {
//				2) 메타
				PiAttVO attatchVO = new PiAttVO(cover_img);
				this.pi_att = attatchVO;
			}
		}
	}
	
	//1:1 클래스 커버이미지
	private PiAttVO pi_att;
	
	// 게시글 순번을 위한 rownum
	private Integer rnum;
	
	private Integer rowcnt;
	
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

	
	/*piattaVO*/
	public ClassPiVO(MultipartFile part){
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
	
	/*classVO*/
	private Integer cl_cd; // 클래스 코드
	@NotNull
	private Integer pre_cd; // 사전조사 코드
	@NotBlank(groups = InsertHint.class)
	private String cl_start; // 강의시작 날짜
	@NotBlank(groups = InsertHint.class)
	private String cl_end; // 강의종료 날짜
	@NotNull
	private Integer cl_fee; // 수강료
	@NotNull(groups = UpdateHint.class)
	private Integer cl_like; // 좋아요 수
	
	
//	private List<MultipartFile> class_file;
//	public void setClass_file(List<MultipartFile> class_file) {
//		this.class_file = class_file;
//		if(class_file!=null && class_file.size()>0) {
//			curriculumList = new ArrayList<>();
//			for(MultipartFile tmp : class_file) {
//				if(StringUtils.isBlank(tmp.getOriginalFilename()))
//					continue;
////				2) 메타
//				CurriculumVO curriculum = new CurriculumVO(tmp);
//				curriculumList.add(curriculum);
//			}
//		}
//	}
	
	private List<CurriculumVO> curriculumList;//1:N
	private List<List<CurriculumVO>> groups;
	
	
	//controller에서 넘어온 vo가 알아서 바인딩해주기 때문에 여기는 필요없고 컨트롤러에서 setGroups호출해서 해결한다.!
//	public void setCurriculumList(List<CurriculumVO> curriculumList) {
//		
//		this.curriculumList = curriculumList;
//	}
	
	public void genGroups(List<CurriculumVO> curriculumList) {
		//curri_cd값이 있는 것들로 추출 ==> 하나의 커리큘럼(그룹)의 시작임 = 상위커리큘럼제목
		//다음 curri_cd가 나오기전까지는 하나의 커리큘럼에 속한 세부커리큘럼들이다.
		boolean check = false; 
//		int parentcd = 0;
		List<CurriculumVO> list = null;
		groups = new ArrayList<>();
		for(CurriculumVO curri : curriculumList) {
			
			//if(curri==null) groups.add(list);
			if(curri.getCurri_cd()!=null) {//상위 커리큘럼일 때
				if(check) {
					this.curriculumList = list;
					groups.add(list);
				}
				check=false;
				list = new ArrayList<>();
//				parentcd = curri.getCurri_cd();
				list.add(curri);
			}else if(curri.getCurri_cd2()!=null){//하위 커리큘럼일 때(&&curri.getCurri_cd2().equals(parentcd))
				check = true;
				//하나의 list에 담는다.
				list.add(curri);
			}
		}
		groups.add(list);
	}
	

	private int startAttNo;
	//private List<ReplyVO> replyList;
	
	private int[] delAttNos;
}
