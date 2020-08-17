package controller.library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.LibraryCommand;
import model.AuthInfo;
import service.libraryBoard.LibBoardModifyPro;
import service.libraryBoard.LibraryBoardDetailService;
import service.libraryBoard.LibraryBoardListService;
import service.libraryBoard.LibraryBoardService;
import validator.LibraryCommandValidator;

@Controller
@RequestMapping("lib")
public class LibraryController {
	@Autowired
	LibraryBoardService libraryBoardService;
	@Autowired
	LibraryBoardListService libraryBoardListService;
	@Autowired
	LibraryBoardDetailService libraryBoardDetailService;
	@Autowired
	LibBoardModifyPro libBoardModifyPro;
	@RequestMapping("libBoard")
	public String libBoard(
			@RequestParam(value="page", required = false) Integer page,
			Model model) {
		libraryBoardListService.libraryBoardList(model,page);
		return "lib_Board/lib_board_list";
	}
	@RequestMapping("boardWriteForm")
	public String boardWriteForm(
			@ModelAttribute(value = "libraryCommand")
				LibraryCommand libraryCommand/*,Model model*/) {
		//model.addAttribute("libraryCommand", new LibraryCommand());
		return "lib_Board/lib_board_write";
	}
	@RequestMapping(value = "boardWritePro" , method= RequestMethod.POST)
	public String boardWritePro(LibraryCommand libraryCommand,
			Errors errors, HttpServletRequest request) {
		new LibraryCommandValidator().validate(libraryCommand, errors);
		if(errors.hasErrors()) {
			return "lib_Board/lib_board_write";
		}
		libraryBoardService.writePro(libraryCommand, request);
		return "redirect:/lib/libBoard"; 
	}
	@RequestMapping("libBoardModify")
	public String libBoardModify(@RequestParam(value = "boardNum")
			Integer boardNum,Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String path = libraryBoardDetailService.libModify(boardNum, model, 
				authInfo.getUserId());
		return path;
	}
	@RequestMapping(value = "libBoardModifyPro", 
			method=RequestMethod.POST)
	public String libBoardModifyPro(LibraryCommand libraryCommand,
			HttpSession session) {
		String path = 
				libBoardModifyPro.libraryUpdate(libraryCommand, session);
		return path;
	}
	@RequestMapping("libDetail/{id}")
	public String  libDetail(@PathVariable(value = "id") 
			Integer boardNum,Model model) {
		libraryBoardDetailService.libDetail(boardNum, model);
		return "lib_Board/lib_board_view";
	}
}
