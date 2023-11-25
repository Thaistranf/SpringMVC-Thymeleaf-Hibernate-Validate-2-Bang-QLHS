package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.controller;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Classroom;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.student.Student;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Subject;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.student.StudentForm;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.classroom.IClassroomService;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.student.IStudentService;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    // Dùng kiểu dữ liệu là "IStudentService" vì cấu hình @Bean trong AppConfiguration là "IStudentService"
    // thì mới lấy được dữ liệu trong vài trường hợp hãm loz
    private IStudentService studentService;

    @Autowired
    private IClassroomService classroomService;

    @Autowired
    private ISubjectService subjectService;

    @Value("${file-upload}")
    private String fileUpload;

    @ModelAttribute("classList")
    // Khai bao "classList" de lay ra ds classroom dung duoc o nhieu file.html khac nhau trong views,
    // ma ko can khoi tao nhieu lan o moi method create / update / delete ...
    public Iterable<Classroom> classrooms() {
        return classroomService.findAll();
    }

    @ModelAttribute("subjectList")
    public Iterable<Subject> subjects(){
        return subjectService.findAll();
    }

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("studentList", studentService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("student", new StudentForm());
        return "/student/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute StudentForm studentForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/student/create";
        } else {
            MultipartFile multipartFile = studentForm.getImage();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            Student student = new Student(studentForm.getId(), fileName, studentForm.getName(), studentForm.getAge(), studentForm.getAddress(), studentForm.getEmail(), studentForm.getClassroom(), studentForm.getSubjects());
            studentService.save(student);
            return ("redirect:/students");
        }
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/student/update");
        Student student = studentService.findById(id).get();
        modelAndView.addObject("studentUp", student);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute StudentForm studentForm) {
        Student student = studentService.findById(id).get();
        Student student1 = null;
        MultipartFile multipartFile = studentForm.getImage();

        if (!multipartFile.isEmpty()){
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            student1 = new Student(studentForm.getId(), fileName,studentForm.getName(), studentForm.getAge(), studentForm.getAddress(), studentForm.getEmail(), studentForm.getClassroom(), studentForm.getSubjects());
        } else {
            student1 = new Student(studentForm.getId(), student.getImage(), studentForm.getName(), studentForm.getAge(),studentForm.getAddress(), studentForm.getEmail(), studentForm.getClassroom(), studentForm.getSubjects());
        }

        studentService.save(student1);
        return ("redirect:/students");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.remove(id);
        return ("redirect:/students");
    }

    @GetMapping("/view/{id}")
    public String View(@PathVariable Long id, Model model) {
        model.addAttribute("studentView", studentService.findById(id).get());
        return "/student/view";
    }

    @PostMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Student> searchStudent = studentService.search(keyword);
        model.addAttribute("searchStudent", searchStudent);
        return "/student/list";
    }
}
