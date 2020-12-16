package org.lanbo.controller;

import org.lanbo.model.User;
import org.lanbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
