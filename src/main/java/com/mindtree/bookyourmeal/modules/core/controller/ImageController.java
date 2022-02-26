package com.mindtree.bookyourmeal.modules.core.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mindtree.bookyourmeal.exception.ServiceException;
import com.mindtree.bookyourmeal.modules.core.entity.Image;
import com.mindtree.bookyourmeal.modules.core.entity.UploadFileResponse;
import com.mindtree.bookyourmeal.modules.core.service.FilesInterface;

@RestController
@CrossOrigin
public class ImageController {
	@Autowired
	FilesInterface filesService;
	
	@PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){
        Image files;
	
			files = filesService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(files.getPictureId())
	                .toUriString();

	        return new UploadFileResponse(files.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
		
    }
	
	  @PostMapping("/uploadMultipleFiles")
	    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files1") MultipartFile[] files) {
	        return Arrays.asList(files)
	                .stream()
	                .map(file -> uploadFile(file))
	                .collect(Collectors.toList());
	    }

	    @GetMapping("/downloadFile/{fileId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws ServiceException {
	        // Load file from database
	        Image files = filesService.getFile(fileId);

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(files.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() + "\"")
	                .body(new ByteArrayResource(files.getData()));
	    }

		
}
