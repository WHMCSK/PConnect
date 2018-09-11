import {Injectable} from "@angular/core";
/**
 * Created by F1 on 2017/10/16.
 */
@Injectable()
export class Config{
  public appConfig:any = { 
    baseUrl:"http://localhost:8965", 
    name: 'PConnect',
    version: '4.0.0',
    testFlag:true,
    // for chart colors
    color: {
      primary: '#7266ba',
      info:    '#23b7e5',
      success: '#27c24c',
      warning: '#fad733',
      danger:  '#f05050',
      light:   '#e8eff0',
      dark:    '#3a3f51',
      black:   '#1c2b36'
    },
    settings: {
      themeID: 1,
      navbarHeaderColor: 'bg-black',
      navbarCollapseColor: 'bg-white-only',
      asideColor: 'bg-black',
      headerFixed: true,
      asideFixed: false,
      asideFolded: false,
      asideDock: false,
      container: false
    }
  };
}
