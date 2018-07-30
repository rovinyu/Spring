package com.rovin.mongo.service;

import com.rovin.mongo.domain.File;
import com.rovin.mongo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    public FileRepository fileRepository;

    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public void removeFile(String id) {
        fileRepository.deleteById(id);
    }

    public Optional<File> getFileById(String id) {
        return fileRepository.findById(id);
    }

    public List<File> listFilesByPage(int pageIndex, int pageSize) {

        Page<File> page = null;
        List<File> list = null;

        Sort sort = new Sort(Sort.Direction.DESC, "uploadDate");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        page = fileRepository.findAll(pageable);

        list = page.getContent();
        return list;
    }

}
