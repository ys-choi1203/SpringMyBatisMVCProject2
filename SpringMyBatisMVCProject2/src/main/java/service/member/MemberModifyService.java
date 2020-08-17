package service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import command.MemberCommand;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class MemberModifyService {
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	MemberRepository memberRepository;
	public String memberUpdate(MemberCommand memberCommand, Errors errors
			, Model model) {
		MemberDTO dto = new MemberDTO();
		dto.setUserAddr(memberCommand.getUserAddr());
		dto.setUserEmail(memberCommand.getUserEmail());
		dto.setUserId(memberCommand.getUserId());
		dto.setUserPh1(memberCommand.getUserPh1());
		dto.setUserPh2(memberCommand.getUserPh2());
		dto.setStartEndPageDTO(new StartEndPageDTO());
		dto.getStartEndPageDTO().setEndPage(1L);
		dto.getStartEndPageDTO().setStartPage(1L);
		/*
		 * 암호화된 비밀번호는 암호화를 할 때마다 다른 비밀번호가 되므로 암호화된 비밀번호를 
		 * 직접 비교할 수 없으므로 DTO에 넣어 DB로 보내는 것은 의미가 없다.
		dto.setUserPw(
				bcryptPasswordEncoder.encode(memberCommand.getUserPw()));
		*/
		MemberDTO member = memberRepository.selectByMember(dto);
		
		if(bcryptPasswordEncoder.matches(
				memberCommand.getUserPw() , member.getUserPw())) {
			memberRepository.memberUpdate(dto);
			
			return "redirect:" + memberCommand.getUrlPath();
		}else {
			errors.rejectValue("userPw", "wrong");
			model.addAttribute("urlPath",memberCommand.getUrlPath());
			return "member/memberModify";
		}
	}
}
