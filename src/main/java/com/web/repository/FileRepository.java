package com.web.repository;

import com.web.domain.UploadFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<UploadFile, Integer> {
}
