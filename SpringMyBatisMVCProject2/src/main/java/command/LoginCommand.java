package command;

import lombok.Data;

@Data
public class LoginCommand {
	private String userId;
	private Boolean idStore;
	private Boolean autoLogin;
	private String userPw;
}
