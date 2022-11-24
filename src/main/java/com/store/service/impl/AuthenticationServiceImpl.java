package com.store.service.impl;

import com.store.common.Link;
import com.store.dto.MailDTO;
import com.store.entity.Customer;
import com.store.repository.CustomerRepository;
import com.store.service.AuthenticationService;
import com.store.service.CustomerService;
import com.store.service.MailerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    MailerService mailer;

    @Override
    public Customer findByToken(String token) {
        Customer customer = customerService.findByToken(token);
        return customer != null ? Customer.builder().id(customer.getId()).token(customer.getToken()).build() : null;
    }

    @Override
    public boolean changePassword(Customer customer) {
        Customer customerDb = customerService.findById(customer.getId());
        customerDb.setPassword(customer.getPassword());
        customerDb.setToken(null);
        Customer updateCustomer = customerService.update(customerDb);
        return updateCustomer != null;
    }

    @Override
    public boolean sendResetMail(String email) {
        Customer customer = customerService.findByEmail(email);
        if (customer != null) {
            String token = RandomString.make(50);
            customer.setToken(token);
            MailDTO mail = getResetMail(email, token);
            mailer.queue(mail);
            customerService.update(customer);
            return true;
        }
        return false;
    }

    private MailDTO getResetMail(String email, String token) {
        Customer customer = customerService.findUsernameByEmail(email);
        String username = customer.getUsername();
        String link = Link.ClientLink.RESET_PASSWORD_URL;
        String url = String.format("%s?token=%s", link, token);
        String subject = "Yêu cầu đặt lại mật khẩu";
        String button = "background-color:#783ecf;color:#fff;font-size:15px;padding:12px 10px;text-decoration:none;border-radius:3px;font-weight:bold";
        String body = "" +
                "<div style='width:50%;margin:0 auto'>" +
                "<div style='background-color:#f0f8ff;font-size:14px;padding:2em 5em'>" +
                "<img src='https://i.imgur.com/VtXfSgp.png' referrerpolicy='no-referrer'>" +
                "<div style='display:flex;border:1px;height:1px;background:lightblue'></div>" +
                "<p>Hi <b>" + username + "</b>,</p>" +
                "<p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu cho tài khoản NghienDT được liên kết với " + email + ". Chưa có thay đổi nào đối với tài khoản của bạn.</p>" +
                "<p>Bạn có thể đặt lại mật khẩu của mình bằng cách nhấp vào liên kết bên dưới:</p>" +
                "<p style='display:grid;margin:0 auto;text-align:center'><a href=\"" + url + "\" style='" + button + "'>Đặt lại mật khẩu</a></p>" +
                "<p>Nếu bạn không yêu cầu mật khẩu mới, vui lòng cho chúng tôi biết ngay lập tức bằng cách trả lời email này.</p>" +
                "<p>Bạn có thể tìm thấy câu trả lời cho hầu hết các câu hỏi và liên hệ với chúng tôi tại nghienecomm@gmail.com. Chúng tôi ở đây để giúp bạn.</p>" +
                "<div style='display:flex;border:1px;height:1px;background:lightblue'></div>" +
                "<div style='margin-top:1em'>" +
                "<p>Toà nhà Innovation lô 24, Quang Trung, Quận 12, TP. Hồ Chí Minh</p>" +
                "Copyright © 2022 <b>H Furniture</b>. All rights reserved." +
                "</div>" +
                "</div>" +
                "</div>";
        return new MailDTO(email, subject, body);
    }
}
