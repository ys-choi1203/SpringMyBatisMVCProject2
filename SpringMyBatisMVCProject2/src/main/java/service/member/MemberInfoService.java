package service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import controller.PageAction;
import model.MemberDTO;
import model.StartEndPageDTO;
import repository.MemberRepository;
@Service
public class MemberInfoService {
	@Autowired
	MemberRepository memberRepository;
	public void memberList(Integer page,Model model) {
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setStartEndPageDTO(new StartEndPageDTO());
		memberDTO.getStartEndPageDTO().setEndPage(endRow);
		memberDTO.getStartEndPageDTO().setStartPage(startRow);
		
		List<MemberDTO> members = 
				memberRepository.getMemberList(memberDTO);
		int count = memberRepository.getMemberCount();
		
		model.addAttribute("lists", members);
		model.addAttribute("count", count);
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "memberList?");
	}
}
