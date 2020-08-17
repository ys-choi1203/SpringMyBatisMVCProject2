package controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.AuthInfo;
import service.member.MemberDetailService;
import service.member.MemberInfoService;

@Controller
@RequestMapping("mem")
public class MemberInfoControler {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberDetailService memberDetailService;
	@RequestMapping("memberList")
	public String memberList(
			@RequestParam(value = "page" , defaultValue = "1")
			Integer page, Model model) {
		memberInfoService.memberList(page, model);
		return "member/memberList";
	}
	@RequestMapping("memberInfo/{userId}")
	public String memberDetail(
			@PathVariable(value="userId") String userId,
			Model model) {
		memberDetailService.memberDetail(userId, model);
		return "member/memberInfo";
	}
	@RequestMapping("memberDetail")
	public String memberInfo(
			Model model,HttpSession session ) {
		// 개인 사용자이므로 session으로부터 userId를 가져옴
		AuthInfo authInfo = 
				(AuthInfo)session.getAttribute("authInfo");
		String userId = authInfo.getUserId();
		memberDetailService.memberDetail(userId, model);
		return "member/memberDetail";
	}
	
	
	
}
