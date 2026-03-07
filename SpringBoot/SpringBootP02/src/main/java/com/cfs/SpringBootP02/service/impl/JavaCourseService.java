package com.cfs.SpringBootP02.service.impl;

import com.cfs.SpringBootP02.service.CourseService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class JavaCourseService implements CourseService {
    @Override
    public void getCourseDetails() {
        System.out.println("Java Course started....");
    }
}
