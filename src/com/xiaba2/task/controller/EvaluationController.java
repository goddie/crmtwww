package com.xiaba2.task.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.task.domain.Evaluation;
import com.xiaba2.task.service.EvaluationService;
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
@Resource
private EvaluationService evaluationService;
}