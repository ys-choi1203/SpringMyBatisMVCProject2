package service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class MemberDetailService {
	@Autowired
	MemberRepository memberRepository;
	public void memberDetail(String userId, Model model) {
		MemberDTO member = new MemberDTO();
		member.setStartEndPageDTO(new StartEndPageDTO());
		member.getStartEndPageDTO().setEndPage(1L);
		member.getStartEndPageDTO().setStartPage(1L);
		member.setUserId(userId);
		List<MemberDTO> list = memberRepository.getMemberList(member);
		model.addAttribute("memberCommand",list.get(0) );
	}
}
