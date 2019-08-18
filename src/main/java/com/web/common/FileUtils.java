package com.web.common;

import com.web.domain.BoardFile;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {

    public List<BoardFile> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
            return null;
        }

        List<BoardFile> fileList = new ArrayList<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();


        String cur = current.format(format);
        String path = "src/main/resources/static/upload/" + cur;
        String webPath = "/upload/" + cur;

        File file = new File(path);
        if(file.exists() == false){
            file.mkdirs();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String newFileName, originalFileExtension, contentType;

        while(iterator.hasNext()){
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
            for (MultipartFile multipartFile : list){
                if(multipartFile.isEmpty() == false){
                    contentType = multipartFile.getContentType();
                    if(ObjectUtils.isEmpty(contentType)){
                        break;
                    }
                    else{
                        if(contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        }
                        else if(contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        }
                        else if(contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        }
                        else if(contentType.contains("video/mp4")) {
                            originalFileExtension = ".mp4";
                        }
                        else if(contentType.contains("video/avi")) {
                            originalFileExtension = ".avi";
                        }
                        else{
                            break;
                        }
                    }

                    newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                    BoardFile boardFile = new BoardFile();
                    boardFile.setBoardIdx(boardIdx);
                    boardFile.setFileSize(multipartFile.getSize());
                    boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
                    boardFile.setStoredFilePath(webPath + "/" + newFileName);
                    fileList.add(boardFile);

                    file = new File(path + "/" + newFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return fileList;
    }

    public List<BoardFile> parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
            return null;
        }

        List<BoardFile> fileList = new ArrayList<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();

        String cur = current.format(format);

        String path = "src/main/resources/static/upload/" + cur;
        String webPath = "/upload/" + cur;
        File file = new File(path);
        if(file.exists() == false){
            file.mkdirs();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String newFileName, originalFileExtension, contentType;

        while(iterator.hasNext()){
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
            for (MultipartFile multipartFile : list){
                if(multipartFile.isEmpty() == false){
                    contentType = multipartFile.getContentType();
                    if(ObjectUtils.isEmpty(contentType)){
                        break;
                    }
                    else{
                        if(contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        }
                        else if(contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        }
                        else if(contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        }
                        else if(contentType.contains("video/mp4")) {
                            originalFileExtension = ".mp4";
                        }
                        else if(contentType.contains("video/avi")) {
                            originalFileExtension = ".avi";
                        }
                        else{
                            break;
                        }
                    }

                    newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                    BoardFile boardFile = new BoardFile();
                    boardFile.setFileSize(multipartFile.getSize());
                    boardFile.setOriginalFileName(multipartFile.getOriginalFilename());

                    boardFile.setStoredFilePath(webPath + "/" + newFileName);
                    boardFile.setCreatorId("admin");
                    fileList.add(boardFile);

                    file = new File(path + "/" + newFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return fileList;
    }
}



