package com.tunewalker.rest.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.tunewalker.rest.model.MailHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${application.sendgrid.key}")
    private String key;

    public String sendEmail(MailHelper email) throws IOException {
        Email from = new Email(email.getFrom());
        Email to = new Email(email.getTo());
        Content content = new Content("text/html", email.getBody());
        Mail mail = new Mail(from, email.getSubject(), to, content);
        SendGrid sg = new SendGrid(key);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }

}
