package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	Integer boardNum;
	String userId;
	String boardName;
	String boardPass;
	String boardSubject;
	String boardContent;
	Timestamp boardDate;
	String ipAddr;
	Integer readCount;
	
	StartEndPageDTO startEndPageDTO;

	public BoardDTO(Integer boardNum, String userId, String boardName, 
			String boardPass, String boardSubject,
			String boardContent, Timestamp boardDate, String ipAddr, 
			Integer readCount) {
		this.boardNum = boardNum;
		this.userId = userId;
		this.boardName = boardName;
		this.boardPass = boardPass;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.ipAddr = ipAddr;
		this.readCount = readCount;
	}
}
