package arquillian;

import fr.unice.polytech.isa.tcf.modules.business.utils.Database;
import fr.unice.polytech.isa.tcf.modules.webservicesmodule.CatalogueExploration;
import fr.unice.polytech.isa.tcf.modules.webservicesmodule.components.CatalogueBean;
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
				// Entities

				// Components Interfaces
                .addPackage(CatalogueExploration.class.getPackage())
				// Interceptors
				// Exceptions
				// Components implementations
                .addPackage(CatalogueBean.class.getPackage());
	}

}
