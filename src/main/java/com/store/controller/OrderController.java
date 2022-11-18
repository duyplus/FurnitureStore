package com.store.controller;

import com.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

	@Autowired
    OrderRepository orderRepository;

	@Autowired
    HttpServletRequest request;

	@RequestMapping("/cart/view")
	public String cart() {
		return "cart/view";
	}

	@RequestMapping("/cart/checkout")
	public String checkout() {
		if (!(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN"))) {
				return "redirect:/auth/login/form";
		}
		return "cart/checkout";
	}

	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderRepository.findByCustomer(username));
		return "order/list";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model) {
		model.addAttribute("order", orderRepository.findById(id));
		return "order/detail";
	}
}
