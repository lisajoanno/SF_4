package fr.unice.polytech.isa.tcf.modules.order.components;

import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.entities.Item;
import fr.unice.polytech.isa.tcf.modules.business.entities.Order;
import fr.unice.polytech.isa.tcf.modules.business.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.tcf.modules.business.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.modules.business.utils.Database;
import fr.unice.polytech.isa.tcf.modules.kitchen.OrderProcessing;
import fr.unice.polytech.isa.tcf.modules.order.Payment;
import fr.unice.polytech.isa.tcf.modules.order.utils.BankAPI;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

@Stateless
public class CashierBean implements Payment {

	@EJB private OrderProcessing kitchen;
	@EJB private Database memory;

	private BankAPI bank;
	public void useBankReference(BankAPI bank) { this.bank = bank; }

	@Override
	public String payOrder(Customer customer, Set<Item> items) throws PaymentException {

		Order order = new Order(customer, items);
		double price = order.getPrice();

		boolean status = false;
		try { status = bank.performPayment(customer, price); }
		catch (ExternalPartnerException e) {
			throw new PaymentException(customer.getName(), price);
		}

		if (!status) {
			throw new PaymentException(customer.getName(), price);
		}

		customer.add(order);
		memory.getOrders().put(order.getId(),order);
		kitchen.process(order);

		return order.getId();
	}

	@PostConstruct
	private void initializeRestPartnership() throws IOException {
		Properties prop = new Properties();
		prop.load(this.getClass().getResourceAsStream("/bank.properties"));
		bank = new BankAPI(	prop.getProperty("bankHostName"),
							prop.getProperty("bankPortNumber"));
	}

}
