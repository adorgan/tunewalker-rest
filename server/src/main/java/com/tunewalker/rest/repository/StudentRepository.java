package com.tunewalker.rest.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.tunewalker.rest.model.Student;
import java.util.List;
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByStudentNumber(long studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpaDesc();

}
