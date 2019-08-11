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
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "board/form";
    }

    @GetMapping("/home")
    public String home(@PageableDefault Pageable pageable, @SocialUser User user, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "home";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, @SocialUser User user, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }

    // -- 추가부분

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public String list2(@PageableDefault Pageable pageable, User user, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }
    // -- 추가부분 끝
}





