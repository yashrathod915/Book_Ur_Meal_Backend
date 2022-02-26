package com.mindtree.bookyourmeal.modules.core.service;

import org.springframework.web.multipart.MultipartFile;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.entity.Image;

public interface FilesInterface {

	Image storeFile(MultipartFile file);

	Image getFile(String fileId) throws ServiceException;

}
