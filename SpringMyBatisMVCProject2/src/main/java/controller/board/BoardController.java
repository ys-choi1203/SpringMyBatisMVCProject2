package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.BoardCommand;
import service.board.BoardDetailService;
import service.board.BoardListService;
import service.board.BoardWriteService;
import validator.BoardCommandValidator;

@Controller
@RequestMapping("qna")
public class BoardController {
	@Autowired
	BoardWriteService boardWriteService;
	@Autowired
	BoardListService boardListService;
	@Autowired
	BoardDetailService boardDetailService;
	
	@RequestMapping(value="qnaDelete")
	public String qnaDelete(@RequestParam("boardNum") Integer boardNum,
			Errors errors) {
		String path =  boardDetailService.boardDelete(boardNum, errors);
		return path;
	}
	@RequestMapping(value="qnaBoardModifyPro")
	public String qnaBoardModifyPro(BoardCommand boardCommand,
			Errors errors) {
		new BoardCommandValidator().validate(boardCommand, errors);
		if(errors.hasErrors()) {
			return "board/qna_board_modify";
		}
		String path = boardDetailService.boardUpdate(boardCommand,errors);
		return path;
	}
	@RequestMapping(value="qnaModify")
	public String qnaModify(BoardCommand boardCommand,
			@RequestParam("num") Integer boardNum,
			Model model) {
		boardDetailService.boardDetail(
				boardNum, model);
		return "board/qna_board_modify";
	}
	@RequestMapping(value="qnaList",method= RequestMethod.GET)
	public String list(
			@RequestParam(value = "page" , defaultValue = "1") 
			Integer page,	Model model) {
		boardListService.boardAllSelect(page, model);
		return "board/qna_board_list";
	}
	@RequestMapping("boardWrite")
	public String boardWrite(BoardCommand boardCommand) {
		return "board/qna_board_write";
	}
	@RequestMapping(value ="boardWritePro",method= RequestMethod.POST)
	public String boardWritePro(BoardCommand boardCommand,
			Errors errors, HttpSession session,HttpServletRequest request) {
		new BoardCommandValidator().validate(boardCommand, errors);
		if(errors.hasErrors()) {
			return "board/qna_board_write";
		}
		boardWriteService.boardInsert(boardCommand, session,request);
		return "redirect:/qna/qnaList";
	}
	@RequestMapping(value ="boardDetail")
	public String detail(@RequestParam("num") Integer boardNum,
			Model model) {
		boardDetailService.boardDetail(boardNum, model);
		return "board/qna_board_view";
	}
}
