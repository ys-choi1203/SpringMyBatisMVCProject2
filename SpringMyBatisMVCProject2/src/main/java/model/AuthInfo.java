package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//로그인시 필요한 사용자 정보 저장하기 위한 class
public class AuthInfo {
	private String userId;
	private String email;
	private String name;
	private String userPw;
}
