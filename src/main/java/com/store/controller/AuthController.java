package com.store.controller;

import com.store.entity.Customer;
import com.store.service.AuthenticationService;
import com.store.service.CustomerService;
import com.store.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    @Autowired
    CustomerService customerService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    MailerService mailer;

//    @CrossOrigin("*")
//    @ResponseBody
//    @RequestMapping("/rest/auth/authentication")
//    public Object getAuthentication(HttpSession session) {
//        return session.getAttribute("authentication");
//    }

    @RequestMapping("/auth/login/form")
    public String logInForm(Model model, @ModelAttribute("customer") Customer customer) {
        return "auth/login";
    }

    //
    @RequestMapping("/auth/login/success")
    public String logInSuccess(Model model, @ModelAttribute("customer") Customer customer) {
        model.addAttribute("message", "Logged in successfully");
        return "redirect:/";
    }

    @RequestMapping("/auth/login/error")
    public String logInError(Model model, @Validated @ModelAttribute("customer") Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Wrong login information!");
            return "auth/login";
        }
        return "auth/login";
    }

    @RequestMapping("/auth/unauthoried")
    public String unauthoried(Model model, @ModelAttribute("customer") Customer customer) {
        model.addAttribute("message", "You don't have access!");
        return "auth/login";
    }

    @GetMapping("/auth/register")
    public String signUpForm(Model model, @ModelAttribute("customer") Customer customer) {
        model.addAttribute("customer", new Customer());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String signUpSuccess(Model model, @Validated @ModelAttribute("customer") Customer customer, Errors error,
                                HttpServletResponse response) {
        if (error.hasErrors()) {
            model.addAttribute("message", "Please correct the error below!");
            return "auth/register";
        }
        customerService.create(customer);
        model.addAttribute("message", "New account registration successful!");
        response.addHeader("refresh", "2;url=/auth/login");
        return "auth/register";
    }

    @GetMapping("/auth/forgot-password")
    public String forgotPasswordForm(Model model) {
        return "auth/forgot-password";
    }

    @PostMapping("/auth/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) throws Exception {
        authenticationService.sendResetMail(email);
        model.addAttribute("message", "We have sent a reset password link to your email. "
                + "If you don't see the email, check your spam folder.");
        return "auth/forgot-password";
    }

    @GetMapping("/auth/reset-password")
    public String resetPasswordForm(@Param(value = "token") String token, Model model) {
        Customer customer = authenticationService.findByToken(token);
        model.addAttribute("token", token);
        if (customer == null) {
            model.addAttribute("message", "Invalid token!");
            return "redirect:/auth/login/form";
        }
        return "auth/reset-password";
    }

    @PostMapping("/auth/reset-password")
    public String processResetPassword(@RequestParam("token") String token, HttpServletResponse response, Model model) {
        Customer customer = authenticationService.findByToken(token);
        if (token == null) {
            model.addAttribute("message", "Invalid token!");
        } else {
            model.addAttribute("message", "You have successfully changed your password!");
            response.addHeader("refresh", "2;url=/auth/login/form");
        }
        return "auth/reset-password";
    }

//    @GetMapping("/auth/change-password")
//    public String changePasswordForm(Model model) {
//        return "auth/change-password";
//    }

//    @PostMapping("/auth/change-password")
//    public String processChangePassword(Model model, @RequestParam("username") String username,
//                                        @RequestParam("password") String newPassword) {
//        Customer account = customerService.findByUsername(username);
//        customerService.changePassword(account, newPassword);
//        model.addAttribute("message", "Change password successfully!");
//        return "auth/change-password";
//    }

//    // OAuth2
//    @RequestMapping("/oauth2/login/success")
//    public String oauth2(OAuth2AuthenticationToken oauth2) {
//        customerService.loginFromOAuth2(oauth2);
//        return "forward:/auth/login/success";
//    }
}
