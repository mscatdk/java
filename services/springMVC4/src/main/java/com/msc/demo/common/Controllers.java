package com.msc.demo.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msc.demo.model.UIMessage;

public class Controllers {
	
	private static final String BOOTSTRAP_ALERT_DANGER = "alert-danger";
	
	private static final String BOOTSTRAP_ALERT_SUCCESS = "alert-success";
	
	private static final String BOOTSTRAP_ALERT_INFO = "alert-info";
	
	private static final String BOOTSTRAP_ALERT_WARNING = "alert-warning";
		
	/**
	 * Add an error message to the page model
	 * @param message
	 * @param redirectUrl
	 * @param redirect
	 * @return
	 */
	public static ModelAndView addErrorMessageAndRedirect(String message, String redirectUrl, RedirectAttributes redirect) {
		return addMessageAndRedirect(message, redirectUrl, redirect, BOOTSTRAP_ALERT_DANGER);
	}
	
	/**
	 * Add a success message to the page model
	 * @param message
	 * @param redirectUrl
	 * @param redirect
	 * @return
	 */
	public static ModelAndView addSuccessMessageAndRedirect(String message, String redirectUrl, RedirectAttributes redirect) {
		return addMessageAndRedirect(message, redirectUrl, redirect, BOOTSTRAP_ALERT_SUCCESS);
	}
	
	/**
	 * Add a info message to the page model
	 * @param message
	 * @param redirectUrl
	 * @param redirect
	 * @return
	 */
	public static ModelAndView addInfoMessageAndRedirect(String message, String redirectUrl, RedirectAttributes redirect) {
		return addMessageAndRedirect(message, redirectUrl, redirect, BOOTSTRAP_ALERT_INFO);
	}
	
	/**
	 * Add a warning message to the page model
	 * @param message
	 * @param redirectUrl
	 * @param redirect
	 * @return
	 */
	public static ModelAndView addWarningMessageAndRedirect(String message, String redirectUrl, RedirectAttributes redirect) {
		return addMessageAndRedirect(message, redirectUrl, redirect, BOOTSTRAP_ALERT_WARNING);
	}
	
	/**
	 * Add a generic message to the page model
	 * @param message
	 * @param redirectUrl
	 * @param redirect
	 * @param messageType
	 * @return
	 */
	private static ModelAndView addMessageAndRedirect(String message, String redirectUrl, RedirectAttributes redirect, String messageType) {
		List<UIMessage> messages = new ArrayList<>();
		messages.add(new UIMessage(message, messageType));
		
		redirect.addFlashAttribute("messages", messages);
		
		return new ModelAndView("redirect:" + redirectUrl);
	}
}
