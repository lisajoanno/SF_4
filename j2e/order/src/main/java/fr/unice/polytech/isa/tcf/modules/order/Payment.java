package fr.unice.polytech.isa.tcf.modules.order;


import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.entities.Item;
import fr.unice.polytech.isa.tcf.modules.business.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.modules.order.utils.BankAPI;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface Payment {

	String payOrder(Customer customer, Set<Item> items) throws PaymentException;

	void useBankReference(BankAPI bank);
}
