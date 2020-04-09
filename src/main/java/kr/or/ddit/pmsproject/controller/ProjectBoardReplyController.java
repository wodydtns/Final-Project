package kr.or.ddit.pmsproject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.pmsproject.service.IProjectBoardReplyService;
import kr.or.ddit.pmsproject.service.IProjectWorkReplyService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PwBoardCommentVO;

@RestController
@RequestMapping(value="/pmsProject", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjectBoardReplyController {

	@Inject
	IProjectBoardReplyService service;
	
	@Inject
	MessageSourceAccessor accessor;		// 스프링 자체의 에러 메세지를 위함 
	
	// 스프링이 지원하지 않는 에러 메세지 출력 보조 기능
	private void generateErrorMsgMap(Errors errors, Map<String, Object> resultMap) {
		Map<String, String> errorMagMap = new HashMap<>();
		resultMap.put("errors", errorMagMap);
		List<FieldError> fieldErrors = errors.getFieldErrors();
		for(FieldError tmp : fieldErrors) {
			// 키 필요
			String propName = tmp.getField();	// 코드명으로 사용
			String code = tmp.getCode();	// 이 메세지를 해결할 수 있는 default 코드를 가져온다.
											// 여기서 코드 리절버가 작동함.
			// 스프링: 메세지 라는 역할과 똑같다.
			String arg = accessor.getMessage("reply." + propName);	// reply.rep_no 형태가 돌아온다.
			String msg = accessor.getMessage(code, new Object[] {arg});
			
			// 이 이름으로 넣을 수 있는 맵이 하나 필요함
			resultMap.put(propName, msg);
		}
	}
	
	// 댓글 리스트 조회
	@GetMapping("projectBoardReplyList.do")
	public PagingVO<PwBoardCommentVO> replyList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="what", required=true, defaultValue="1") int stu_post_cd
			) throws IOException{
		PwBoardCommentVO searchDetail = new PwBoardCommentVO();
		searchDetail.setStu_post_cd(stu_post_cd);
		
		PagingVO<PwBoardCommentVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchDetail(searchDetail);
		pagingVO.setCurrentPage(currentPage);
		
		pagingVO.setTotalRecord(service.readProjectBoardReplyCount(pagingVO));
		List<PwBoardCommentVO> replyList = service.readProjectBoardReplyList(pagingVO);
		
		pagingVO.setDataList(replyList);
		
		return pagingVO;
	}
	

	@PostMapping("projectBoardReplyInsert.do")
	public Map<String, Object> replyInsert(
			@Validated(InsertHint.class) @ModelAttribute("pbVO") PwBoardCommentVO pbVO
			, Errors errors
			){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pbVO", pbVO);
		boolean success = false;
		String message = null;
		if(!errors.hasErrors()) {
			int pm_cd = service.readProjectMember(pbVO);
			pbVO.setPm_cd(pm_cd);
			ServiceResult result = service.createReply(pbVO);
			if(ServiceResult.OK.equals(result)) {
				success = true;
			}else {
				success = false;
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
			}
		}else {
			success = false;
			generateErrorMsgMap(errors, resultMap);
		}
		resultMap.put("message", message);
		resultMap.put("success", success);
		
		return resultMap;
	}
	
	@PostMapping("projectBoardReplyUpdate.do")
	public Map<String, Object> replyUpdate(
			@Validated(InsertHint.class) @ModelAttribute("pbVO") PwBoardCommentVO pbVO
			, Errors errors
			){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reply", pbVO);
		boolean success = false;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyReply(pbVO);
			if(ServiceResult.OK.equals(result)) {
				success = true;
			}else {
				success = false;
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
			}
		}else {
			success = false;
			generateErrorMsgMap(errors, resultMap);
		}
		resultMap.put("message", message);
		resultMap.put("success", success);
		
		return resultMap;
	}
	
	@PostMapping("projectBoardReplyDelete.do")
	public Map<String, Object> replyDelete(
			@Validated(InsertHint.class) @ModelAttribute("pbVO") PwBoardCommentVO pbVO
			, Errors errors
			){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reply", pbVO);
		boolean success = false;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeReply(pbVO);
			if(ServiceResult.OK.equals(result)) {
				success = true;
			}else {
				success = false;
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
			}
		}else {
			success = false;
			generateErrorMsgMap(errors, resultMap);
		}
		resultMap.put("message", message);
		resultMap.put("success", success);
		
		return resultMap;
	}
		
}
