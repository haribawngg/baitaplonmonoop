package com.school.grademanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/students")
    public String students() {
        return "students";
    }
    
    @GetMapping("/classes")
    public String classes() {
        return "classes";
    }
    
    @GetMapping("/subjects")
    public String subjects() {
        return "subjects";
    }
    
    @GetMapping("/teachers")
    public String teachers() {
        return "teachers";
    }
    
    @GetMapping("/grades")
    public String grades() {
        return "grades";
    }
}
