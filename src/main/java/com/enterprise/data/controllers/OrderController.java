package com.enterprise.data.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enterprise.data.entities.Order;
import com.enterprise.data.repositories.OrderRepository;

@Controller
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping(value = "/orders")
	public String orders(Model model) {

		model.addAttribute("orders", orderRepository.findAll());
		return "orders";
	}
	
	@RequestMapping(value = "/order")
    public Order ordersById(@RequestParam("id") UUID id){  	
     	return orderRepository.findOne(id);  	
    } 
}
