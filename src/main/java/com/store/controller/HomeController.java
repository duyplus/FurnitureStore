package com.store.controller;

import com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping({"/", "index"})
    public String home() { //Model model, @RequestParam("cid") Optional<String> cid
//        List<Product> list;
//        if (cid.isPresent()) {
//            list = productRepository.findByCategoryId(cid.get());
//        } else {
//            list = productRepository.findAll();
//        }
//        model.addAttribute("items", list);
        return "index";
    }

    @GetMapping({"admin", "admin/index"})
    public String admin() {
        return "redirect:/admin/index.html";
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
