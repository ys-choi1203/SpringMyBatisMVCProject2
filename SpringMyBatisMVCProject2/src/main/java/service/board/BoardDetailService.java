package service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import command.BoardCommand;
import model.BoardDTO;
import repository.BoardRepository;

public class BoardDetailService {
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public void boardDetail(Integer boardNum,Model model) {
		BoardDTO dto = new BoardDTO();
		dto.setBoardNum(boardNum);
		dto = boardRepository.getBoardList(dto).get(0); 
		model.addAttribute("boardCommand",dto);
	}
	public String boardDelete(Integer boardNum, Errors errors) {
		BoardDTO board = new BoardDTO();
		board.setBoardNum(boardNum);
		BoardDTO dto = boardRepository.getBoardList(board).get(0);
		if(bcryptPasswordEncoder.matches(
				"aaaaa",dto.getBoardPass() )) {
			boardRepository.boardDelete(board);
			return "redirect:/qna/qnaList";
		}else {
			errors.rejectValue("boardPass", "badPass");
			return "board/qna_board_modify";
		}
	}
	public String boardUpdate(BoardCommand boardCommand, Errors errors) {
		BoardDTO board = new BoardDTO();
		board.setBoardNum(boardCommand.getBoardNum());
		board.setBoardContent(boardCommand.getBoardContent());
		board.setBoardSubject(boardCommand.getBoardSubject());
		BoardDTO dto = boardRepository.getBoardList(board).get(0);
		if(bcryptPasswordEncoder.matches(
				boardCommand.getBoardPass(),dto.getBoardPass() )) {
			boardRepository.boardUpdate(board);
			return "redirect:/qna/boardDetail?num="+boardCommand.getBoardNum();
		}else {
			errors.rejectValue("boardPass", "badPass");
			return "board/qna_board_modify";
		}
	}	
}
