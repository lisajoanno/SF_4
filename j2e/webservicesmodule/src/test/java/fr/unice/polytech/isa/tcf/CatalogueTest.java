package fr.unice.polytech.isa.tcf;

import arquillian.AbstractTCFTest;
import fr.unice.polytech.isa.tcf.modules.business.entities.Cookies;
import fr.unice.polytech.isa.tcf.modules.webservicesmodule.CatalogueExploration;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Set;
import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class CatalogueTest extends AbstractTCFTest {

	@EJB
	CatalogueExploration catalogue;

	@Test
	public void preMadeRecipesAreAVailable() {
		Set<Cookies> premade = catalogue.listPreMadeRecipes();
		assertEquals(3, premade.size());
	}

	@Test
	public void regexpExploration() {
		assertEquals(0, catalogue.exploreCatalogue("unknown").size());
		assertEquals(2, catalogue.exploreCatalogue("CHOCO").size());
		assertEquals(1, catalogue.exploreCatalogue(Cookies.DARK_TEMPTATION.name()).size());
	}

}
