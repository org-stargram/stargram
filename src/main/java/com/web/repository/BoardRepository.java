package com.web.repository;

import com.web.domain.Board;
import com.web.domain.BoardFile;
import com.web.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {
    Board findByUser(User user);


    List<Board> findAllByOrderByBoardIdxDesc();


    @Query("SELECT file FROM BoardFile file WHERE board_idx = :boardIdx AND idx = :idx")
    BoardFile findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);

}
