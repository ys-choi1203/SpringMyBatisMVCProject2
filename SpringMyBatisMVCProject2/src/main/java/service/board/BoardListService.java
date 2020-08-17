package service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import controller.PageAction;
import model.BoardDTO;
import model.StartEndPageDTO;
import repository.BoardRepository;
@Service
public class BoardListService {
	@Autowired
	BoardRepository boardRepository;
	public void boardAllSelect(Integer page,Model model) {
		int limit = 10;
		int limitPage = 10;
		Long startRow = ((long)page -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setStartEndPageDTO(new StartEndPageDTO());
		boardDTO.getStartEndPageDTO().setEndPage(endRow);
		boardDTO.getStartEndPageDTO().setStartPage(startRow);
		
		List<BoardDTO> boards = 
				boardRepository.getBoardList(boardDTO);
		int count = boardRepository.getBoardCount();
		
		model.addAttribute("lists", boards);
		model.addAttribute("count", count);
		
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "qnaList?");	
	}
}
