package com.cfs.SpringBootP02.service.impl;

import com.cfs.SpringBootP02.service.CourseService;
import org.springframework.stereotype.Service;


@Service
public class SpringBootCourseService implements CourseService {
    @Override
    public void getCourseDetails() {
        System.out.println("SB Course started....");
    }
}
