package arquillian;

import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.utils.Database;
import fr.unice.polytech.isa.tcf.modules.kitchen.OrderProcessing;
import fr.unice.polytech.isa.tcf.modules.kitchen.Tracker;
import fr.unice.polytech.isa.tcf.modules.kitchen.components.KitchenBean;
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
				// Entities
				.addPackage(Customer.class.getPackage())
		        // Components Interfaces
                .addPackage(OrderProcessing.class.getPackage())
                .addPackage(Tracker.class.getPackage())
                // Components implementations
                .addPackage(KitchenBean.class.getPackage());
	}

}
