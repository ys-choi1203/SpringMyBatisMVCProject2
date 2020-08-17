package service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import command.MemberCommand;
import model.AuthInfo;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class MemberPasswordService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public void execute(MemberCommand memberCommand,Model model,
			HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserEmail(authInfo.getEmail());
		memberDTO.setUserId(authInfo.getUserId());
		memberDTO.setStartEndPageDTO(new StartEndPageDTO());
		memberDTO.getStartEndPageDTO().setEndPage(1L);
		memberDTO.getStartEndPageDTO().setStartPage(1L);
		MemberDTO member = 	memberRepository.selectByMember(memberDTO);
		if(bcryptPasswordEncoder.matches(
				memberCommand.getOldPw(), member.getUserPw())) {
			memberDTO.setUserPw(bcryptPasswordEncoder.encode(
					memberCommand.getUserPw()));
			memberRepository.passwordUpdate(memberDTO);
			model.addAttribute("userId",authInfo.getUserId() );
		}
	}
}
