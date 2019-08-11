package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.User;
import com.web.service.BoardService;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {
    private final BoardService boardService;

    public MainController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @SocialUser User user) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "board/form";
    }

    @GetMapping("/photo_explore")
    public String photo_explore(@SocialUser User user) {
        return "/main/photo_explore";
    }

    @GetMapping("/photo_upload")
    public String photo_upload(@SocialUser User user) {
        return "/main/photo_upload";
    }

}





