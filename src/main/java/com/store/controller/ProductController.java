package com.store.controller;

import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
	@Autowired
	ProductRepository productRepository;

//	@RequestMapping("/product/detail/{id}")
//	public String detail(Model model, @PathVariable("id") Integer id) {
//		Product item = productRepository.findById(id).get();
//		model.addAttribute("item", item);
//		return "product/detail";
//	}
}
