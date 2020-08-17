package service.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AuthInfo;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class CookieService {
	
	@Autowired
	MemberRepository memberRepository;
	private AuthInfo authInfo;
	
	public void  getCookie(HttpServletRequest request) {
		Cookie [] cookies = request.getCookies();
		HttpSession session = request.getSession();
		if(cookies != null && cookies.length >0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().startsWith("id")) {
					request.setAttribute("isId", cookie.getValue());
				}
				if(cookie.getName().startsWith("auto")) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setUserId(cookie.getValue());
					memberDTO.setStartEndPageDTO(new StartEndPageDTO());
					memberDTO.getStartEndPageDTO().setEndPage(1L);
					memberDTO.getStartEndPageDTO().setStartPage(1L);
					memberDTO =  memberRepository.selectByMember(memberDTO);
					authInfo = new AuthInfo(memberDTO.getUserId(),
							memberDTO.getUserEmail(),
							memberDTO.getUserName(),memberDTO.getUserPw());
					session.setAttribute("authInfo",authInfo);
				}
			}
		}
	}
}
