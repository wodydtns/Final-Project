package kr.or.ddit.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class recruitAreaController {
	
	@GetMapping(value="/recruit/recruitArea.do")
	public String doget() {
		return "recruit/recruitArea";
	}
}
