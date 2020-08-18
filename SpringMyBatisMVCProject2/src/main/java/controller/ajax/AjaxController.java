package controller.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjaxController {
	@RequestMapping("/ajaxTest")
	public String ajaxTest() {
		return "ajax/AjaxTest";
	}
	
	@RequestMapping("/AjaxTest1")
	public String ajaxTest1() {
		return "ajax/ajaxTest1";
	}
	
	@RequestMapping("/AjaxTest2")
	public String ajaxTest2(@RequestParam(value="num") Integer num, Model model) {
		model.addAttribute("num", num); 
		return "ajax/ajaxTest2";
	}
}
