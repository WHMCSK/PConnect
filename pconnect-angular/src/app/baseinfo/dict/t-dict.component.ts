import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {TDict} from "./t-dict";
import {TDictService} from "./t-dict.service";
import {TUserService} from "../t-user.service";
import {PageChangedEvent} from "ngx-bootstrap/pagination/pagination.component";

@Component({
  templateUrl: './t-dict.component.html',
  styleUrls: ['./t-dict.component.css']
})
export class TDictComponent implements OnInit {
  msg: string;
  dictList:TDict[];
  selectedDict:TDict = new TDict();
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '基础配置';
  secondName:string = '字典管理';

  /**分页用到的参数**/
  pageSize:number = 10;
  maxSize:number = 5;
  totalItems:number;
  currentPage:number = 1;

  authorityTElements:any[];
  editButton:boolean=false;
  deleteButton:boolean=false;
  addButton:boolean=false;
  viewButton:boolean=false;
  constructor(private router:Router,
              private tUserService:TUserService,
              private tDictService:TDictService) { }


  ngOnInit():void {
    this.authorityTElements = this.tUserService.getLocalAuthorityTElements();
    console.log("this.authorityTElements:",this.authorityTElements);
    this.authorityTElements.forEach((tElement) =>{
      if(tElement.code == 'dictManager:view'){
        this.viewButton=true;
      }else if(tElement.code == 'dictManager:btn_add'){
        this.addButton = true;
      }else if(tElement.code == 'dictManager:btn_edit'){
        this.editButton = true;
      }else if(tElement.code == 'dictManager:btn_del'){
        this.deleteButton = true;
      }
    })
    if(this.viewButton) {
      this.getDictList();
    }
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }
  pageChanged(page:PageChangedEvent) {
    this.currentPage = page.page;
    this.pageSize = page.itemsPerPage;
    this.getDictList();
  }

  numPages(numPages:number) {
    console.log(numPages);
  }



  onSelect(dict:TDict):void {
    this.selectedDict = dict;
  }

  getDictList():void {
    this.tDictService.getList(this.currentPage, this.pageSize).subscribe(data => {
      this.dictList = data.data.list;
      this.totalItems = data.data.total;
    });

  }

  delete():void {
    if(!this.selectedDict.id){
      this.msg = "请选择字典信息";
      return;
    }
    if(window.confirm('你确定要删除记录吗？')){
      this.tDictService.delete(this.selectedDict.id).subscribe(data => {
        if(data.code==0){
          this.msg = "删除成功";
          this.getDictList();
        }else{
          this.msg = "删除失败";
        }
      });
    }
  }

  edit(){
    if(!this.selectedDict.id){
      this.msg = "请选择用户信息";
    }else{
      this.router.navigate(['/privilege/tDictUpdate', this.selectedDict.id]);
    }
  }

}
