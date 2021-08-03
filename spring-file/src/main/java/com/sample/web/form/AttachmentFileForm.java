package com.sample.web.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentFileForm {

	private String title;
	private String writer;
	private String description;
	private List<MultipartFile> upfiles;
}
