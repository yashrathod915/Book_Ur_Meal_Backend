package com.mindtree.bookyourmeal.modules.core.dto;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Lob;

public class ImageDto {
	private String pictureId;
	private String fileName;
	private String fileType;
	@Lob
	private byte[] data;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public ImageDto() {
		super();
	}

	public ImageDto(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public ImageDto(String pictureId, String fileName, String fileType, byte[] data, LocalDateTime localDateTime) {
		super();
		this.pictureId = pictureId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.localDateTime = LocalDateTime.now();
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "ImageDto [pictureId=" + pictureId + ", fileName=" + fileName + ", fileType=" + fileType + ", data="
				+ Arrays.toString(data) + ", localDateTime=" + localDateTime + "]";
	}

}
