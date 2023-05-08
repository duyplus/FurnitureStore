package com.store.controller;

import com.store.entity.Category;
import com.store.entity.Product;
import com.store.repository.CategoryRepository;
import com.store.repository.ProductRepository;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping({"/", "index"})
    public String home(Model model, @RequestParam("cid") Optional<String> cid, @RequestParam(name = "page") Optional<Integer> page) {
        if (cid.isPresent()) {
            List<Product> list = productRepository.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            Page<Product> list = productService.findAll(page.orElse(0), 8);
            model.addAttribute("items", list);
            List<Product> list2 = productRepository.randProdInOd();
            model.addAttribute("items2", list2);
            List<Product> list3 = productRepository.randProdLessThanOrEqual();
            model.addAttribute("items3", list3);
            Page<Product> list4 = productService.findAll(page.orElse(0), 16);
            model.addAttribute("items4", list4);
            List<Category> list5 = categoryRepository.findAll();
            model.addAttribute("items5", list5);
        }
        return "index";
    }

    @GetMapping({"admin", "admin/index"})
    public String admin() {
        return "redirect:/admin/index.html";
    }

    @GetMapping({"shop", "shop/search"})
    public String shop(Model model, @RequestParam(name = "page") Optional<Integer> page,
                       @ModelAttribute("product") Product product, @RequestParam("name") Optional<String> name) {
        if(name.isPresent()) {
            List<Product> listSearch = productRepository.findByNameLike(name.orElse(""));
            model.addAttribute("items", listSearch);
            System.out.println("sd");
        }else {
            Page<Product> list = productService.findAll(page.orElse(0), 12);
            model.addAttribute("items", list);
            List<Category> list5 = categoryRepository.findAll();
            model.addAttribute("items5", list5);
        }
        return "shop";
    }

    @GetMapping("about")
    public String about(Model model) {
        List<Category> list5 = categoryRepository.findAll();
        model.addAttribute("items5", list5);
        return "about";
    }

    @GetMapping("contact")
    public String contact(Model model) {
        List<Category> list5 = categoryRepository.findAll();
        model.addAttribute("items5", list5);
        return "contact";
    }
}
