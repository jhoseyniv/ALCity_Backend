package com.alcity.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Tag(name = "Home Page of backend", description = "Home Page of backend")
@CrossOrigin(origins = "*" ,maxAge = 3600)

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/index")
    public String   welcome() {
        return "redirect:/index.html";
    }



}
