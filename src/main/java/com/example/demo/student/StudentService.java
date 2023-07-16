package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
//    return List.of(new Student(
//                1l,
//                        "Ramiro",
//                        "ramiro@gmail.com",
//                LocalDate.of(1995, Month.MAY,9),
//                21
//                        ));
    }
    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);

    }
    @Transactional
    public void updateStudent(Long studentId,String name,String email){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists."));

        if(name != null && name.length() > 0  && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }

    }
}
