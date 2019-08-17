package com.web.service;

import com.web.domain.Board;
import com.web.domain.BoardFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {


    List<Board> selectBoardList() throws Exception;

    void saveBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    Board selectBoardDetail(int boardIdx) throws Exception;

    void deleteBoard(int boardIdx);

    BoardFile selectBoardFileInformation(int boardIdx, int idx) throws Exception;

}
