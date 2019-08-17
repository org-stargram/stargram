package com.web.service;

import com.web.common.FileUtils;
import com.web.domain.Board;
import com.web.domain.BoardFile;
import com.web.domain.User;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

/**
 * Created by KimYJ on 2017-07-13.
 */

@Service
public class BoardServiceIml implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<Board> selectBoardList() throws Exception {
        return boardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest, User user) throws Exception {
//        board.setCreatorId("admin");

        board.setUser(user);
        List<BoardFile> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(list) == false){
            board.setFileList(list);
        }
        boardRepository.save(board);
    }

    @Override
    public Board selectBoardDetail(int boardIdx) throws Exception{
        Optional<Board> optional = boardRepository.findById(boardIdx);
        if(optional.isPresent()){
            Board board = optional.get();
            board.setHitCnt(board.getHitCnt() + 1);
            boardRepository.save(board);

            return board;
        }
        else {
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(int boardIdx) {
        boardRepository.deleteById(boardIdx);
    }

    @Override
    public BoardFile selectBoardFileInformation(int boardIdx, int idx) throws Exception {
        BoardFile boardFile = boardRepository.findBoardFile(boardIdx, idx);
        return boardFile;
    }
}
