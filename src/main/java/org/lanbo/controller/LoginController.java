package org.lanbo.controller;

import org.lanbo.model.User;
import org.lanbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "service/login";
    }

    @GetMapping(value = "/admin")
    public String helloAdmin(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello, dear administrator of this resource!");
        messages.add("I'm is your personal helper!");
        messages.add("Can I help you?)");
        model.addAttribute("messages", messages);
        return "admin/hello-admin";
    }

    @GetMapping(value = "/user/{id}")
    public String helloUser(Model model, @PathVariable("id") Long id) {
        User user = userService.show(id);
        model.addAttribute("user", user);
        return "user/show-user";
    }
}
