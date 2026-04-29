package com.cfs.PaymentService.service;

import com.cfs.PaymentService.model.Course;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourseCatalogService {

    private final List<Course> courses= List.of(

            new Course("java-Spring","Java Spring boot","Build production ready REST APIs with spring boot",499),
            new Course("kafka-basics","kafka basics","Learn producer, consumer, topics",999),
            new Course("fullstack-web","Full stack web","HTML, CSS, JS, APIs and deployment",5999)

    );

    public List<Course> findAll()
    {
        return courses;
    }

    public Course findById(String id)
    {
        return courses.stream()
                .filter(c->c.id().equals(id))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Invalid course id: "+ id));
    }
}
