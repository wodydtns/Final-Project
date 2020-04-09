package kr.or.ddit.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.service.IAdminMemberManageService;

@Controller
public class AdminMemberDeleteController {
	
	@Inject
	IAdminMemberManageService service;
	
	@GetMapping(value = "/master/memberDeleteManage.do")
	public String deleteMember(
			@RequestParam(required=true) String mem_email,
			RedirectAttributes redirectAttributes
			){
		String message = "";
		int result = service.executeDeleteMember(mem_email);
		if(result>0) {
			message = "탈퇴시켰습니다.";
		}else {
			message = "서버 오류, 조금 있다 시도하세요.";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/master/adminMemberManage.do";
	}
	
	@GetMapping(value = "/master/creatorDeleteManage.do")
	public String deleteCreator(
			@RequestParam(required=true) String mem_email,
			RedirectAttributes redirectAttributes
			){
		String message = "";
		int result = service.executeDeleteMember(mem_email);
		if(result>0) {
			message = "탈퇴시켰습니다.";
		}else {
			message = "서버 오류, 조금 있다 시도하세요.";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/master/adminCreatorManage.do";
	}
	
	@GetMapping(value = "/master/companyDeleteManage.do")
	public String deleteCompany(
			@RequestParam(required=true) String mem_email,
			RedirectAttributes redirectAttributes
			){
		String message = "";
		int result = service.executeDeleteMember(mem_email);
		if(result>0) {
			message = "탈퇴시켰습니다.";
		}else {
			message = "서버 오류, 조금 있다 시도하세요.";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/master/adminCompanyManage.do";
	}
}
