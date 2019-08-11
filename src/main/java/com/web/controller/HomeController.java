package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/home")
    public void home3(@SocialUser User user) {
    }


}
