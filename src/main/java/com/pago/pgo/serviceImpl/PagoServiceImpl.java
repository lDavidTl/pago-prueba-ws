package com.pago.pgo.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pago.pgo.enums.EstatusOrder;
import com.pago.pgo.exception.BadRequestException;
import com.pago.pgo.model.Order;
import com.pago.pgo.repository.OrderRepository;
import com.pago.pgo.service.PagoService;

import jakarta.transaction.Transactional;

@Service
public class PagoServiceImpl implements PagoService{
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	@Transactional
	public String genetarePayOrder(Long orderId) {
		
		Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);
		
		try {
			Order order = getOrderById(orderId);
			order.setStatus(EstatusOrder.PAY);
			orderRepository.save(order);
			
			return "Your payment was made successfully, authorization number: " + generateAuthNumber(orderId);
		} catch (Exception e) {
			log.error("An error occurred while generating the payment: " + e.getMessage());
			throw new BadRequestException("An error occurred while generating the payment...");
			
		}
	}
	
	private Order getOrderById(Long orderId) {
	    return orderRepository.findById(orderId)
	                          .orElseThrow(() -> new BadRequestException("Error to find order"));
	}
	
	private String generateAuthNumber(Long orderId) {
		return String.format("AUTH%010d", orderId);
	}
}
