package com.ramostear.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ramostear
 * @create-time 2019/3/3 0003-3:01
 * @modify by :
 * @since:
 */
@Controller
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("appName",appName);
        return "home";
    }
}
