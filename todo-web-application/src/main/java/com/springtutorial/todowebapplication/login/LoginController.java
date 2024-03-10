package com.springtutorial.todowebapplication.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    @Autowired
    private LoginAuthenticationService loginAuthenticationService;
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String gotoLogin(){
        return "login";
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String gotoWelcome(@RequestParam String name,@RequestParam String password, ModelMap modelMap){
        if (loginAuthenticationService.authenticate(name,password)){
            modelMap.put("name",name);
            return "welcome";
        }
        modelMap.put("errorMessage","Invalid Credentials, Please try again!");
        return "login";
    }
}
