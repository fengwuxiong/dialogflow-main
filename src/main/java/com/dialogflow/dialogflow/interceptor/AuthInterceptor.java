package com.dialogflow.dialogflow.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Enumeration headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                System.out.println("Header key:" + key + " ,Header value:" + value);
            }
        }

        String auth = request.getHeader(AUTHORIZATION);
        if(auth != null && auth.length() > 6) {
            auth = auth.substring(6, auth.length());
            if(StringUtils.isEmpty(auth)) {
                return false;
            } else {
                try {
                    String decodedAuth = new String(new BASE64Decoder().decodeBuffer(auth), "UTF-8");
                    System.out.println("decodedAuth：" + decodedAuth);
                    if("fexiong:fexiong521911".equals(decodedAuth)){
                        return true;
                    } else {
                        System.out.println("Verification failed, no access permission.");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("Decoder Error ：" + e.getMessage());
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
