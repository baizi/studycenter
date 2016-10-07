package com.study.view;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhangshuwen
 * Date is 2016/8/7.
 * Time is 17:11
 */
public class PersonView implements View {

    private String contentType;

    public PersonView(String contentType) {
        this.contentType = contentType;
//        ExceptionHandler
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String[]> param = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        for(String key : param.keySet()) {
            sb.append(key).append("=").append(param.get(key)[0]).append(";");
        }
        response.getOutputStream().write(sb.toString().getBytes());

//        response.setContentLength(sb.toString().getBytes().length);
//        response.getOutputStream().println("zhangshuwen");
    }
}
