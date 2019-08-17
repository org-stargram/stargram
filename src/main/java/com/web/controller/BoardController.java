package com.web.controller;

import com.web.domain.Board;
import com.web.domain.BoardFile;
import com.web.domain.User;
import com.web.service.BoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;


@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value="/jpa/board", method= RequestMethod.GET)
    public ModelAndView openBoardList(ModelMap model) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardList");

        List<Board> list = boardService.selectBoardList();
        mv.addObject("list", list);

        return mv;
    }

    @RequestMapping(value="/jpa/board/write", method=RequestMethod.GET)
    public String openBoardWrite() throws Exception{
        return "/board/jpaBoardWrite";
    }

    @RequestMapping(value="/board/write", method=RequestMethod.POST)
    public String writeBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest,
                             User user) throws Exception{
        boardService.saveBoard(board, multipartHttpServletRequest, user);
        return "redirect:/main/photo_profile";
    }

    @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");

        Board board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);

        return mv;
    }

    @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.PUT)
    public String updateBoard(Board board, User user) throws Exception{
        boardService.saveBoard(board, null, user);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception{
        boardService.deleteBoard(boardIdx);
        return "redirect:/jpa/board";
    }

    @RequestMapping(value="/jpa/board/file", method=RequestMethod.GET)
    public void downloadBoardFile(int boardIdx, int idx, HttpServletResponse response) throws Exception{
        BoardFile file = boardService.selectBoardFileInformation(boardIdx, idx);

        byte[] files = FileUtils.readFileToByteArray(new File(file.getStoredFilePath()));

        response.setContentType("application/octet-stream");
        response.setContentLength(files.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getOriginalFileName(),"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(files);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}

