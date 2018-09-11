import {Injectable} from "@angular/core";
import {Todo} from "./Todo";
import {HttpUtil} from "../../../util/http.util";


@Injectable()
export class TodoService{
  private baseUrl = "/pconnect-todo/v1/tTodo";
  constructor(private httpUtil: HttpUtil){
  }

  getSessionInfo(){
    let url = "/pconnect-user/v1/session/sessionInfo";
    return  this.httpUtil.get(url);
  }

  setLocalSessionInfo(sessionInfo:any){
    sessionStorage.setItem("sessionInfo",JSON.stringify(sessionInfo));
  }

  getLocalSessionInfo():any{
    return JSON.parse(sessionStorage.getItem("sessionInfo"));
  }
  setLocalAuthorityTElements(tElements:any){
    sessionStorage.setItem("tElements",JSON.stringify(tElements));
  }

  getLocalAuthorityTElements():any{
    return JSON.parse(sessionStorage.getItem("tElements"));
  }

  getList(currentPage:number, pageSize:number) {
    let param = "?pageSize="+pageSize+"&pageNum="+currentPage;
    let url = this.baseUrl+"/listByPage"+param;
    return this.httpUtil.get(url);
  }

  delete(id:any){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.delete(url);
  }

  add(tUser: Todo){
    let url = this.baseUrl+"/add";
    return this.httpUtil.post(url, tUser);
  }

  register(tUser:Todo):any {
    let url = this.baseUrl+"/register";
    return this.httpUtil.post(url, tUser);
  }

  getById(id:number){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.get(url);
  }
  update(user: Todo){
    let url = this.baseUrl+"/update";
    return this.httpUtil.put(url, user);
  }

  getListByGroupId(groupId:number) {
    let url = this.baseUrl+"/getListByGroupId/"+groupId;
    return this.httpUtil.get(url);
  }

  getByKey(term:any) {
    let url = this.baseUrl+"/getList/"+term;
    return this.httpUtil.get(url);
  }

  getAuthorityByUsername(username:string):any {
    let url = this.baseUrl+"/getAuthority/"+username;
    return this.httpUtil.get(url);
  }

  logout() {
    let url = "/logout";
    return this.httpUtil.get(url);
  }

}
