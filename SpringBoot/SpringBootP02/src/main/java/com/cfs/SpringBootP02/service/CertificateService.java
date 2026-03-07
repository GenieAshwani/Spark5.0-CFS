package com.cfs.SpringBootP02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    private NotificationService notificationService;

    //setter dependency injection
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void sendCertificate()
    {
        System.out.println("certificate is ready...");
        notificationService.sendNotification();
    }
}
