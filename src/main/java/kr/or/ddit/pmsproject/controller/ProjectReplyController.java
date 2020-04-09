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
import kr.or.ddit.hint.DeleteHint;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.pmsproject.service.IProjectWorkReplyService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjCommentVO;

@RestController
@RequestMapping(value="/pmsProject", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjectReplyController {
	
	@Inject
	IProjectWorkReplyService service;
	
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
	@GetMapping("projectWorkReplyList.do")
	public PagingVO<ProjCommentVO> replyList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="what", required=true, defaultValue="1") int pw_cd
			) throws IOException{
		ProjCommentVO searchDetail = new ProjCommentVO();
		searchDetail.setPw_cd(pw_cd);
		
		PagingVO<ProjCommentVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchDetail(searchDetail);
		pagingVO.setCurrentPage(currentPage);
		
		pagingVO.setTotalRecord(service.readProjectWorkReplyCount(pagingVO));
		List<ProjCommentVO> replyList = service.readProjectWorkReplyList(pagingVO);
		
		pagingVO.setDataList(replyList);
		
		
		return pagingVO;
	}
	
	@PostMapping("projectWorkReplyInsert.do")
	public Map<String, Object> replyInsert(
			@Validated(InsertHint.class) @ModelAttribute("pcVO") ProjCommentVO pcVO
			, Errors errors
			){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pcVO", pcVO);
		boolean success = false;
		String message = null;
		if(!errors.hasErrors()) {
			int pm_cd = service.readProjectMember(pcVO);
			pcVO.setPm_cd(pm_cd);
			ServiceResult result = service.createReply(pcVO);
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
	
	@PostMapping("projectWorkReplyUpdate.do")
	public Map<String, Object> replyUpdate(
			@Validated(InsertHint.class) @ModelAttribute("pcVO") ProjCommentVO pcVO
			, Errors errors
			){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reply", pcVO);
		boolean success = false;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyReply(pcVO);
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
	
	@PostMapping("projectWorkReplyDelete.do")
	public Map<String, Object> replyDelete(
			@Validated(InsertHint.class) @ModelAttribute("pcVO") ProjCommentVO pcVO
			, Errors errors
			){
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reply", pcVO);
		boolean success = false;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeReply(pcVO);
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
