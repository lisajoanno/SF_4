package fr.unice.polytech.isa.tcf.modules.kitchen.components;


import fr.unice.polytech.isa.tcf.modules.business.entities.Order;
import fr.unice.polytech.isa.tcf.modules.business.entities.OrderStatus;
import fr.unice.polytech.isa.tcf.modules.business.exceptions.UnknownOrderId;
import fr.unice.polytech.isa.tcf.modules.business.utils.Database;
import fr.unice.polytech.isa.tcf.modules.kitchen.OrderProcessing;
import fr.unice.polytech.isa.tcf.modules.kitchen.Tracker;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class KitchenBean implements OrderProcessing, Tracker {

	@EJB private Database memory;

	@Override
	public void process(Order order) {
		if (order.getCustomer().getName().contains("p")) {
			order.setStatus(OrderStatus.IN_PROGRESS);
		} else if (order.getCustomer().getName().contains("r")) {
			order.setStatus(OrderStatus.READY);
		}
		memory.getOrders().put(order.getId(), order);
	}

	@Override
	public OrderStatus status(String orderId) throws UnknownOrderId {
		Order order = memory.getOrders().get(orderId);
		if (order == null)
			throw new UnknownOrderId(orderId);
		return  order.getStatus();
	}
}
