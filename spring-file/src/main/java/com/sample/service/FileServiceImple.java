package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.AttachmentFileDao;
import com.sample.dao.FileItemDao;
import com.sample.vo.AttachmentFile;
import com.sample.vo.FileItem;

@Service
@Transactional
public class FileServiceImple implements FileService {

	@Autowired
	AttachmentFileDao attachmentFileDao;
	
	@Autowired
	FileItemDao fileItemDao;
	
	@Override
	public void insertNewAttachmentFile(AttachmentFile attachmentFile) {
		attachmentFileDao.insertAttachmentFile(attachmentFile);
		List<FileItem> items = attachmentFile.getFileItems();
		for (FileItem item : items) {
			item.setFileNo(attachmentFile.getNo());
			fileItemDao.insertFileItem(item);
		}
	}
	
	@Override
	public List<AttachmentFile> getAttachmentFileList() {
		return attachmentFileDao.getAllAttachmentFiles();
	}
	
	@Override
	public AttachmentFile getAttachmentDetail(long fileNo) {
		AttachmentFile attachmentFile = attachmentFileDao.getAttachmentFile(fileNo);
		List<FileItem> fileItems = fileItemDao.getFileItemsByFileNo(fileNo);
		attachmentFile.setFileItems(fileItems);
		
		return attachmentFile;
	}
	
	@Override
	public void updateAttachmentFile(AttachmentFile attachmentFile) {
		fileItemDao.deleteFileItemsByFileNo(attachmentFile.getNo());
		attachmentFileDao.updateAttachmentFile(attachmentFile);
		
		List<FileItem> items = attachmentFile.getFileItems();
		for (FileItem item : items) {
			item.setFileNo(attachmentFile.getNo());
			fileItemDao.insertFileItem(item);
		}
	}
	
	@Override
	public void removeAttachmentFile(long fileNo) {
		fileItemDao.deleteFileItemsByFileNo(fileNo);
		attachmentFileDao.deleteAttachmentFile(fileNo);		
	}
	
	@Override
	public void removeFileItems(long fileNo, long[] itemNumbers) {
		for (long itemNo : itemNumbers) {
			fileItemDao.deleteFileItem(itemNo);
		}
		
		AttachmentFile attachmentFile = attachmentFileDao.getAttachmentFile(fileNo);
		attachmentFile.setAmount(attachmentFile.getAmount() - itemNumbers.length);
		attachmentFileDao.updateAttachmentFile(attachmentFile);
	}
}
