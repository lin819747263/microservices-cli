package com.linmsen.file.service.impl;

import com.linmsen.core.vo.CommonResult;
import com.linmsen.file.model.FileInfo;
import com.linmsen.file.service.FileService;
import com.linmsen.file.utils.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class AbstractFileService implements FileService {

    private static final String FILE_SPLIT = ".";

    @Override
    public FileInfo upload(MultipartFile file) throws Exception {
        FileInfo fileInfo = FileUtil.getFileInfo(file);
        if (!fileInfo.getName().contains(FILE_SPLIT)) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        uploadFile(file, fileInfo);
        // 设置文件来源
        fileInfo.setSource(fileType());
        // 将文件信息保存到数据库
//        baseMapper.insert(fileInfo);

        return fileInfo;
    }

    /**
     * 删除文件
     * @param id 文件id
     */
    @Override
    public void delete(String id) {
//        FileInfo fileInfo = baseMapper.selectById(id);
//        if (fileInfo != null) {
//            baseMapper.deleteById(fileInfo.getId());
//            this.deleteFile(fileInfo);
//        }
    }

    /**
     * 删除文件资源
     *
     * @param fileInfo
     * @return
     */
    protected abstract boolean deleteFile(FileInfo fileInfo);

    protected abstract String fileType();

    protected abstract void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception;

    @Override
    public CommonResult<List<FileInfo>> findList(){
//        Page<FileInfo> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
//        List<FileInfo> list = baseMapper.findList(page, params);
//        return PageResult.<FileInfo>builder().data(list).code(0).count(page.getTotal()).build();
        return null;
    };
}