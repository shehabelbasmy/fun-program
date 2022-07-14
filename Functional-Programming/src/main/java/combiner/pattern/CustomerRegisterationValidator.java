package combiner.pattern;

import static combiner.pattern.ValidationResults.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public interface CustomerRegisterationValidator 
		extends Function<Customer, ValidationResults> {

	static CustomerRegisterationValidator isEmailValid() {
		return customer -> customer.getEmail().contains("@") ? 
				SUCCESS: EMAIL_NOT_VALID;
	}
	
	static CustomerRegisterationValidator isPhoneValid() {
		return customer -> customer.getPhoneNumber().startsWith("1") ? 
				SUCCESS : PHONE_NUMBER_NOT_VALID;
	}
	
	static CustomerRegisterationValidator isAdult() {
		return customer-> 
				Period.between(customer.getDob(), LocalDate.now()).getYears() > 18? 
				SUCCESS : IS_NOT_ADULT;
	}
	
	default CustomerRegisterationValidator and(CustomerRegisterationValidator other) {
		return customer->{
			ValidationResults result = this.apply(customer);
			return result.equals(SUCCESS) ? other.apply(customer) :result;
		};
	}
}
