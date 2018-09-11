
import {Component, OnInit} from '@angular/core';
import {Config} from "../app-config";
import {Router} from "@angular/router";

@Component({
  selector: 'my-app',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})


export class IndexComponent implements OnInit{
  app:any;
  constructor(private config:Config,
  				private router:Router){
    this.app = config.appConfig; 
  }
  ngOnInit(){
  	
  }

}
