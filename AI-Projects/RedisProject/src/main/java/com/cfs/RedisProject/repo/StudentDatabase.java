package com.cfs.RedisProject.repo;

import com.cfs.RedisProject.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class StudentDatabase {

    private static final Logger log= LoggerFactory.getLogger(StudentDatabase.class);

    private final Map<Long, Student> database=new ConcurrentHashMap<>();

    public Student save(Student student) throws InterruptedException {
        log.info("Database: Saving student with id {}",student.getId());
        Thread.sleep(100);
        database.put(student.getId(),student);
        return student;
    }

    public List<Student> findAll() throws InterruptedException {
        log.info("DATABASE: Finding all Student");
        Thread.sleep(100);
        return new ArrayList<>(database.values());
    }

    public List<Student> findByEmail(String email) throws InterruptedException {
        log.info("DATABASE: Finding Student by email");
        Thread.sleep(100);
        return database.values().stream()
                .filter(s->email.equals(s.getEmail()))
                .collect(Collectors.toList());
    }

    public void deletedById(Long id) throws InterruptedException {
        log.info("DATABASE: deleting student by Id");
        Thread.sleep(100);
        database.remove(id);
    }

    public void deletedAll() throws InterruptedException {
        log.info("DATABASE: deleting All students");
        Thread.sleep(100);
        database.clear();
    }
}
