package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LibraryCommandValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "boardName", "required");
		ValidationUtils.rejectIfEmpty(errors, "boardSubject", "required");
		ValidationUtils.rejectIfEmpty(errors, "boardPass", "required");	
	}
}
