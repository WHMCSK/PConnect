import {Component, OnInit} from '@angular/core';
import {Config} from "../app-config";
import {LoginService} from "./Login.service";
import {Router} from "@angular/router";
import {AlertEnum} from "../alert/alert";
import {TUser} from "../baseinfo/t-user";

@Component({
  selector: 'my-app',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit{
  msg:string;
  alert:AlertEnum = AlertEnum.Danger;

  app:any;
  username:string;
  password:string;
  constructor(private router:Router,
              private config:Config,
              private loginService:LoginService){
    this.app = config.appConfig;
  }
  ngOnInit(){
  } 
  msg_(msg_:string) {
    this.msg = msg_;
  }
  check() {
    let result = true;
    if (!this.username) {
      this.msg = '用户名不能为空';
      result = false;
      return result;
    }
    if (!this.password) {
      this.msg = '密码不能为空';
      result = false;
      return result;
    }
    return result;
  }
  login(){
    if(!this.check())return;
    this.loginService.login(this.username,this.password).subscribe(data =>{
      if(data.data && data.data.success && data.data.userDetails.username){
       // window.location.href = "/index/dashboard";  
        this.router.navigate(['/3545']); 
      }else{
        this.msg = "用户名密码错误";
      }
    });
  }
}
