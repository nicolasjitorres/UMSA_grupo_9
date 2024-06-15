package validator;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Validator;
import model.Schedule;

@ApplicationScoped
public class ScheduleValidator {

	private Validator validator;

	public ScheduleValidator(Validator validator) {
		super();
		this.validator = validator;
	}
	
	public List<String> validateSchedule(Schedule schedule) {
		var errors = validator.validate(schedule);
		if (errors.isEmpty()) {
			return null;
		} else {
			return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
		}
	}
	
}
