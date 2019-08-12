package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by KimYJ on 2017-09-13.
 */
@Controller
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        String email = null;
        if (session.getAttribute("user.email") != null){
            email = session.getAttribute("user.email").toString();
        }
        model.addAttribute("user", userRepository.findByEmail(email));
        return "register";
    }

    @GetMapping("/loginSuccess")
    public String loginComplete(@SocialUser User user)
    {
        return "redirect:/";
    }

    // -- 추가부분
    @RequestMapping(value = "/formLoginSuccess", method = RequestMethod.GET)
    public String formLoginComplete(HttpServletRequest request) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String username = ((UserDetails) principal).getUsername();

        User user = userRepository.findByEmail(username);
        HttpSession session = request.getSession();

        session.setAttribute("user", user);

        return "redirect:/";
    }
    // -- 추가부분 끝
}

