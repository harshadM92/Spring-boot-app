package com.facebook.common;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {


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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	ctx = applicationContext;
    }

}