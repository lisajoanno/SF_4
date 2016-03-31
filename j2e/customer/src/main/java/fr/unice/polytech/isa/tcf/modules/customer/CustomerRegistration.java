package fr.unice.polytech.isa.tcf.modules.customer;



import fr.unice.polytech.isa.tcf.modules.business.exceptions.AlreadyExistingCustomerException;

import javax.ejb.Local;

@Local
public interface CustomerRegistration {

	void register(String name, String creditCard)
			throws AlreadyExistingCustomerException;

}

