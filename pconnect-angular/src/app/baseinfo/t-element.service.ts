import {Injectable} from "@angular/core";
import {HttpUtil} from "../util/http.util";
import {TElement} from "./t-element";


@Injectable()
export class TElementService{
  private baseUrl = "/pconnect-user/v1/tElement";
  constructor(private httpUtil: HttpUtil){
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

  add(tElement: TElement){
    let url = this.baseUrl+"/add";
    return this.httpUtil.post(url, tElement);
  }
  getById(id:number){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.get(url);
  }
  update(tElement: TElement){
    let url = this.baseUrl+"/update";
    return this.httpUtil.put(url, tElement);
  }

  getByMenuId(menuId:number) {
    let url = this.baseUrl+"/getByMenuId/"+menuId;
    return this.httpUtil.get(url);
  }
}
