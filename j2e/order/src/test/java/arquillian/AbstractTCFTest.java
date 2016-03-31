package arquillian;

import fr.unice.polytech.isa.tcf.modules.business.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.isa.tcf.modules.business.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.modules.customer.CustomerFinder;
import fr.unice.polytech.isa.tcf.modules.customer.CustomerRegistration;
import fr.unice.polytech.isa.tcf.modules.customer.components.CustomerRegistryBean;
import fr.unice.polytech.isa.tcf.modules.kitchen.OrderProcessing;
import fr.unice.polytech.isa.tcf.modules.kitchen.components.KitchenBean;
import fr.unice.polytech.isa.tcf.modules.order.CartModifier;
import fr.unice.polytech.isa.tcf.modules.order.Payment;
import fr.unice.polytech.isa.tcf.modules.order.components.CartBean;
import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.utils.Database;
import fr.unice.polytech.isa.tcf.modules.order.components.CashierBean;
import fr.unice.polytech.isa.tcf.modules.order.components.carts.CartStatefulBean;
import fr.unice.polytech.isa.tcf.modules.order.components.carts.CartStatelessBean;
import fr.unice.polytech.isa.tcf.modules.business.interceptors.CartCounter;
import fr.unice.polytech.isa.tcf.modules.business.interceptors.ItemVerifier;
import fr.unice.polytech.isa.tcf.modules.business.interceptors.Logger;
import fr.unice.polytech.isa.tcf.modules.order.utils.BankAPI;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractTCFTest {


	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Utils
				.addPackage(Database.class.getPackage())
                .addPackage(BankAPI.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage())
				// Components Interfaces
				.addPackage(CartModifier.class.getPackage())
                .addPackage(CustomerRegistration.class.getPackage())
                .addPackage(CustomerFinder.class.getPackage())
                .addPackage(Payment.class.getPackage())
                .addPackage(OrderProcessing.class.getPackage())
				// Cart components
				.addPackage(CartStatefulBean.class.getPackage())
				.addPackage(CartStatelessBean.class.getPackage())
				// Interceptors
				.addPackage(Logger.class.getPackage())
                .addPackage(ItemVerifier.class.getPackage())
                .addPackage(CartCounter.class.getPackage())
				// Exceptions
				.addPackage(AlreadyExistingCustomerException.class.getPackage())
                .addPackage(PaymentException.class.getPackage())
				// Components implementations
				.addPackage(CartBean.class.getPackage())
                .addPackage(CustomerRegistryBean.class.getPackage())
                .addPackage(CashierBean.class.getPackage())
                .addPackage(KitchenBean.class.getPackage());
	}

}
