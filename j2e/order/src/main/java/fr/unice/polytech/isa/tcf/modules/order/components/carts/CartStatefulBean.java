package fr.unice.polytech.isa.tcf.modules.order.components.carts;

import fr.unice.polytech.isa.tcf.modules.business.entities.Customer;
import fr.unice.polytech.isa.tcf.modules.business.entities.Item;
import fr.unice.polytech.isa.tcf.modules.order.components.CartBean;

import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Stateful(name = "cart-stateful")
public class CartStatefulBean extends CartBean {

	private Map<Customer, Set<Item>> carts = new HashMap<>();

	@Override
	public boolean add(Customer c, Item item) {
		carts.put(c, updateCart(c, item));
		return true;
	}

	@Override
	public Set<Item> contents(Customer c) {
		return carts.getOrDefault(c, new HashSet<Item>());
	}

}
