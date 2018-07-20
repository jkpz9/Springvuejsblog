package itus._2014.king.springvuejsblog.controllers;

import itus._2014.king.springvuejsblog.entities.Role;
import itus._2014.king.springvuejsblog.entities.User;
import itus._2014.king.springvuejsblog.pojos.UserRegistration;
import itus._2014.king.springvuejsblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    private TokenStore tokenStore;

    @PostMapping("/register")
    public String register(@RequestBody UserRegistration userRegistration)
    {
        if(!userRegistration.getPasswordConfirmation().equals(userRegistration.getPassword()))
            return "Password confirm doesnt match!";

        if(this.userService.getUser(userRegistration.getUsername())!= null)
            return "The username is already existed!";

        Pattern pattern = Pattern.compile("^a-zA-Z0-9");

        if(pattern.matcher(userRegistration.getUsername()).find())
            return "No special characters are allowed in the username";

        this.userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(new Role("USER"),new Role("ACTUATOR"))));
        return "User created!";
    }

    @GetMapping(value="/users")
    public List<User> users()
    {
        return this.userService.getAllUser();
    }

    @GetMapping(value="/logout")
    public void logout(@RequestParam (value = "access_token") String accessToken)
    {
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @GetMapping(value = "/getUsername")
    public String getUsername()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
