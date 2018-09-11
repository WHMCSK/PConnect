import {Location} from "@angular/common";
import {Component, OnInit, enableProdMode} from "@angular/core";
import {TDict} from "./t-dict";
import {TDictService} from "./t-dict.service";
enableProdMode();
@Component({
  templateUrl: './t-dict-add.component.html',
})
export class TDictAddComponent implements OnInit {

  msg:string;
  tDict:any = new TDict();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '组织类型管理';
  constructor(private tDictService:TDictService,
              private location:Location) {
  }

  ngOnInit():void {
  }
  msg_(msg_:string) {
    this.msg = msg_;
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
    this.tDictService.add(this.tDict)
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

