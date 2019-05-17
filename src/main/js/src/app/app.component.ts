import { Component } from '@angular/core';
import {Title} from '@angular/platform-browser';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private titleService: Title, private router: Router) {
    titleService.setTitle('Procedure Scheduling');
    this.router.navigate(['patient'])
  }
}
