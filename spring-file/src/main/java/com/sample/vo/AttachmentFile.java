package com.sample.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentFile {

	long no;
	String title;
	String writer;
	String description;
	int amount;
	Date createdDate;
	List<FileItem> fileItems;
}
