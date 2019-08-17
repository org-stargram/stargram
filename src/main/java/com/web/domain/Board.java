package com.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by KimYJ on 2017-07-12.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="board")
public class Board implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardIdx;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(nullable=false)
    private int hitCnt = 0;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDatetime;

    @Column
    private String creatorId;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedDatetime;

    @Column
    private String updaterId;

    @OneToOne(fetch= FetchType.LAZY)
    private User user;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="boardIdx")
    private List<BoardFile> fileList;

    @Builder
    public Board(String title, String contents, LocalDateTime createdDatetime,
                 String creatorId, LocalDateTime updatedDatetime, String updaterId,
                 User user, List<BoardFile> fileList) {
        this.title = title;
        this.contents = contents;
        this.createdDatetime = createdDatetime;
        this.creatorId = creatorId;
        this.updatedDatetime = updatedDatetime;
        this.updaterId = updaterId;
        this.user = user;
        this.fileList = fileList;
    }



    public void setCreatedDateNow() {
        this.createdDatetime = LocalDateTime.now();
    }

    public void update(Board board) {
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.updatedDatetime = LocalDateTime.now();
    }

}
