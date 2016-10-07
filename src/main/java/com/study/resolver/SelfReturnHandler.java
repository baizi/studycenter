package com.study.resolver;

import com.study.model.Person;
import com.study.view.SelfStringView;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangshuwen.
 * Date is 2016/8/7.
 * Time is 1:43
 */
@Component
public class SelfReturnHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {

        if(Person.class.equals(returnType.getParameterType()) )
            return true;

        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
//        if(returnValue instanceof Person) {
//            mavContainer.setViewName("helloword");
//        }
    }
}
