package com.study.view;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhangshuwen
 * Date is 2016/8/7.
 * Time is 1:36
 */
public class SelfStringView implements View {

    private String contentType;

    public SelfStringView(String contentType) {
        this.contentType = contentType;
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
