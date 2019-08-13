package com.web.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FileUploadResponse {

    private String fileName;
    private String fileDownLoadUri;
    private String fileType;
    private long size;

    public FileUploadResponse(String fileName, String fileDownLoadUri, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownLoadUri = fileDownLoadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
