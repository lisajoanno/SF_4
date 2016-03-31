package fr.unice.polytech.isa.tcf.modules.kitchen;

import fr.unice.polytech.isa.tcf.modules.business.entities.OrderStatus;
import fr.unice.polytech.isa.tcf.modules.business.exceptions.UnknownOrderId;

import javax.ejb.Local;

@Local
public interface Tracker {

	OrderStatus status(String orderId) throws UnknownOrderId;

}
