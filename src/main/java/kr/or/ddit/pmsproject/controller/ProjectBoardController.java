package kr.or.ddit.pmsproject.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.pmsproject.service.IProjectBoardService;
import kr.or.ddit.pmsproject.service.IProjectWorkReplyService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProjListVO;
import kr.or.ddit.vo.PwBoardVO;
import kr.or.ddit.vo.PwListVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 작성자명
 * @since 2020. 4. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 1.      최효은       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */

@Controller
@RequestMapping("/pmsProject")
public class ProjectBoardController {

	@Inject
	IProjectBoardService service;
	
	@Value("#{appInfo['projectBoardImagePath']}")
	String saveFolderUrl;
	
	File saveFolder;
	
	@Inject
	WebApplicationContext context;
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
	
	// 프로젝트 리스트 출력(ajax)
	@GetMapping(value="projectBoardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<PwBoardVO> AllListForAjax(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="what", required=true, defaultValue="1") int proj_cd
			, @ModelAttribute("psVO") PwBoardVO searchDetail
			, @ModelAttribute("search") SearchVO search
			, Model model
			) {
		AllList(currentPage, proj_cd, searchDetail, search, model);
		PagingVO<PwBoardVO> pagingVO = (PagingVO) model.asMap().get("pagingVO");
		return pagingVO;
	}
	
	// 프로젝트 리스트 출력(view)
	@GetMapping("projectBoardList.do")
	public String AllList (
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="what", required=true, defaultValue="1") int proj_cd
			, @ModelAttribute("psVO") PwBoardVO searchDetail
			, @ModelAttribute("search") SearchVO searchVO
			, Model model
			){
		PagingVO<PwBoardVO> pagingVO = new PagingVO<>();
		searchDetail.setProj_cd(proj_cd);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchDetail(searchDetail);
		pagingVO.setTotalRecord(service.readProjcetBoardCount(pagingVO));
		pagingVO.setCurrentPage(currentPage);
		List<PwBoardVO> projectBoardList = service.readProjcetBoardList(pagingVO);
		pagingVO.setDataList(projectBoardList);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		viewName = "pmsprojectwork/projectBoardList";
		return viewName;
	}

	
	// 게시글 상세 내역
	@GetMapping("projectBoardView.do")
	public String viewDisplay1(
			@RequestParam(name="what", required=true) int stu_post_cd
			, @CookieValue(name="likeCookie", required=false) String cookieValue
			, Model model
			) throws IOException {
		PwBoardVO projectBoardList = service.readProjcetBoard(stu_post_cd);
		
		service.incrementHit(stu_post_cd);
		
		boolean recommended = false;
	 	if(StringUtils.isNotBlank(cookieValue)) {
	 		ObjectMapper mapper = new ObjectMapper();
			
	 		int[] boNOArray = mapper.readValue(cookieValue, int[].class);
	 		Arrays.sort(boNOArray);	 
	 		if(Arrays.binarySearch(boNOArray, stu_post_cd)>=0) {
	 			recommended = true;
	 		}
	 	}
	 	projectBoardList.setRecommended(recommended);
		
		model.addAttribute("pbList", projectBoardList);
		return "pmsprojectwork/projectBoardView";
	}
	

	// 작업 등록 폼 불러오기
	@GetMapping("projectBoardInsert.do")
	public String insertform(
			@RequestParam(name="what", required=true) int stu_post_cd
			, Model model
			){
		return "pmsprojectwork/projectBoardForm";
	}
	
	// 작업 등록
	@PostMapping("projectBoardInsert.do")
	public String insert(
			@Validated(InsertHint.class) @ModelAttribute("pbList") PwBoardVO pbVO
			, Errors errors
			, Model model
			){
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			int pm_cd = service.readProjectMember(pbVO);
			pbVO.setPm_cd(pm_cd);
			ServiceResult result = service.createProjcetBoard(pbVO);
			switch (result) {
			case OK:
				viewName = "redirect:/pmsProject/projectBoardView.do?what="+pbVO.getStu_post_cd();
				break;
			default:
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "pmsprojectwork/projectBoardForm";
				break;
			}
		}else {
			viewName = "pmsprojectwork/projectBoardForm";
		}
		model.addAttribute("message", message);
		return viewName;	
	}
	
	// 작업 수정 폼 불러오기
	@GetMapping("projectBoardUpdate.do")
	public String Updateform(
			@RequestParam(name="what", required=true) int stu_post_cd
			, Model model
			){
			PwBoardVO readProjectBoard = service.readProjcetBoard(stu_post_cd);
			model.addAttribute("pbList", readProjectBoard);
		return "pmsprojectwork/projectBoardForm";
	}
	
	// 작업 수정
	@PostMapping("projectBoardUpdate.do")
	public String update(
			@Validated(UpdateHint.class) @ModelAttribute("pbList") PwBoardVO pbVO
			, Errors errors
			, Model model
			){
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyProjcetBoard(pbVO);
			switch (result) {
				case OK:
					viewName = "redirect:/pmsProject/projectBoardView.do?what="+pbVO.getStu_post_cd();
					break;
				default:
					message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
					viewName = "pmsprojectwork/projectBoardForm";
					break;
			}
		}else {
			viewName = "pmsprojectwork/projectBoardForm";
		}
		model.addAttribute("message", message);
		return viewName;	
	}
	

	// 작업 삭제
	@GetMapping("projectBoardDelete.do")
	public String delete(
			@RequestParam(name="what", required=true) int stu_post_cd
			, @RequestParam(name="who", required=true) String mem_email
			, Model model
			, RedirectAttributes redirectAttributes 
			) {
		PwBoardVO pbVO = new PwBoardVO();
		pbVO.setStu_post_cd(stu_post_cd);
		ServiceResult result = service.removeProjcetBoard(pbVO);
		String viewName = null;
		String message = null;
		switch (result) {
			case OK:
				viewName = "redirect:/pmsProject/projectBoardList.do?who="+mem_email;
				break;
			case NOTEXIST:
				message = "해당 작업을 찾을 수 없습니다.";
				viewName = "redirect:/pmsProject/projectBoardList.do?who="+mem_email;
				break;
			default:
				message = "서버 오류입니다. 잠시 뒤에 시도해주세요.";
				viewName = "projectwork/projectBoardForm";
				break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
	
	// 에디터 사용해서 이미지 저장하기
	@PostMapping(value="projectBoardImageInsert.do",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> upload(	// 마샬링의 대상이 되는 객체가 리턴타입
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
		
	
	
}
























