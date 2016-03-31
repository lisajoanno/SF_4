package fr.unice.polytech.isa.tcf.modules.customer.components;

import fr.unice.polytech.isa.tcf.modules.business.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.isa.tcf.modules.customer.CustomerFinder;
import fr.unice.polytech.isa.tcf.modules.customer.CustomerRegistration;
import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.utils.Database;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;


@Stateless
public class CustomerRegistryBean
		implements CustomerRegistration, CustomerFinder {

	@EJB
	private Database memory;

	/******************************************
	 ** Customer Registration implementation **
	 ******************************************/

	@Override
	public void register(String name, String creditCard)
			throws AlreadyExistingCustomerException {
	 	if(findByName(name).isPresent())
			throw new AlreadyExistingCustomerException(name);
		memory.getCustomers().put(name, new Customer(name, creditCard));
	}


	/************************************
	 ** Customer Finder implementation **
	 ************************************/

	@Override
	public Optional<Customer> findByName(String name) {
		if (memory.getCustomers().containsKey(name))
			return Optional.of(memory.getCustomers().get(name));
		else
			return Optional.empty();
	}

}

