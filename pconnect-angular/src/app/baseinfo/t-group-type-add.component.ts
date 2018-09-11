import {Location} from "@angular/common";
import {Component, OnInit, enableProdMode} from "@angular/core";
import {TGroupType} from "./t-group-type";
import {TGroupTypeService} from "./t-group-type.service";
enableProdMode();
@Component({
  templateUrl: './t-group-type-add.component.html',
})
export class TGroupTypeAddComponent implements OnInit {

  msg:string;
  tGroupType:any = new TGroupType();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '组织类型管理';
  constructor(private tGroupTypeService:TGroupTypeService,
              private location:Location) {
  }

  ngOnInit():void {
  }
  msg_(msg_:string) {
    this.msg = msg_;
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
    this.tGroupTypeService.add(this.tGroupType)
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

