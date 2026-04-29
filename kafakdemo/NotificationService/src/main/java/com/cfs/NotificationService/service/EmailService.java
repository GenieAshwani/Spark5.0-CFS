package com.cfs.NotificationService.service;

import com.cfs.NotificationService.model.EnrollmentNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final boolean mailEnabled;
    private final String mailFrom;

    public EmailService(
            JavaMailSender mailSender,
            @Value("${app.mail.enabled:true}") boolean mailEnabled,
            @Value("${app.mail.from}") String mailFrom
    ) {
        this.mailSender = mailSender;
        this.mailEnabled = mailEnabled;
        this.mailFrom = mailFrom;
    }

    public void sendEnrollmentEmail(EnrollmentNotification notification) {
        if (!mailEnabled) {
            log.info("Mail disabled. Email skipped for {} and course {}", notification.email(), notification.courseTitle());
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(notification.email());
        message.setSubject("Course enrollment confirmed: " + notification.courseTitle());
        message.setText("""
                Hello %s,
                Your payment was successful and your enrollment is confirmed.

                Course: %s
                Payment Id: %s
                Order Id: %s

                Happy Learning!
                """.formatted(
                notification.studentName(),
                notification.courseTitle(),
                notification.razorpayPaymentId(),
                notification.razorpayOrderId()
        ));

        mailSender.send(message);
        log.info("Enrollment email sent to {}", notification.email());
    }
}
