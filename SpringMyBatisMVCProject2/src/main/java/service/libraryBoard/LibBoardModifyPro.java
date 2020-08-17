package service.libraryBoard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import command.LibraryCommand;
import model.AuthInfo;
import model.LibraryBoardDTO;
import repository.LibraryBoardRepository;
@Service
public class LibBoardModifyPro {
	@Autowired
	LibraryBoardRepository libraryBoardRepository;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	public String libraryUpdate(LibraryCommand libraryCommand,
				HttpSession session) {
		String path = "";
		LibraryBoardDTO dto = new LibraryBoardDTO();
		AuthInfo authInfo = 
				(AuthInfo)session.getAttribute("authInfo");
		dto.setBoardContent(libraryCommand.getBoardContent());
		dto.setBoardNum(libraryCommand.getBoardNum());
		dto.setBoardSubject(libraryCommand.getBoardSubject());
		dto.setUserId(authInfo.getUserId());
		LibraryBoardDTO lib = 
				libraryBoardRepository.getLibraryList(dto).get(0);
		if(bcryptPasswordEncoder.matches(libraryCommand.getBoardPass(), 
				lib.getBoardPass())) {
			libraryBoardRepository.libraryUpdate(dto);
			path = "redirect:libDetail/"+lib.getBoardNum();
		}else {
			path = "lib_Board/lib_board_modify";
		}
		return path;
	}
}
