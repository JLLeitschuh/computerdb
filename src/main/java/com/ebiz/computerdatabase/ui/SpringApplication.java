package com.ebiz.computerdatabase.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ebiz on 07/06/17.
 */
public class SpringApplication {

   public static void main(String ... arg){

       ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/spring/applicationContext.xml");

   }
}
