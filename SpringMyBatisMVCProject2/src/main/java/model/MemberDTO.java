package model;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	String userId ;
	String userPw  ; 
	String userName;
	Timestamp userBirth;
	String userGender;
	String userEmail;
	String userAddr;
	String userPh1;
	String userPh2;
	Timestamp userRegist;
	String joinOk;
	String interest;
	
	StartEndPageDTO startEndPageDTO;
}
