package service.libraryBoard;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import command.LibraryCommand;
import model.AuthInfo;
import model.LibraryBoardDTO;
import repository.LibraryBoardRepository;
@Service
public class LibraryBoardService {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	@Autowired
	LibraryBoardRepository libraryBoardRepository;
	public void writePro(LibraryCommand libraryCommand,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String userId = authInfo.getUserId();
		LibraryBoardDTO dto = new LibraryBoardDTO();
		dto.setBoardContent(libraryCommand.getBoardContent());
		dto.setBoardName(libraryCommand.getBoardName());
		dto.setBoardPass(bCryptPasswordEncoder.encode(
				libraryCommand.getBoardPass()));
		dto.setBoardSubject(libraryCommand.getBoardSubject());
		dto.setIpAddr(request.getRemoteAddr());
		dto.setUserId(userId);
		String path = "WEB-INF/view/lib_Board/upload";
		String filePath = session.getServletContext().getRealPath(path);
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal ="";
		for(MultipartFile mf : libraryCommand.getReport()) {
			String original = mf.getOriginalFilename();
			String originalFileExtension = 
					original.substring(original.lastIndexOf("."));
			String store = 
					UUID.randomUUID().toString().replace("-", "")
					+originalFileExtension;
			String fileSize = Long.toString(mf.getSize());
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			File file = new File(filePath + "\\" + store);
			try {
				mf.transferTo(file); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		dto.setFileSize(fileSizeTotal);
		libraryBoardRepository.libraryInsert(dto);
	}
}
