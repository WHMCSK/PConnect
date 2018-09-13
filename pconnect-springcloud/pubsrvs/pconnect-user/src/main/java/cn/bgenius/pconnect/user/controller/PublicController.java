package cn.bgenius.pconnect.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fier on 2017/11/28.
 */
@RestController
public class PublicController {
    @GetMapping("")
    public String home(){
        return "PConnect User";
    }
}
