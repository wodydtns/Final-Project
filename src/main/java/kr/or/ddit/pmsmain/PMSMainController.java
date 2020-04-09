package kr.or.ddit.pmsmain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pmsProject")
public class PMSMainController {

	@GetMapping("/pmsMain.do")
	public String pmsMain() {
		return "pmsMain/pms-main";
	}
	
}
