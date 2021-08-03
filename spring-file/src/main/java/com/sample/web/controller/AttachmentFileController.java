package com.sample.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sample.service.FileService;
import com.sample.vo.AttachmentFile;
import com.sample.vo.FileItem;
import com.sample.web.form.AttachmentFileForm;

@Controller
public class AttachmentFileController {

	@Autowired
	FileService fileService;
	
	@GetMapping({"/", "/home", "/list"})
	public String home(Model model) {
		List<AttachmentFile> files = fileService.getAttachmentFileList();
		model.addAttribute("files", files);
		
		return "list";
	}
	
	@GetMapping("/register")
	public String form() {
		return "registerform";
	}
	
	@PostMapping("/register")
	public String form(AttachmentFileForm form) throws IOException {
		AttachmentFile attachmentFile = new AttachmentFile();
		attachmentFile.setTitle(form.getTitle());
		attachmentFile.setWriter(form.getWriter());
		attachmentFile.setDescription(form.getDescription());
		
		List<MultipartFile> uploadFiles = form.getUpfiles();
		
		List<FileItem> fileItems = new ArrayList<>();
		for (MultipartFile uploadFile : uploadFiles) {
			if (!uploadFile.isEmpty()) {
				String filename = System.currentTimeMillis() + uploadFile.getOriginalFilename();
				String filetype = uploadFile.getContentType();
				long filesize = uploadFile.getSize();
				
				FileItem fileItem = new FileItem();
				fileItem.setFilename(filename);
				fileItem.setFiletype(filetype);
				fileItem.setFilesize(filesize);
				
				fileItems.add(fileItem);
				
				FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(new File("c:/upload", filename)));
			}
		}
		attachmentFile.setAmount(fileItems.size());
		attachmentFile.setFileItems(fileItems);
		
		fileService.insertNewAttachmentFile(attachmentFile);
		
		return "redirect:list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("no") long fileNo, Model model) {
		model.addAttribute("file", fileService.getAttachmentDetail(fileNo));
		return "detail";
	}
}
