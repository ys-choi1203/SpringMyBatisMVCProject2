package controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.MemberCommand;
import service.member.MemberJoinService;
import validator.MemberCommandValidator;

@Controller
@RequestMapping("register")
public class MemberController {
	@Autowired
	MemberJoinService memberJoinService;
	@RequestMapping("agree")
	public String agree() {
		return "member/agree";
	}
	@RequestMapping("regist")
	public String  memberForm(
			@RequestParam(value="agree", defaultValue = "false") 
			Boolean agree,Model model) {
		if(!agree) return "member/agree";
		model.addAttribute("memberCommand", new MemberCommand());
		return "member/memberForm";
	}
	@RequestMapping(value="memberJoin",method=RequestMethod.POST)
	public String  memberJoin(
			MemberCommand memberCommand,Errors errors,
			Model model) {
		new MemberCommandValidator().validate(memberCommand, errors);
		if(errors.hasErrors()) {
			model.addAttribute("err","날짜형식이 맞지 않습니다.");
			return "member/memberForm";
		}
		// 중복확인 
		Integer i = memberJoinService.execute(memberCommand);
		if(i == null) {
			errors.rejectValue("userId", "duplicate");
			return "member/memberForm";
		}
		return "member/memberWelcome";
	}
	@RequestMapping("memberMail")
	public String memberMail(@RequestParam(value = "num") String num,
			@RequestParam(value = "reciver")String reciver,
			@RequestParam(value = "userId")String userId) {
		Integer i = memberJoinService.numUpdate(num,reciver,userId );
		if(i > 0) {
			return "member/success";
		}else {
			return "member/fail";
		}
	}
}










