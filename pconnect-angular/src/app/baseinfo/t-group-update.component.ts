import {Location} from "@angular/common";
import "rxjs/add/operator/switchMap";
import {ActivatedRoute, Params} from "@angular/router";
import {Component, OnInit, enableProdMode} from "@angular/core";
import {TGroup} from "./t-group";
import {TGroupService} from "./t-group.service";
enableProdMode();
@Component({
  templateUrl: './t-group-update.component.html',
})
export class TGroupUpdateComponent implements OnInit {
  msg:string;
  tGroup:any = new TGroup();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '组织类型管理';

  tGroupList:any[];
  constructor(private tGroupService:TGroupService,
              private route: ActivatedRoute,
              private location:Location) {
  }

  ngOnInit():void {
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => {
        return this.tGroupService.getById(+params['id'])
      })
      .subscribe(data => {
        this.tGroup = data.data
        this.tGroupService.getListByGroupTypeId(this.tGroup.groupTypeId).subscribe(data =>{
          this.tGroupList = data.data.filter(group => group.id!=this.tGroup.id);
        });
      });

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
    return result;
  }
  onSubmit(){
    if(!this.checkGroup(this.tGroup)){
      return;
    }
    this.tGroupService.update(this.tGroup)
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

