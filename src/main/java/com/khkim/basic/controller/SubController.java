package com.khkim.basic.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub")
public class SubController {
     @GetMapping("/method1")
     public String method1(){
          return "/sub/method1입니다";
     }

     @PostMapping("")
     public String postMethod(){
          return "This is Post Method";
     }

     @PutMapping("")
     public String putMethod(){
          return "put methid";
     }

     @DeleteMapping("/method1")
     public String DeleteMethod(){
          return "This is Delete Method";
     }
     

    @GetMapping("path-variable/{variable}")
    public String getPathVarible(
     @PathVariable("variable") Integer variable
     ){
     return "Parameter value : " + variable;          
    }

    @GetMapping("/parameter")
    public String getParameter(
        @RequestParam("name") String name,
        @RequestParam("age") Integer age
    ){
        return "name: " + name + ", age : " + age;   

    }  

  
//   
       
}
