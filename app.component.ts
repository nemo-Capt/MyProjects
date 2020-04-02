import {Component} from '@angular/core';
import {BackEndService} from './myhttp.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  logList: string[];
  info: number[];
  constructor(private http: BackEndService) {
  }

  title = 'my-app';

  startButtonClick() {
    this.http.startMyApp()
      .subscribe();
    this.http.startProgram()
      .subscribe();
  }

  findSum(sum: string) {
    this.http.findSum(sum)
      .subscribe();
  }

  submitTask(taskName: string) {
    this.http.executeTask(taskName)
      .subscribe();
  }

  getData() {
    this.http.startProgram()
      .subscribe((data: string[]) => this.logList = data
      );
  }

  getInfo() {
    this.http.getInfo()
      .subscribe((data: number[]) => this.info = data
      );
  }

  clear() {
    this.logList.fill('', 0);
    this.http.clear()
      .subscribe();
  }
}
