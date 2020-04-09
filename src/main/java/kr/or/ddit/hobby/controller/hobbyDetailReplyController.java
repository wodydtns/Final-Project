package kr.or.ddit.hobby.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hobby.service.IHobbyDetailReplyService;
import kr.or.ddit.hobby.service.IHobbyDetailService;
import kr.or.ddit.vo.CommReplyVO;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/reply")
public class hobbyDetailReplyController {
	@Inject
	IHobbyDetailService service;
	
	//커뮤니티글에 댓글등록
	@PostMapping(value="replyInsert.do")
	public String reply(
			@RequestParam(name="what",required=true) int cl_cd ,
			@ModelAttribute("commReply") CommReplyVO commReply, HttpSession session
			,Errors errors
			,Model model) {
		boolean valid = !errors.hasErrors();
		String message = null;
		ServiceResult result = null;
		
		MemberVO mem = (MemberVO) session.getAttribute("authMember");
		String goPage = null;
		
		if(mem==null) {//로그인되어있지 않을 때
			goPage = "redirect:/login/login.do";
		}else {
			
			if(valid) {
				System.err.println("타타타타타타타타니2?");
				commReply.setMem_email(mem.getMem_email());
				result =  service.createReply(commReply);
				switch (result) {
				case OK: // login redirect 이동 (굳이 데이터 가져갈 필요 없음)
					goPage = "redirect:/hobby/hobbyDetail.do?what="+cl_cd;
					break;
				case FAIL: // memberForm (서버의 잘못이라 데이터를 그대로 유지시켜야함)
					//6. 뷰설정
					goPage = "redirect:/hobby/hobbyDetail.do?what="+cl_cd;
					message = "서버 오류";
					break;
				}
			}else {
				goPage = "redirect:/hobby/hobbyDetail.do?what="+cl_cd;
				message = "에러 발생";
			}
			
			
		}
		return goPage;
	}
	
	
//	//댓글등록
//	@PostMapping(value="detailReplyInsert.do")
//	public Map<String, Object> detailReplyInsert(@Validated(InsertHint.class) CommReplyVO reply
//			, Errors errors, Model model
//			, @RequestPart(name="comr_image", required=false) MultipartFile imageFile) throws IOException{
//				
//		if(imageFile!=null) {
//			reply.setComr_image(imageFile);
//		}
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("reply", reply);
//		resultMap.put("errors", errors);
//		boolean success = false;
//		String message = null;
//		model.addAttribute("reply", reply);
//		
//		if(!errors.hasErrors()) {
//			ServiceResult result = service.createReply(reply);
//			if(ServiceResult.OK.equals(result)) {
//				//댓글 등록 성공
//				success=true;
//			}else {
//				message="서버오류";
//				success=false;
//			}
//		}else {
//			success=false;
//		}
//		resultMap.put("message", message);
//		resultMap.put("success", success);
//		return resultMap;
//		
//	}
	
	
}






