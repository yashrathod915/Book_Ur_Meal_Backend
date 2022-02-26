package com.mindtree.bookyourmeal.modules.core.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.exception.MyFileNotFoundException;
import com.mindtree.bookyourmeal.modules.core.repository.ImageRepository;
import com.mindtree.bookyourmeal.modules.core.service.FilesInterface;

@Service
public class FilesImpl implements FilesInterface{
	@Autowired
	ImageRepository filesRepo;
	@Override
	public Image storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
            Image files = null;
			try {
				files = new Image(fileName, file.getContentType(), file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

            return filesRepo.save(files);
		
	}
	
	public Image getFile(String fileId) throws ServiceException {
        return filesRepo.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}




	