package validator;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Validator;
import model.Location;

@ApplicationScoped
public class LocationValidator {

	private Validator validator;

	public LocationValidator(Validator validator) {
		super();
		this.validator = validator;
	}
	
	public List<String> validateLocation(Location location) {
		var errors = validator.validate(location);
		if (errors.isEmpty()) {
			return null;
		} else {
			return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
		}
	}
	
}
