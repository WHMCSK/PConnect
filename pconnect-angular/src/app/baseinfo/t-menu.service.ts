import {Injectable} from "@angular/core";
import {HttpUtil} from "../util/http.util";
import {TMenu} from "./t-menu";


@Injectable()
export class TMenuService{
  private baseUrl = "/pconnect-user/v1/tMenu";
  constructor(private httpUtil: HttpUtil){
  }
  getTree(){
    let url = this.baseUrl+"/allTree";
    return this.httpUtil.get(url);
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

  add(tMenu: TMenu){
    let url = this.baseUrl+"/add";
    return this.httpUtil.post(url, tMenu);
  }
  getById(id:number){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.get(url);
  }
  update(tMenu: TMenu){
    let url = this.baseUrl+"/update";
    return this.httpUtil.put(url, tMenu);
  }
}
