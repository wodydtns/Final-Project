package kr.or.ddit.creatorCenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.creatorCenter.service.IClassService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CreatorVO2;
import kr.or.ddit.vo.CurriculumVO;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/creatorCenter")
public class classRetrieveController {
	
	@Inject
	IClassService service;
	
	//내클래스조회
	@GetMapping(value="classList.do", 
	 produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> ajax(HttpSession session , Model model) {
		ServiceResult result = null;
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		if(member==null) {
			result = ServiceResult.NULLSESSION;
		}else {
			CreatorVO creator = service.creatorCheck(member.getMem_email()); 
			if(creator==null) {
				result = ServiceResult.NOTEXIST;
			}else {
				result = ServiceResult.EXIST;
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", result.name());
		return resultMap; 
	}
	
	@GetMapping(value="classList.do")
	public String doget(HttpSession session , Model model) {
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		String goPage = null;
		if(member==null) {
			goPage = "redirect:/login/login.do";
		}else {
			List<ClassVO> dataList = service.readMyClassList(member.getMem_email()); //1:1일때는 vo에 list 안만들어도됨
			model.addAttribute("dataList", dataList);
			goPage = "creatorCenter/cc-main";
		}
		return goPage;	//내클래스조회
	
	}
	
	//클래스상세정보조회
	@GetMapping(value="classView.do")
	public String view(@RequestParam(name="what",required=true) int cl_cd, Model model) {
		ClassVO classInfo = service.readMyClass(cl_cd);
		List<CurriculumVO> curriList = service.readMyCurri(cl_cd);
		model.addAttribute("classInfo", classInfo);
		model.addAttribute("curriList", curriList);
		return "creatorCenter/classView";
	}
	
	
	
	//크리에이터전환 insert
	@PostMapping(value="creatorInsert.do")
	public String doPost(@Validated(InsertHint.class) @ModelAttribute CreatorVO2 crea,
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String goPage = null;
		String message = null;
		ServiceResult result = null;
		
		if(valid) {
			result =  service.createCreator(crea);
			switch (result) {
			case OK: 
				goPage = "redirect:/creatorCenter/classList.do";
				break;
			case FAIL: 
				//6. 뷰설정
				goPage = "creatorCenter/changeCreator";
				message = "서버 오류";
				break;
			}
		}else {
			goPage = "creatorCenter/changeCreator";
		}
		model.addAttribute("message", message);
		return goPage;
	}
	

}
