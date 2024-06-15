package validator;

import java.util.List;

import dto.AffiliateDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Validator;

@ApplicationScoped
public class AffiliateValidator {

	private Validator validator;

	public AffiliateValidator(Validator validator) {
		super();
		this.validator = validator;
	}
	
	public List<String> validateAffiliate(AffiliateDTO affiliate) {
		var errors = validator.validate(affiliate);
		if (errors.isEmpty()) {
			return null;
		} else {
			return errors.stream().map(violation ->violation.getPropertyPath() + violation.getMessage()).toList();
		}
	}
	
}
