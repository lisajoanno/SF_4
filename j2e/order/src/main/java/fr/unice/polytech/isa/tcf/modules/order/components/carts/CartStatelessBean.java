package fr.unice.polytech.isa.tcf.modules.order.components.carts;

import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.entities.Item;
import fr.unice.polytech.isa.tcf.modules.business.utils.Database;
import fr.unice.polytech.isa.tcf.modules.order.components.CartBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;


@Stateless(name = "cart-stateless")
public class CartStatelessBean extends CartBean {

	@EJB
	private Database memory;

	@Override
	public boolean add(Customer c, Item item) {
		memory.getCarts().put(c, updateCart(c, item));
		return true;
	}

	@Override
	public Set<Item> contents(Customer c) {
		return memory.getCarts().getOrDefault(c, new HashSet<Item>());
	}

}
