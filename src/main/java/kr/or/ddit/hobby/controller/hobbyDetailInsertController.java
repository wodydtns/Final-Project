package kr.or.ddit.hobby.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hobby.service.IHobbyDetailService;
import kr.or.ddit.vo.CommReplyVO;

@Controller
@RequestMapping("/hobby")
public class hobbyDetailInsertController {

	@Inject
	WebApplicationContext context;
	ServletContext application;

	@Value("#{appInfo.adminImgPath}")
	String saveFolderUrl;
	@Value("#{appInfo.adminImgPath}")
	File saveFolder;

	@PostConstruct
	public void init() {
		application = context.getServletContext();
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	@Inject
	IHobbyDetailService service;
	
	@GetMapping(value="hobbyDetailInsert.do")
	public String form() {
		return "hobby/hobbyDetailInsert";
	}
	
	@PostMapping(value="hobbyDetailInsert.do")
	public String insert(
			@ModelAttribute("commReply") CommReplyVO commReplyVO
			,Errors errors, Model model
		) {
		
		String viewName = null;
		String message = null;
		
		System.out.println(commReplyVO.toString());
		if(!errors.hasErrors()) {
			int cnt = service.createBoard(commReplyVO);
			if(cnt>0) {
				viewName="redirect:/hobby/hobbyDetail.do?cl_cd="+commReplyVO.getCommu_cd();
			}else {
				message="서버오류";
				viewName="hobby/hobbyDetailInsert";
			}
		}else {
			viewName="hobby/hobbyDetailInsert";
		}
		model.addAttribute("message", message);
		return viewName;		
	}
	
	// 이미지를 보여주기 위한 메소드
		@PostMapping(value = "hobbyDetailImageUpload.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public Map<String, Object> hobbyDetailImageUpload(@RequestParam("file") MultipartFile image) throws IOException {

			// 성공 여부를 확인할 변수
			int uploaded = 0;
			// 실제 파일 이름
			String fileName = null;
			// 설정할 url
			String url = null;
			// 메시지
			String message = "이미지 업로드 실패";
			// 파일이 존재한다면
			if (StringUtils.isNotBlank(image.getOriginalFilename())) {
				// db에 저장할 이름으로 변경
				String savename = UUID.randomUUID().toString();

				image.transferTo(new File(saveFolder, savename));
				uploaded = 1;
				fileName = image.getOriginalFilename();
				url = application.getContextPath() + saveFolderUrl + "/" + savename;
				message = null;
			}

			// 결과 데이터와 url을 담을 map 객체
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("uploaded", uploaded);
			resultMap.put("fileName", fileName);
			resultMap.put("url", url);
			if (message != null) {
				resultMap.put("error.message", message);
			}
			return resultMap;
		}
}
