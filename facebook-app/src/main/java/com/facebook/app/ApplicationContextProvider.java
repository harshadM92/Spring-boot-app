package com.facebook.app;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ApplicationContextProvider {


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ApplicationContextAware#
     * setApplicationContext(org.springframework.context.ApplicationContext)
     * 
     * Injected from the class "ApplicationContextProvider" which is
     * automatically
     * 
     * loaded during Spring-Initialization.
     * 
     * @param ctx
     * the new application context
     */
	private static ApplicationContext ctx;


    public static ApplicationContext getApplicationContext(){
    	return ctx;
    }

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		ctx=applicationContext;
	}
}
