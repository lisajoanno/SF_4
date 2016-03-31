package fr.unice.polytech.isa.tcf.modules.kitchen;


import fr.unice.polytech.isa.tcf.modules.business.entities.Order;

import javax.ejb.Local;

@Local
public interface OrderProcessing {

	void process(Order order);

}
