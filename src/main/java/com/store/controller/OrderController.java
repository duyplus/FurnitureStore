package com.store.controller;

import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

//	@Autowired
//	OrderRepository orderRepository;
//
//	@Autowired
//	HttpServletRequest request;

//	@RequestMapping("/cart/view")
//	public String cart() {
//		return "cart/view";
//	}
//
//	@RequestMapping("/cart/checkout")
//	public String checkout() {
//		if (!(request.isUserInRole("DIRE") || request.isUserInRole("STAF") || request.isUserInRole("CUST"))) {
//			return "redirect:/auth/login/form";
//		}
//		return "cart/checkout";
//	}
//
//	@RequestMapping("/order/list")
//	public String list(Model model, HttpServletRequest request) {
//		String username = request.getRemoteUser();
//		model.addAttribute("orders", orderRepository.findByUsername(username));
//		return "order/list";
//	}
//
//	@RequestMapping("/order/detail/{id}")
//	public String detail(@PathVariable("id") int id, Model model) {
//		model.addAttribute("order", orderRepository.findById(id));
//		return "order/detail";
//	}
}
