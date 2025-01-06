package br.tech.tickets.service;

import br.tech.tickets.domain.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.EnumMap;

@Service
public class MailService {

    private static final String TEMPLATE_NAME = "tickets.html";
    private static final String SPRING_LOOP_IMAGE = "templates/images/spring-loop.png";
    private static final String PNG_MIME = "image/png";
    private static final String MAIL_SUBJECT = "Seja bem vindo ao fastTickets";

    private final Environment env;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailService(Environment env, JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.env = env;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMailWithInline(User user) throws MessagingException, UnsupportedEncodingException {
        String confirmationUrl = "generated_confirmation_url";
        String mailFrom = env.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = env.getProperty("mail.from.name", "identify");

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true);

        email.setTo(user.getEmail());
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context context = new Context(LocaleContextHolder.getLocale());
        context.setVariable("email", user.getEmail());
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("springLogo", SPRING_LOOP_IMAGE);
        context.setVariable("url", confirmationUrl);

        final String html = templateEngine.process(TEMPLATE_NAME, context);
        email.setText(html, true);

        ClassPathResource classPathResource = new ClassPathResource(SPRING_LOOP_IMAGE);
        email.addInline("springLogo", classPathResource, SPRING_LOOP_IMAGE);
        mailSender.send(mimeMessage);
    }


}
