package com.example.webapp.controller;


import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Incorrect form data!");
            return "create";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String editPage(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "id") long id) {
        if (bindingResult.hasErrors()) {
            System.out.println("Incorrect form data!");
            return "update";
        }
        System.out.println("Form data is Ok!");
        userService.editUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}