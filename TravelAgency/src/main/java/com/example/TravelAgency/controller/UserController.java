package com.example.TravelAgency.controller;

import com.example.TravelAgency.DataStore.UserAuthintication;
import com.example.TravelAgency.entities.User;
import com.example.TravelAgency.entities.UserAuthEntity;
import com.example.TravelAgency.services.LoginService;
import com.example.TravelAgency.services.CreateAccountService;
import com.example.TravelAgency.services.LogoutService;
import com.example.TravelAgency.services.ResetPasswordService;
import com.example.TravelAgency.DataStore.UserDatabase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private LoginService loginService;
    private CreateAccountService createAccountService;
    private LogoutService logoutService;
    private ResetPasswordService resetPasswordService;
    private UserDatabase userDatabase;
    private UserAuthintication userAuthintication;

    public UserController() {
        this.userDatabase = new UserDatabase();
        this.userAuthintication = new UserAuthintication();
        this.loginService = new LoginService(userDatabase, userAuthintication);
        this.createAccountService = new CreateAccountService(userDatabase);
        this.logoutService = new LogoutService(userAuthintication);
        this.resetPasswordService = new ResetPasswordService(userDatabase);
    }
    @PostMapping("/Login")

    public String login(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }
    @PostMapping("/createAccount")
    public void createAccount(@RequestParam String username,@RequestParam String password,@RequestParam(required = false) String email,@RequestParam String channel,@RequestParam(required = false) String phone ) {
        createAccountService.createAccount(username, password, email, phone, channel);
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String token) {
        System.out.println(userAuthintication);
        logoutService.logout(token);
    }
    @PostMapping("resetPassword")
    public void resetPassword(@RequestParam String username,@RequestParam String password,@RequestParam String newPassword) {
        resetPasswordService.resetPassword(username, password, newPassword);
    }
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userDatabase.getUsers();
    }
}
