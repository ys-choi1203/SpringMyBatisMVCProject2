package service.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import command.LoginCommand;
import model.AuthInfo;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;

@Service
public class AuthService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	private AuthInfo authInfo;
	public void authenticate(LoginCommand loginCommand,
			HttpSession session,Errors errors,
			HttpServletResponse response) {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(loginCommand.getUserId());
		memberDTO.setStartEndPageDTO(new StartEndPageDTO());
		memberDTO.getStartEndPageDTO().setEndPage(1L);
		memberDTO.getStartEndPageDTO().setStartPage(1L);
		memberDTO =  memberRepository.selectByMember(memberDTO);
		if(memberDTO == null) {
			errors.rejectValue("userId", "notId");
		}else {
			if(bcryptPasswordEncoder.matches(loginCommand.getUserPw(), 
					memberDTO.getUserPw())) {
				authInfo = new AuthInfo(memberDTO.getUserId(),
						memberDTO.getUserEmail(),
						memberDTO.getUserName(),memberDTO.getUserPw());
				session.setAttribute("authInfo",authInfo);
				System.out.println(loginCommand.getIdStore());
				
				if(loginCommand.getAutoLogin() != null) {
					Cookie cookie = new Cookie("autoLogin",authInfo.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}
				
				if(loginCommand.getIdStore() != null 
						&& loginCommand.getIdStore()) {
					Cookie cookie = new Cookie("idStore",authInfo.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("idStore","");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}else {
				errors.rejectValue("userPw","wrong");
			}
		}
	}
}
