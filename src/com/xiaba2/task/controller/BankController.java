package com.xiaba2.task.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.task.domain.Bank;
import com.xiaba2.task.service.BankService;
@Controller
@RequestMapping("/bank")
public class BankController {
@Resource
private BankService bankService;
}