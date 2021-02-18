package org.itstep.dnepr.controller;

import org.itstep.dnepr.model.User;
import org.itstep.dnepr.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class SecurityController {


    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, Model model) {
        System.out.println("Error = "+ error);
        if (Boolean.TRUE.equals(error)){
            model.addAttribute("error", true);
        }
        return "signin";
    }


    @GetMapping("/registration")
    public String reg() {
        return "signup";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login";
    }

//    @GetMapping("/role{user}")
//    public String role(@PathVariable User user){
//    }
}
