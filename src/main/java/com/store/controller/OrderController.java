package com.store.controller;

import com.store.repository.OrderRepository;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/cart/view")
    public String cart() {
        return "cart/view";
    }

    @RequestMapping("/cart/checkout")
    public String checkout() {
//        if (!(request.isUserInRole("CUST"))) {
//            return "redirect:/auth/login";
//        }
        return "cart/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderRepository.findByCustomer(username));
        return "order/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/detail";
    }

    @GetMapping("/cart/wishlist")
    public String wishlist() {
        return "cart/wishlist";
    }
}
