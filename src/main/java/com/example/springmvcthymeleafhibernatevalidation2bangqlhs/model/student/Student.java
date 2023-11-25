package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.student;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Classroom;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Subject;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    @NotEmpty(message = "Your name cannot be empty.")    //Ten bat buoc phai nhap vao (không để trống)
    @Size(min = 2, max = 30, message = "Your name is from 2 to 30 characters")    //độ dài tối thiểu 2 ký tự, tối đa 30 ký tự, kieu String dung @Size
    private String name;

//    @NotEmpty(message = "Your age cannot be null.")
    @Min(value = 5, message = "Min age is 5")                //Tuoi co giá trị nhỏ nhất là 5, kieu number dung @Min / @Max
    private int age;
    private String address;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "test@gmail.com")
    private String email;

    @ManyToOne
    private Classroom classroom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    public Student() {
    }

    public Student(Long id, String image, String name, int age, String address, String email, Classroom classroom, Set<Subject> subjects) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
        this.classroom = classroom;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
