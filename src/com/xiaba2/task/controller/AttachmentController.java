package com.xiaba2.task.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.task.domain.Attachment;
import com.xiaba2.task.service.AttachmentService;
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
@Resource
private AttachmentService attachmentService;
}