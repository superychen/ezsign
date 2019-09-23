package com.ezcsign.utile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * describe:
 *
 * @author donting
 * @date 2019/08/01
 */
public class Session {
   public static Logger logger = LoggerFactory.getLogger(Session.class);
    public static int getUserType() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        logger.info(attributes.getRequest().getRequestURI()+"---"+ session.getId());
        return 2;
    }

    public static String getUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        logger.info(attributes.getRequest().getRequestURI()+"---"+ session.getId());
        return "1c02532dee65e9f5425e84afa6a48ab8";
    }
}
