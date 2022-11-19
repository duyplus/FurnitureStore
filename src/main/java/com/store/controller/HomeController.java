package com.store.controller;

import com.store.entity.Product;
import com.store.repository.ProductRepository;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "index"})
    public String home(Model model, @RequestParam("cid") Optional<String> cid, @RequestParam(name = "page") Optional<Integer> page) {
        if (cid.isPresent()) {
            List<Product> list = productRepository.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            Page<Product> list = productService.findAll(page.orElse(0), 8);
            model.addAttribute("items", list);
        }
        return "index";
    }

    @GetMapping({"admin", "admin/index"})
    public String admin() {
        return "redirect:/admin/index.html";
    }

    @GetMapping("shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }
}
