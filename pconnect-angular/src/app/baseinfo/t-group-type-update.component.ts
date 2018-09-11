import { Location }               from '@angular/common';
import 'rxjs/add/operator/switchMap';
import { ActivatedRoute, Params } from '@angular/router';
import {Component, OnInit, enableProdMode}      from '@angular/core';
import {TGroupType} from "./t-group-type";
import {TGroupTypeService} from "./t-group-type.service";
enableProdMode();
@Component({
  templateUrl: './t-group-type-update.component.html',
})
export class TGroupTypeUpdateComponent implements OnInit {
  msg:string;
  tGroupType:any = new TGroupType();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '组织类型管理';
  constructor(private tGroupTypeService:TGroupTypeService,
              private route: ActivatedRoute,
              private location:Location) {
  }

  ngOnInit():void {
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => this.tGroupTypeService.getById(+params['id']))
      .subscribe(data => this.tGroupType = data.data);
  }
  checkGroupType(groupType:TGroupType){
    let result =true;
    if(!groupType.name){
      this.msg = '名称不能为空';
      result = false;
    }
    if(!groupType.code){
      this.msg = '编码不能为空';
      result = false;
    }
    return result;
  }
  onSubmit(){
    if(!this.checkGroupType(this.tGroupType)){
      return;
    }
    this.tGroupTypeService.update(this.tGroupType)
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

