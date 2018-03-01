package com.zytx.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginHandler {

	@RequestMapping(method=RequestMethod.POST,value="/login")
	public String login(@RequestParam("username") String username,@RequestParam("password") String password,
						Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
			try {
				subject.login(usernamePasswordToken);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "redirect:/login.jsp";
			}
			
		}
		return "admin";
	}
	
}
