package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일       		수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.   박재욱   	 최초작성 
 * 2020. 3. 19.   김혜정 		사전조사이름추가
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@Alias("classVO")
public class ClassVO implements Serializable{
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
	private Integer rnum;
	
	private String pi_nm; //=사전조사이름
	private String lod_nm; // 난이도
	private Integer pi_cnt; // 사전조사 수 
	private String cate_nm; // 카테고리 이름
	private String pi_add_info;
	private String pi_intro;
	private String temp_nm;
	
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
