package com.study.controller;

import com.study.model.Person;
import com.study.view.SelfStringView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangshuwen
 * Date is 2016/8/7.
 * Time is 1:27
 */
@Controller
@RequestMapping("/uc/api/springmvc")
public class SpringMvcViewController {

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public SelfStringView returnType(@RequestParam String name) {

        return new SelfStringView("Content-Type: text/plain; charset=utf-8");
    }

    @RequestMapping(value = "object", method = RequestMethod.GET)
    public Person objectReturnType() {

        return new Person("cuiweilong", 43);
    }
}
