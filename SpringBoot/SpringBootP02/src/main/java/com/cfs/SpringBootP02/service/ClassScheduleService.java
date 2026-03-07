package com.cfs.SpringBootP02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClassScheduleService {

    //Field injection
    @Autowired
    private NotificationService notificationService;

    void sendClassNotification()
    {
        System.out.println("class scheduling...");
        notificationService.sendNotification();
    }

}
