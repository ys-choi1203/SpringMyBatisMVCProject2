package controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.LoginCommand;
import service.member.AuthService;
import validator.LoginCommandValidator;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AuthService authService;
	@RequestMapping(method = RequestMethod.GET)
	public String main() {
		return "redirect:/";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String submit(LoginCommand loginCommand,
			Errors errors, HttpSession session,
			HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		authService.authenticate(loginCommand,session,errors,response);
		if (errors.hasErrors()) {
			return "main/main";
		}
		return "redirect:/";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session,
			HttpServletResponse response) {
		Cookie cookie = new Cookie("autoLogin","");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		session.invalidate();
		return "redirect:/";
	}
}
