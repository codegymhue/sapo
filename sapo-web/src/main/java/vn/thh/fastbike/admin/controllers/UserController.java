package vn.thh.fastbike.admin.controllers;

import org.springframework.web.bind.annotation.RestController;
import vn.thh.fastbike.service.UserService;

@RestController
public class UserController {
    public static void main(String[] args) {
        UserService userService = new UserService();
    }
}
