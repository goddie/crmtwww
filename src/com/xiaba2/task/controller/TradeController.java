package com.xiaba2.task.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.task.domain.Trade;
import com.xiaba2.task.service.TradeService;
@Controller
@RequestMapping("/trade")
public class TradeController {
@Resource
private TradeService tradeService;
}