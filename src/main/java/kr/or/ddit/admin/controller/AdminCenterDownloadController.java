package kr.or.ddit.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.admin.service.IAdminCsCenterService;
import kr.or.ddit.vo.CsCenterAttVO;

@Controller
public class AdminCenterDownloadController {
	@Inject
	IAdminCsCenterService service;
	
	@GetMapping("/master/download.do")
	public String download(
			int cs_cd, Model model
			) {
		CsCenterAttVO attach = service.attDownload(cs_cd);
		model.addAttribute("attach", attach);
		return "downloadView";
	}
}
