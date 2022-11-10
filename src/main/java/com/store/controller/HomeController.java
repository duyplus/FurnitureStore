package com.store.controller;

import com.store.entity.Product;
import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping({"/", "/index"})
    public String home(Model model, @RequestParam("cid") Optional<String> cid) {
        List<Product> list;
        if (cid.isPresent()) {
            list = productRepository.findByCategoryId(cid.get());
        } else {
            list = productRepository.findAll();
        }
        model.addAttribute("items", list);
        return "index";
    }

    @RequestMapping({"/admin", "/admin/index"})
    public String admin(Model model) {
        return "redirect:/admin/index.html";
    }

    @RequestMapping("about")
    public String about() {
        return "about";
    }

    @RequestMapping("contact")
    public String contact() {
        return "contact";
    }
}
