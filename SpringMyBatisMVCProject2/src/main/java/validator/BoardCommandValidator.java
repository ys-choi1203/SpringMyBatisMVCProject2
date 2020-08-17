package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//BoardCommandValidator가 validate가 되디 위해 Validator 상속
public class BoardCommandValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "boardName","required");
		ValidationUtils.rejectIfEmpty(errors, "boardSubject","required");
		ValidationUtils.rejectIfEmpty(errors, "boardPass","required");
	}
}
