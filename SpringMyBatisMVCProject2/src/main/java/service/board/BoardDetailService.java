package service.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import command.BoardCommand;
import model.AuthInfo;
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
	public String boardDelete(Integer boardNum, String boardPass,
				HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		BoardDTO board = new BoardDTO();
		board.setBoardNum(boardNum);
		board.setUserId(authInfo.getUserId());

		BoardDTO dto = boardRepository.getBoardList(board).get(0);
		if(bcryptPasswordEncoder.matches(boardPass, dto.getBoardPass())) {
			int i = boardRepository.boardDelete(board);
			model.addAttribute("val", i);
			return "board/delPage";
		}else {
			model.addAttribute("err", "비밀번호가 틀렸습니다.");
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
