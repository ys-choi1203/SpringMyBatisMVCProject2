package service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import command.MemberCommand;
import controller.MailService;
import controller.RamdomPassword;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class FindPasswordService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	MailService mailService; 
	public String findPass(MemberCommand memberCommand,Model model) {
		MemberDTO dto = new MemberDTO();
		dto.setUserEmail(memberCommand.getUserEmail());
		dto.setUserId(memberCommand.getUserId());
		dto.setStartEndPageDTO(new StartEndPageDTO());
		dto.getStartEndPageDTO().setEndPage(1L);
		dto.getStartEndPageDTO().setStartPage(1L);
		MemberDTO member = memberRepository.selectByMember(dto);
		if(member != null) {
			String pass = RamdomPassword.getRamdomPassword(10);
			String newPw = bcryptPasswordEncoder.encode(pass);
			dto.setUserPw(newPw);
			memberRepository.passwordUpdate(dto);
			String content = member.getUserName() + "님 임시비밀번호는 " 
			               + pass +"입니다.";
			String subject = "비밀번호 변경";
			try{
				mailService.sendMail(dto.getUserEmail(), 
						dto.getUserId(), content, subject);
			}catch(Exception e) {
				e.printStackTrace();
			}					
			model.addAttribute("member", member);
			return "member/passView";
		}else {
			model.addAttribute("err","이메일이나 아이디가 잘못되었습니다.");
			return "member/findPassword";
		}
	}
}
