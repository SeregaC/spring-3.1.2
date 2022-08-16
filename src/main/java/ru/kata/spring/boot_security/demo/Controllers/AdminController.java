package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Админ панель
     *
     * @param model
     * @return
     */
    @GetMapping()
    public String userList(Model model, Principal principal) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("admin", userService.findUserByName(principal.getName()));
        return "/admin";
    }

    /**
     * Удаления пользователя из админ панели.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    /**
     * Создание пользователя отображение формы.
     *
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String creatUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.listRoles());

        return "/create";
    }

    /**
     * Создание пользователя пост запрос.
     *
     * @param user
     * @return
     */
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);
        return "redirect:/admin";
    }

    /**
     * Обновление пользователя, отображение формы.
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}/update")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleService.listRoles());
        return "/update";

    }

    /**
     * Обновление пользователя, пост запрос.
     *
     * @param user
     * @return
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {

        userService.updateUser(user);
        return "redirect:/admin";
    }


}
