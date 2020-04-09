package kr.or.ddit.pmsproject.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.pmsproject.service.IProjectBoardService;
import kr.or.ddit.pmsproject.service.IProjectWorkService;
import kr.or.ddit.vo.PMSAttVO;

@Controller
@RequestMapping("/pmsProject")
public class ProjectDownloadController  {
	
	@Inject
	IProjectWorkService pwService;
	@Inject
	IProjectBoardService pbService;
	
	@GetMapping("PWDownload.do")
	public String pwDownload(
			@RequestParam(name="what", required=true) int st_cd
			, Model model
			
			) throws IOException {
		PMSAttVO attatch = pwService.projectWorkAttDownload(st_cd);
		model.addAttribute("attatch", attatch);
		return "projectWorkDownloadView";
	}
	
	@GetMapping("PBDownload.do")
	public String pbDownload(
			@RequestParam(name="what", required=true) int st_cd
			, Model model
			
			) throws IOException {
		PMSAttVO attatch = pbService.selectBoardAttDownload(st_cd);
		model.addAttribute("attatch", attatch);
		return "projectBoardDownloadView";
	}
	
}
