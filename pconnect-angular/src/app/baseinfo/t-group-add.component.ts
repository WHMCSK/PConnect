import { Location }               from '@angular/common';
import {Component, OnInit, enableProdMode} from '@angular/core';
import {TGroup} from "./t-group";
import {TGroupService} from "./t-group.service";
import {ActivatedRoute, Params} from "@angular/router";
enableProdMode();
@Component({
  templateUrl: './t-group-add.component.html',
})
export class TGroupAddComponent implements OnInit {

  msg:string;
  tGroup:any = new TGroup();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '组织架构管理';
  tGroupList:any[];
  constructor(private tGroupService:TGroupService,
              private route: ActivatedRoute,
              private location:Location) {

  }

  ngOnInit():void {
    this.tGroup.parentId = -1;
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => {
        this.tGroup.groupTypeId = +params['id'];
        return this.tGroupService.getListByGroupTypeId(this.tGroup.groupTypeId);
      })
      .subscribe(data => this.tGroupList = data.data);
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }
  checkGroup(group:TGroup){
    let result =true;
    if(!group.name){
      this.msg = '名称不能为空';
      result = false;
    }
    if(!group.code){
      this.msg = '编码不能为空';
      result = false;
    }
    if(!group.path){
      this.msg = 'path不能为空';
      result = false;
    }
    return result;
  }
  onSubmit(){
    if(!this.checkGroup(this.tGroup)){
      return;
    }
    this.tGroupService.add(this.tGroup)
      .subscribe(
        data  => {
          if(data.code == 0){
            this.msg = "添加成功";
            setTimeout(() => {this.goBack()},1000);
          }else{
            this.msg = "添加失败";
          }
        },
        error =>  this.errorMessage = <any>error);
  }
  goBack(): void {
    this.location.back();
  }
}

