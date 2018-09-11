import {Injectable} from "@angular/core";
import {HttpUtil} from "../util/http.util";


@Injectable()
export class LoginService{
  constructor(private httpUtil: HttpUtil){
  }

  login(username:string, password:string) {
    let param = {'username':username,'password':password};
    console.log(param);
    let url = "/login";
    return this.httpUtil.postForm(url,param);
  }

}
