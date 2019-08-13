package com.web.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="upload_file")
@NoArgsConstructor
public class UploadFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="file_name")
    private String fileName;

    @Column(name="size")
    private Long size;

    @Column(name="mime_type")
    private String mimeType;

    @CreationTimestamp  // 입력시 시간 정보를 자동으로 입력해주는 어노테이션.
    @Column(name="insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;

    public UploadFile(String fileName, Long size, String mimeType) {
        this.fileName = fileName;
        this.size = size;
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "UploadFile [id=" + id + ", fileName=" + fileName + ", size=" + size + ", mimeType=" + mimeType
                + ", insertDate=" + insertDate + "]";
    }
}
