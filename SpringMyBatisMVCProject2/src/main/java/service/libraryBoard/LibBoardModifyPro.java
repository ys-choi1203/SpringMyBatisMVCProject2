package service.libraryBoard;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import command.FileInfo;
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
		List<FileInfo> list = (List<FileInfo>) session.getAttribute("fileList");
		if(list != null) {
			for (FileInfo fi : list) {
				lib.getOriginalFileName().replace(fi.getOriginalFileName()+"`", "");
				lib.getStoreFileName().replace(fi.getStoreFileName()+"`", "");
				lib.getFileSize().replace(fi.getFileSize()+"`", "");
			}
		}
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		File file = null;
		String path1 = "WEB-INF/view/lib_Board/upload";
		String filePath = session.getServletContext().getRealPath(path1);
		
		for (MultipartFile mf : libraryCommand.getReport()) {
			String original = mf.getOriginalFilename();
			String originalFileExtension = original.substring(original.lastIndexOf("."));
			
			String store = UUID.randomUUID().toString().replace("-", "")
							+ originalFileExtension;
			
			String fileSize = Long.toString(mf.getSize());
			originalTotal += original + "-";
			storeTotal += store + "-";
			fileSizeTotal += fileSize + "-";
			// 파일 저장 시작
			file = new File(filePath + "\\" + store);
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 파일 저장 끝
		}
		dto.setOriginalFileName(originalTotal + lib.getOriginalFileName());
		dto.setStoreFileName(storeTotal + lib.getStoreFileName());
		dto.setFileSize(fileSizeTotal + lib.getFileSize());
		if(bcryptPasswordEncoder.matches(libraryCommand.getBoardPass(), 
				lib.getBoardPass())) {
			libraryBoardRepository.libraryUpdate(dto);
			// 파일 삭제
			if(list != null) {
				for (FileInfo fi : list) {		
					file = new File(filePath + "/" + fi.getStoreFileName().replace("`", ""));
					if(file.exists()) {
						file.delete();
					}
				}
				session.removeAttribute("fileList");
			}
			path = "redirect:libDetail/"+lib.getBoardNum();
		}else {
			path = "lib_Board/lib_board_modify";
		}
		return path;
	}
}
