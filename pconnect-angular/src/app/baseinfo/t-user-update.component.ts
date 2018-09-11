import { Location }               from '@angular/common';
import 'rxjs/add/operator/switchMap';
import { ActivatedRoute, Params } from '@angular/router';
import {Component, OnInit, enableProdMode}      from '@angular/core';
import {TUser} from "./t-user";
import {TUserService} from "./t-user.service"; 
enableProdMode();
@Component({
  templateUrl: './t-user-update.component.html',
})
export class TUserUpdateComponent implements OnInit {
  msg:string;
  tUser:any = new TUser();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '用户管理';
  constructor(private tUserService:TUserService,
              private route: ActivatedRoute,
              private location:Location) {
  }

  ngOnInit():void {
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => { 
        return this.tUserService.getById(params['id']);
      })
      .subscribe(data => this.tUser = data.data);
  }
  checkUser(user:TUser){
    let result =true;
    if(!user.name){
      this.msg = '姓名不能为空';
      result = false;
    }
    if(!user.username){
      this.msg = '用户名不能为空';
      result = false;
    }
    if(!user.password){
      this.msg = '密码不能为空';
      result = false;
    }
    if(!user.mobilePhone){
      this.msg = '手机号不能为空';
      result = false;
    }
    if(!user.email ){
      this.msg = '邮箱不能为空';
      result = false;
    }
    return result;
  }
  onSubmit(){
    if(!this.checkUser(this.tUser)){
      return;
    }
    this.tUserService.update(this.tUser)
      .subscribe(
        data  => {
          if(data.code == 0){
            this.msg = '更新成功';
            setTimeout(() => {this.goBack()},1000);
          }else{
            this.msg = '更新失败';
          }
        },
        error =>  this.errorMessage = <any>error);
  }
  goBack(): void {
    this.location.back();
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }
}

