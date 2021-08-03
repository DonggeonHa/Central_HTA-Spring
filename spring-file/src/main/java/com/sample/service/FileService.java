package com.sample.service;

import java.util.List;

import com.sample.vo.AttachmentFile;

public interface FileService {

	void insertNewAttachmentFile(AttachmentFile attachmentFile);
	List<AttachmentFile> getAttachmentFileList();
	AttachmentFile getAttachmentDetail(long fileNo);
	void updateAttachmentFile(AttachmentFile attachmentFile);
	void removeAttachmentFile(long fileNo);
	void removeFileItems(long fileNo, long[] itemNumbers);
}
