package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import com.web.service.BoardServiceIml;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

    @Autowired
    private BoardServiceIml boardServiceIml;

    @GetMapping("/photo_profile")
    public String photo_profile(@SocialUser User user) {
        return "main/photo_profile";
    }

}





