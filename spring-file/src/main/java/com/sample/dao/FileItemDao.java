package com.sample.dao;

import java.util.List;

import com.sample.vo.FileItem;

public interface FileItemDao {

	void insertFileItem(FileItem fileItem);
	List<FileItem> getFileItemsByFileNo(long fileNo);
	FileItem getFileItemByNo(long itemNo);
	void deleteFileItem(long itemNo);
	void deleteFileItemsByFileNo(long fileNo);
}
