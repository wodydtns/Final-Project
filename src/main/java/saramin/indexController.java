package saramin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//출처 https://junior-dev.tistory.com/217?category=729555


@Controller
public class indexController {
	
	@GetMapping(value="/saramin/index.do")
	public String form(){
		return "/saramin/test";
	}
	
	
	
}
	
	
	