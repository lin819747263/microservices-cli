package com.linmsen.file.service;

import com.linmsen.core.vo.CommonResult;
import com.linmsen.core.vo.PageResult;
import com.linmsen.file.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {

    FileInfo upload(MultipartFile file ) throws Exception;

    CommonResult<List<FileInfo>> findList();

    void delete(String id);
}
