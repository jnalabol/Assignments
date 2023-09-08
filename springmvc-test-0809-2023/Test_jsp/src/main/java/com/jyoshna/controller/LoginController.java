package com.jyoshna.controller;

import com.jyoshna.dao.UserDao;
import com.jyoshna.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @GetMapping("/loginPage")
    public String getLoginPage() {
        return "Login";
    }

    @PostMapping("/validateUser")
    public String authenticateUser(@RequestParam("uname") String username, @RequestParam("pword") String password, Model model)  {
        String message = "Invalid Username / Password ... Try Again...!";
        User user = new User(username, password);
        if (userDao.validateUser(user)) {
            return "SearchFlight";
        }
        model.addAttribute("message", message);
        return "Display";
    }


}




