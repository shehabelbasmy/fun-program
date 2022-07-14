package combiner.pattern;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Customer customer=new Customer(
			"shehab",
			"shehabelbasmy123@gmail.com",
			"01024271863",
			LocalDate.of(1998, 11, 28)
		);
		
		ValidationResults result = CustomerRegisterationValidator
				.isAdult()
				.and(CustomerRegisterationValidator.isEmailValid())
				.and(CustomerRegisterationValidator.isPhoneValid())
				.apply(customer);
		
		System.out.println(result);
		
	}

}
