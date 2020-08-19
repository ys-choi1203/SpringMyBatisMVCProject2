package service.libraryBoard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import command.FileInfo;

@Service
public class FileDelService {

	public void fileAdd(FileInfo fileInfo, HttpSession session,
							Model model) {
		String path = null;
		List<FileInfo> list = (List<FileInfo>) session.getAttribute("fileList");
		if(list == null) {
			list = new ArrayList<FileInfo>();
			//list.add(fileInfo);
		}
		// session이 있는 경우
		Integer num = null;
		Boolean newFile = true;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStoreFileName().equals(fileInfo.getStoreFileName())) {
				list.remove(i);
				newFile = false;
				num = 0;
			}
		}
		if(newFile) {
			list.add(fileInfo);
			num = 1;
		}
		model.addAttribute("val", num);
		session.setAttribute("fileList", list);

	}

}
