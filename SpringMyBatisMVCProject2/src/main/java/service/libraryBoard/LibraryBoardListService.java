package service.libraryBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import controller.PageAction;
import model.LibraryBoardDTO;
import model.StartEndPageDTO;
import repository.LibraryBoardRepository;

@Service
public class LibraryBoardListService {
	@Autowired
	LibraryBoardRepository libraryBoardRepository; 
	public void libraryBoardList(Model model, Integer page) {
		int nowPage = 1;
		if(page != null) {
			nowPage = page;
		}
		int limit = 10; 
		int limitPage = 10 ;
		Long startRow = ((long)nowPage -1 ) * 10 +1;
		Long endRow = startRow + limit -1;
		
		LibraryBoardDTO dto = new LibraryBoardDTO();
		dto.setStartEndPageDTO(new StartEndPageDTO());
		dto.getStartEndPageDTO().setStartPage(startRow);
		dto.getStartEndPageDTO().setEndPage(endRow);
		
		List<LibraryBoardDTO> lists = 
				libraryBoardRepository.getLibraryList(dto);
		int count = libraryBoardRepository.getLibraryCount();
		
		model.addAttribute("lists", lists);
		model.addAttribute("count", count);
		
		PageAction pageAction = new PageAction();
		pageAction.page(
				model, count, limit, limitPage, nowPage,"libBoard?");
	}
}
