package com.web.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@Entity
@Table(name="board_file")
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable=false)
    private int boardIdx;

    @Column(nullable=false)
    private String originalFileName;

    @Column(nullable=false)
    private String storedFilePath;

    @Column(nullable=false)
    private long fileSize;

    @Column(nullable=false)
    private String creatorId;

    @Column(nullable=false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId;

    private LocalDateTime updatedDatetime;
}
