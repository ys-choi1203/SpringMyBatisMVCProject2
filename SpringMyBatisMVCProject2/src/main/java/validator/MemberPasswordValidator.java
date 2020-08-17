package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.MemberCommand;

public class MemberPasswordValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		MemberCommand regReq = (MemberCommand) target;
		ValidationUtils.rejectIfEmpty(errors, "userPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPwCon", "required");
		ValidationUtils.rejectIfEmpty(errors, "oldPw", "required");
		if(!regReq.getUserPw().isEmpty()) {
			if(!regReq.isUserPwEqualToUserPwCon()) {
				
				errors.rejectValue("userPwCon", "nomatch");
			}
		}
	}
}
