package kr.or.ddit.pmsproject.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.pmsproject.service.IProjectWorkService;
import kr.or.ddit.vo.PwListVO;

@Controller
@RequestMapping("pmsProject")
public class ProjectWorkCUDController {

	@Inject
	IProjectWorkService service;
	
	@Value("#{appInfo['projectWorkImagePath']}")
	String saveFolderUrl;
	
	File saveFolder; 
	
	@Inject
	WebApplicationContext context; //얘주입후에 서블릿컨텍스트를 잡아야한다.
	ServletContext application;
	
	@PostConstruct
	private void init() {
		application = context.getServletContext();
		String path = application.getRealPath(saveFolderUrl); 
		saveFolder = new File(path);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs(); 
		}
	}
	
	// 작업 생성 폼 불러오기
	@GetMapping("projectWorkInsert.do")
	public String InsertForm() {
		return "pmsprojectwork/projectWorkForm";
	}
	
	// 작업 생성
	@PostMapping("projectWorkInsert.do")
	public String insert(
			@Validated(InsertHint.class) @ModelAttribute("pwList") PwListVO pwList
			, Errors errors
			, Model model
			){
		String viewName = null;
		String message = null;
		System.out.println(errors.getAllErrors());
		if(!errors.hasErrors()) {
			ServiceResult result = service.createProjectWork(pwList);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/pmsProject/projectWorkView.do?what="+pwList.getPw_cd();
			}else {
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "pmsprojectwork/projectWorkForm";
			}
		}else {
			viewName = "pmsprojectwork/projectWorkForm";
		}
		model.addAttribute("message", message);
		return viewName;		
	}
	
	// 에디터 사용해서 이미지 저장하기
	@PostMapping(value="projectWorkImageInsert.do",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> upload(//마샬링의대상이되는객체가 리턴타입
			@RequestParam("file") MultipartFile image			
			) throws IOException {
		//파트데이터 꺼내기
		int uploaded = 0; // 업로드 된 것
		String fileName = null;
		String url = null; // ckeditor가 필요로 함. img 태그의 src 에서 씀. 클라이언트 사이드 절대경로 형태가 됨.
		String message = "이미지 업로드를 실패했습니다."; // 실패했을 때 메세지
		
		
		if(StringUtils.isNotBlank(image.getOriginalFilename())) { // 파일이 업로드 됐는지 확인
			// 1) 2진데이터처리 : /piImages에 저장 
			String savename = UUID.randomUUID().toString();
			System.out.println(new File(saveFolder,savename).getAbsolutePath());
			image.transferTo(new File(saveFolder,savename)); // 저장
			uploaded = 1;
			//메타데이터 만들기
			fileName = image.getOriginalFilename(); // 원본 파일명 가져옴
			url = application.getContextPath() + saveFolderUrl + "/" +  savename; // 내가 만드는 것
			message = null; // 업로드 여부 판단 가능
		}
		
		Map<String, Object> resultMap = new HashMap<>(); 
		resultMap.put("uploaded", uploaded);
		resultMap.put("fileName", fileName);
		resultMap.put("url", url);
		
		if(message!=null) {
			resultMap.put("error.message", message);
		}
		
		return resultMap;
		
	}
	
	// 작업 수정 폼 불러오기
	@GetMapping("projectWorkUpdate.do")
	public String Updateform(
			@RequestParam(name="what", required=true) int pw_cd
			, Model model
			){
		PwListVO readProjectWork = service.readProjectWorkAtt(pw_cd);
		model.addAttribute("pwList", readProjectWork);
		return "pmsprojectwork/projectWorkForm";
	}
	
	// 작업 수정
	@PostMapping("projectWorkUpdate.do")
	public String update(
			@Validated(UpdateHint.class) @ModelAttribute("pwList") PwListVO pwList
			, Errors errors
			, Model model
			){
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyProjectWork(pwList);
			switch (result) {
				case OK:
					viewName = "redirect:/pmsProject/projectWorkView.do?what="+pwList.getPw_cd();
					break;
				default:
					message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
					viewName = "pmsprojectwork/projectWorkForm";
					break;
			}
		}else {
			viewName = "pmsprojectwork/projectWorkForm";
		}
		model.addAttribute("message", message);
		return viewName;	
	}
	
	// 작업 삭제
	@GetMapping("projectWorkDelete.do")
	public String delete(
			@RequestParam(name="what", required=true) int pw_cd
			, @RequestParam(name="who", required=true) String mem_email
			, @RequestParam(name="proj_cd", required=true) String proj_cd
			, Model model
			, RedirectAttributes redirectAttributes 
			) {
		PwListVO pwList = new PwListVO();
		pwList.setPw_cd(pw_cd);
		ServiceResult result = service.removeProjectWork(pwList);
		String viewName = null;
		String message = null;
		switch (result) {
			case OK:
				viewName = "redirect:/pmsProject/projectWorkList.do?what="+proj_cd+"&who="+mem_email;
				break;
			case NOTEXIST:
				message = "해당 작업을 찾을 수 없습니다.";
				viewName = "redirect:/pmsProject/projectWorkList.do?what="+proj_cd+"&who="+mem_email;
				break;
			default:
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "projectwork/projectWorkForm";
				break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
	
	
}
