package service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import command.MemberCommand;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class PasswordChangeService {
	@Autowired
	MemberRepository remberRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public String execute(MemberCommand memberCommand, Model model) {
		MemberDTO dto = new MemberDTO();
		dto.setUserId(memberCommand.getUserId());
		dto.setStartEndPageDTO( new StartEndPageDTO());
		dto.getStartEndPageDTO().setEndPage(1L);
		dto.getStartEndPageDTO().setStartPage(1L);
		System.out.println(dto.getUserId());
		MemberDTO member = remberRepository.selectByMember(dto);
		System.out.println(member.getUserPw());
		if(bcryptPasswordEncoder.matches(
				memberCommand.getUserPw(), member.getUserPw())) {
			return "member/pwModify_1";
		}else {
			model.addAttribute("err","비밀번호가 틀립니다.");
			return "member/pwModify";
		}
	}
}
