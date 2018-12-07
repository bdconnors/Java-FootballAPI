package main.java.businesslayer.controllers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ShutdownController.BASE_URL)
public class ShutdownController implements ApplicationContextAware {

        public static final String BASE_URL = "footballapi";
        private ApplicationContext context;

        @RequestMapping(value = "/shutdown", method = {RequestMethod.GET, RequestMethod.POST})
        public void shutdownContext() {
            ((ConfigurableApplicationContext) context).close();
        }

        @Override
        public void setApplicationContext(ApplicationContext ctx) throws BeansException {
            this.context = ctx;

        }
}

