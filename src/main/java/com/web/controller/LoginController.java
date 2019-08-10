package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import com.web.domain.enums.SocialType;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KimYJ on 2017-09-13.
 */
@Controller
public class LoginController  {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {   // 추가부분

        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginComplete(@SocialUser User user) {
        return "redirect:/";
    }

    // -- 추가부분
    @RequestMapping(value = "/formLoginSuccess", method = RequestMethod.POST)
    public String formLoginComplete( HttpServletRequest request) {

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

