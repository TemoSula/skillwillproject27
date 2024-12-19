package com.example.skillwillproject27.Controllers;

import com.example.skillwillproject27.Models.UserModel;
import com.example.skillwillproject27.Request.RegistrationRequest;
import com.example.skillwillproject27.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public String register(RegistrationRequest request)
    {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return userService.login(username,password);
    }

    @GetMapping("getusername")
    @Secured("ROLE_Admin")
    public String getusername()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @Secured("ROLE_Admin")
    @GetMapping("/Admin")
    public String AdminMethod()
    {
        return "Hello Admin";
    }
    @Secured("ROLE_User")
    @GetMapping("/User")
    public String UserMethod()
    {
        return "Hello User";
    }

}
