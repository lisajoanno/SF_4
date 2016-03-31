package integration;


import arquillian.AbstractTCFTest;
import fr.unice.polytech.isa.tcf.modules.customer.CustomerFinder;
import fr.unice.polytech.isa.tcf.modules.customer.CustomerRegistration;
import fr.unice.polytech.isa.tcf.modules.business.entities.Cookies;
import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.entities.Item;
import fr.unice.polytech.isa.tcf.modules.business.entities.Order;
import fr.unice.polytech.isa.tcf.modules.order.Payment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class PaymentIntegrationTest extends AbstractTCFTest {

	@EJB private Payment cashier;
	@EJB private CustomerFinder finder;
	@EJB private CustomerRegistration registration;

	private Set<Item> items;

	@Before
	public void setUpContext() {
		memory.flush();
		items = new HashSet<>();
		items.add(new Item(Cookies.CHOCOLALALA, 3));
		items.add(new Item(Cookies.DARK_TEMPTATION, 2));
	}

	@Test
	public void integrationBetweenCustomersAndOrders() throws Exception {
		registration.register("john", "1234-896983");
		Customer retrieved = finder.findByName("john").get();
		assertTrue(retrieved.getOrders().isEmpty());
		String id = cashier.payOrder(retrieved, items);
		Order order = memory.getOrders().get(id);
		assertTrue(retrieved.getOrders().contains(order));
	}


}
