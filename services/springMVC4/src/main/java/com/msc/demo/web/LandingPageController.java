package com.msc.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msc.demo.common.Config;

@Controller
public class LandingPageController {

	@RequestMapping(value = Config.LANDING_PAGE_URL, method = RequestMethod.GET)
	public ModelAndView customerSearchGet(ModelAndView model) {
		model.setViewName(Config.LANDING_PAGE_VIEW);
		
		return model;
	}
	
}
