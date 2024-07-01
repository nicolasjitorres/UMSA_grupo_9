package validator;

import java.util.List;

import model.Affiliate;
import jakarta.enterprise.context.ApplicationScoped;
import model.Location;
import model.Schedule;
import model.Specialist;

@ApplicationScoped
public class Validator {

	private jakarta.validation.Validator validator;

	public Validator(jakarta.validation.Validator validator) {
		super();
		this.validator = validator;
	}

	public List<String> validateAffiliate(Affiliate affiliate) {
		var errors = validator.validate(affiliate);
		if (errors.isEmpty()) {
			return null;
		} else {
			return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
		}

	}
	public List<String> validateSpecialist(Specialist specialist) {
		var errors = validator.validate(specialist);
		if (errors.isEmpty()) {
			return null;
		} else {
			return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
		}
	}
	public List<String> validateSchedule(Schedule schedule) {
		var errors = validator.validate(schedule);
		if (errors.isEmpty()) {
			return null;
		}
		return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
	}
	public List<String> validateLocation(Location location) {
		var errors = validator.validate(location);
		if (errors.isEmpty()) {
			return null;
		}
		return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
	}

}
