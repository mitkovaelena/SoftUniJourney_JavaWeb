package org.softuni.residentEvil.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class PresentOrPassedDateValidator implements ConstraintValidator<PresentOrPassedDate, Date> {

	@Override
    public boolean isValid(Date date, ConstraintValidatorContext arg1) {
		return date == null || date.before(new Date());
	}
	
}