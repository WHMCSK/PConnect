package cn.bgenius.pconnect.gate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 金飞 on 2017/10/16.
 */
@RestController
public class PublicController {

    @RequestMapping("/")
    public String login(){

        return "PConnect Gate success";
    }
}
