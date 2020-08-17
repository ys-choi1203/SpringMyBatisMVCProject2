package controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.LoginCommand;
import service.member.CookieService;

@Controller
public class MainController {
	@Autowired
	private CookieService cookieService; 
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public String mainForm(
			@ModelAttribute("loginCommand") 
						LoginCommand loginCommand, 
						HttpServletRequest request) {
		cookieService.getCookie(request);
		return "main/main";
	}
}
