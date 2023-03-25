package com.tunewalker.rest.service;

import com.tunewalker.rest.model.Student;

import java.util.List;

/**
 * @author regcrix
 */
public interface StudentService {

    List<Student> findAll();

    Student findByStudentNumber(long studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpaDesc();

    Student saveOrUpdateStudent(Student student);

    void deleteStudentById(String id);

}
