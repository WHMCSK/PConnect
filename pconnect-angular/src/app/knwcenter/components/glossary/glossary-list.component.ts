/**
 * Created by F1 on 2017/6/1.
 */
import {Component, OnInit, enableProdMode} from "@angular/core";
import {Router} from "@angular/router";
import {GlossaryService} from "./glossary.service";
import {Glossary} from "./glossary";
import {PageChangedEvent} from "ngx-bootstrap/pagination/pagination.component";
enableProdMode();//阻止报错：Expression has changed after it was checked
@Component({
  templateUrl: './glossary-list.component.html',
})
export class GlossaryListComponent implements OnInit {
  msg: string;
  userList:Glossary[];
  selectedUser:Glossary = new Glossary();
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '词汇表';
  secondName:string = '词汇表';

  /**分页用到的参数**/
  pageSize:number = 12;
  maxSize:number = 5;
  totalItems:number;
  currentPage:number = 1;

  authorityTElements:any[];

  editButton:boolean=false;
  deleteButton:boolean=false;
  addButton:boolean=false;
  viewButton:boolean=false;

  constructor(private router:Router,
              private glossaryService:GlossaryService) {
  }

  ngOnInit():void {
    this.authorityTElements = this.glossaryService.getLocalAuthorityTElements();
    console.log(this.authorityTElements);
    this.authorityTElements.forEach((tElement) =>{
      if(tElement.code == 'glossaryManager:view'){
        this.viewButton=true;
      }else if(tElement.code == 'glossaryManager:btn_add'){
        this.addButton = true;
      }else if(tElement.code == 'glossaryManager:btn_edit'){
        this.editButton = true;
      }else if(tElement.code == 'glossaryManager:btn_del'){
        this.deleteButton = true;
      }
    })
    console.log("test1----");
    if(this.viewButton){
      console.log("test2----");
      this.getGlossaryList();
    }
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }
  pageChanged(page:PageChangedEvent) {
    this.currentPage = page.page;
    this.pageSize = page.itemsPerPage;
    this.getGlossaryList();
  }

  numPages(numPages:number) {
    console.log(numPages);
  }



  onSelect(user:Glossary):void {
    this.selectedUser = user;
  }

  getGlossaryList():void {
    this.glossaryService.getList(this.currentPage,this.pageSize).subscribe(data =>{
      this.userList = data.data.list;
      console.log(this.userList , "this.userList----------");

      this.totalItems = data.data.total;
    })
   }

  delete():void {
    if(!this.selectedUser.id){
      this.msg = "请选择用户信息";
      return;
    }
    if(window.confirm('你确定要删除记录吗？')){
      this.glossaryService.delete(this.selectedUser.id).subscribe(data => {
        if(data.code==0){
          this.msg = "删除成功";
          this.getGlossaryList();
        }else{
          this.msg = "删除失败";
        }
      });
    }
  }

  edit(){
    if(!this.selectedUser.id){
      this.msg = "请选择用户信息";
    }else{
      this.router.navigate(['/privilege/tUserUpdate',this.selectedUser.id]);
    }
  }

}
