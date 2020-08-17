package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryBoardDTO /*implements Serializable*/{
	Integer boardNum;
	String userId;
	String boardName;
	String boardPass;
	String boardSubject;
	String boardContent;
	Timestamp boardDate;
	String ipAddr;
	Integer readCount;
	String originalFileName;
	String storeFileName;
	String fileSize;
	//  한 쿼리문으로  모든 행또는 한행만 가져오기 위해서 
	StartEndPageDTO startEndPageDTO;
	// alt+shift+s o 
	public LibraryBoardDTO(Integer boardNum, String userId, String boardName, String boardPass, String boardSubject,
			String boardContent, Timestamp boardDate, String ipAddr, Integer readCount, String originalFileName,
			String storeFileName, String fileSize) {
		this.boardNum = boardNum;
		this.userId = userId;
		this.boardName = boardName;
		this.boardPass = boardPass;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.ipAddr = ipAddr;
		this.readCount = readCount;
		this.originalFileName = originalFileName;
		this.storeFileName = storeFileName;
		this.fileSize = fileSize;
	}
	
}
