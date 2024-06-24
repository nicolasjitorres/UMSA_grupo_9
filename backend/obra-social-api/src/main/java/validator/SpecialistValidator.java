package validator;

import java.util.List;

import dto.SpecialistDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Validator;
import model.Specialist;

@ApplicationScoped
public class SpecialistValidator {

	private Validator validator;

	public SpecialistValidator(Validator validator) {
		super();
		this.validator = validator;
	}
	
	public List<String> validateSpecialist(Specialist specialist) {
		var errors = validator.validate(specialist);
		if (errors.isEmpty()) {
			return null;
		} else {
			return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
		}
	}
	
}
