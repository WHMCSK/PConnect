import { Location }               from '@angular/common';
import 'rxjs/add/operator/switchMap';
import { ActivatedRoute, Params } from '@angular/router';
import {Component, OnInit, enableProdMode}      from '@angular/core';
import {TDict} from "./t-dict";
import {TDictService} from "./t-dict.service";
enableProdMode();
@Component({
  templateUrl: './t-dict-update.component.html',
})
export class TDictUpdateComponent implements OnInit {
  msg:string;
  tDict:any = new TDict();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '字典管理';
  constructor(private tDictService:TDictService,
              private route: ActivatedRoute,
              private location:Location) {
  }

  ngOnInit():void {
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => this.tDictService.getById(+params['id']))
      .subscribe(data => this.tDict = data.data);
  }
  checkDict(dict:TDict){
    let result =true;
    if(!dict.name){
      this.msg = '名称不能为空';
      result = false;
    }
    if(!dict.code){
      this.msg = '编码不能为空';
      result = false;
    }
    if(!dict.name){
      this.msg = '名称不能为空';
      result = false;
    }
    if(!dict.sort){
      this.msg = '排序不能为空，且为数字';
      result = false;
    }
    return result;
  }
  onSubmit(){
    if(!this.checkDict(this.tDict)){
      return;
    }
    this.tDictService.update(this.tDict)
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

