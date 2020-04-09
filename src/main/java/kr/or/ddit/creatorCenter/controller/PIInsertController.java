package kr.or.ddit.creatorCenter.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.creatorCenter.service.IPIService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.vo.PIVO;
@Controller
public class PIInsertController {
	
	@Inject
	IPIService service;
	
	@Value("#{appInfo.piImgPath}")
	String saveFolderUrl;
	
	
	//@Value("#{appInfo.piImgrealPath}")
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
	
	
	@GetMapping(value="/creatorCenter/piInsert.do")
	public String piInsertForm(HttpServletRequest req) {
		return "creatorCenter/makePI";
	}
	
	@PostMapping(value="/creatorCenter/piInsert.do")
	public String piInsert(@Validated(InsertHint.class) @ModelAttribute PIVO pi, 
			Errors errors,
			Model model) {
		boolean valid = !errors.hasErrors();
		String goPage = null;
		String message = null;
		ServiceResult result = null;
		System.out.println("타니1?");
		
		if(valid) {
			System.out.println("타니2?");
			result =  service.createPI(pi);
			switch (result) {
			case OK: // login redirect 이동 (굳이 데이터 가져갈 필요 없음)
				goPage = "redirect:/creatorCenter/pi_wait_List.do";
				break;
			case FAIL: // memberForm (서버의 잘못이라 데이터를 그대로 유지시켜야함)
				//6. 뷰설정
				goPage = "creatorCenter/makePI";
				message = "서버 오류";
				break;
			}
		}else {
			goPage = "creatorCenter/makePI";
		}
		model.addAttribute("message", message);
		return goPage;
	}
	
	//에디터사용
	@PostMapping(value="/creatorCenter/piInsert1.do",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> upload(//마샬링의대상이되는객체가 리턴타입
			@RequestParam("file") MultipartFile image			
			) throws IOException {
		//파트데이터 꺼내기
		int uploaded = 0; //업로드된것
		String fileName = null;
		String url = null; //ckeditor가 필요로 함. img태그의 src에서 씀. clientside 절대경로 형태가 됨.
		String message = "이미지 업로드 실패"; //실패했을때 메세지
		
		
		if(StringUtils.isNotBlank(image.getOriginalFilename())) { //파일이업로드됐는지확인
		// 1) 2진데이터처리 : /piImages에 저장 
		String savename = UUID.randomUUID().toString();
		System.out.println(new File(saveFolder,savename).getAbsolutePath());
		image.transferTo(new File(saveFolder,savename)); //저장
		uploaded = 1;
		//메타데이터 만들기
		fileName = image.getOriginalFilename(); //원본파일명가져옴
		url = application.getContextPath() + saveFolderUrl + "/" +  savename; //내가만드는것
		message = null; //업로드여부판단가능
			
		}
		
		Map<String, Object> resultMap = new HashMap<>(); 
		resultMap.put("uploaded",uploaded);
		resultMap.put("fileName",fileName);
		resultMap.put("url",url);
		
		if(message!=null) {
			resultMap.put("error.message",message);
		}
		
		return resultMap;
		
	}
}
