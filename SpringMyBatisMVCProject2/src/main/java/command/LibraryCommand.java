package command;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCommand {
	private Integer boardNum;
	private String boardName;
	private String boardPass;
	private String boardSubject;
	private String boardContent;
	private String ipAddr; 
	private MultipartFile [] report;
}
