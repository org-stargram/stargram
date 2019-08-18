package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.Board;
import com.web.domain.User;
import com.web.service.BoardService;
import com.web.service.BoardServiceIml;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardServiceIml boardServiceIml;

    @RequestMapping(value="/photo_my_profile", method= RequestMethod.GET)
    public ModelAndView photo_profile(@SocialUser User user) throws Exception {
        ModelAndView mv = new ModelAndView("main/photo_my_profile");

        List<Board> list = boardService.selectBoardList();
        mv.addObject("list", list);

        return mv;
    }

}





