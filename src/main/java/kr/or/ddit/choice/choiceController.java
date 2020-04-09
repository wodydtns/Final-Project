package kr.or.ddit.choice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class choiceController {
	@RequestMapping(value="choice/choice.do")
	public String choiceView() {
		return "choice/choice";
	}
}
