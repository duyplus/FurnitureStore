package com.store.service.impl;

import com.store.common.Link;
import com.store.dto.MailDTO;
import com.store.entity.Customer;
import com.store.repository.CustomerRepository;
import com.store.service.AuthenticationService;
import com.store.service.CustomerService;
import com.store.service.MailerService;
import lombok.extern.slf4j.Slf4j;
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
            String token = RandomString.make(20);
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
        String subject = "Y??u c???u ?????t l???i m???t kh???u";
        String button = "background-color:#783ecf;color:#fff;font-size:15px;padding:12px 10px;text-decoration:none;border-radius:3px;font-weight:bold";
        String body = "" +
                "<div style='width:50%;margin:0 auto'>" +
                "<div style='background-color:#f0f8ff;font-size:14px;padding:2em 5em'>" +
                "<img src='https://i.imgur.com/VtXfSgp.png' referrerpolicy='no-referrer'>" +
                "<div style='display:flex;border:1px;height:1px;background:lightblue'></div>" +
                "<p>Hi <b>" + username + "</b>,</p>" +
                "<p>Ch??ng t??i ???? nh???n ???????c y??u c???u ?????t l???i m???t kh???u cho t??i kho???n NghienDT ???????c li??n k???t v???i " + email + ". Ch??a c?? thay ?????i n??o ?????i v???i t??i kho???n c???a b???n.</p>" +
                "<p>B???n c?? th??? ?????t l???i m???t kh???u c???a m??nh b???ng c??ch nh???p v??o li??n k???t b??n d?????i:</p>" +
                "<p style='display:grid;margin:0 auto;text-align:center'><a href=\"" + url + "\" style='" + button + "'>?????t l???i m???t kh???u</a></p>" +
                "<p>N???u b???n kh??ng y??u c???u m???t kh???u m???i, vui l??ng cho ch??ng t??i bi???t ngay l???p t???c b???ng c??ch tr??? l???i email n??y.</p>" +
                "<p>B???n c?? th??? t??m th???y c??u tr??? l???i cho h???u h???t c??c c??u h???i v?? li??n h??? v???i ch??ng t??i t???i nghienecomm@gmail.com. Ch??ng t??i ??? ????y ????? gi??p b???n.</p>" +
                "<div style='display:flex;border:1px;height:1px;background:lightblue'></div>" +
                "<div style='margin-top:1em'>" +
                "<p>To?? nh?? Innovation l?? 24, Quang Trung, Qu???n 12, TP. H??? Ch?? Minh</p>" +
                "Copyright ?? 2022 <b>H Furniture</b>. All rights reserved." +
                "</div>" +
                "</div>" +
                "</div>";
        return new MailDTO(email, subject, body);
    }
}
