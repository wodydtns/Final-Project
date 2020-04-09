package kr.or.ddit.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.vo.CsCenterVO;

/**
 * @author 작성자명
 * @since 2020. 3. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 19.      작성자명   박재욱            최초작성 - FAQ, 공지사항
 * Copyright (c) 2020 by DDIT All right reserved
 *      </pre>
 */
@Controller
@RequestMapping("/master")
public class AdminCenterBoardInsertController {

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
	IAdminCsCenterService service;

	@GetMapping("adminCSCenterBoardWrite.do") // 프로시져 만들어야함
	public String WriteForm() {
		return "master/adminCSCenterBoardWrite";
	}

	@PostMapping("adminCSCenterBoardWrite.do")
	public String WriteCsBoard(
			@ModelAttribute("center") CsCenterVO csCenter, Model model,Errors errors) {
		String viewName = null;
		String message = null;
		
		System.out.println(csCenter.toString());
		if(!errors.hasErrors()) {
			int cnt = service.createCsCenterBoard(csCenter);
			if(cnt >0) {
				viewName = "redirect:/master/adminCSCenter.do?cs_cd="+csCenter.getCs_cd();
			}else {
				message ="서버 오류";
				viewName = "master/adminCSCenterBoardWrite";
			}
		}else {
			viewName = "master/adminCSCenterBoardWrite"; 
		}
		
		model.addAttribute("message", message);
		return viewName;
	}

	// 이미지를 보여주기 위한 메소드
	@PostMapping(value = "centerImageUpload.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> CsCenterImageUpload(@RequestParam("file") MultipartFile image) throws IOException {

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
