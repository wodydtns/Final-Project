package kr.or.ddit.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class recruitJobController {
	@GetMapping(value="/recruit/recruitJob.do")
	public String doget() {
		return "recruit/recruitJob";
	}
}
