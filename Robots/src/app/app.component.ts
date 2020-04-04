import {Component, OnInit} from '@angular/core';
import {BackEndService} from './myhttp.service';
import {RobotsInfo} from './RobotsInfo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private http: BackEndService) {
  }

  logList: string[];
  robotsInfo: RobotsInfo;
  problem: string;
  title = 'my-app';


  startButtonClick() {
    this.http.startMyApp()
      .subscribe();
    this.http.startProgram()
      .subscribe();
  }


  submitTask(taskName: string, robotNumberStr: string) {
    this.http.executeTask(taskName, robotNumberStr, this.problem)
      .subscribe();
  }


  ngOnInit(): void {
    setInterval(() => this.getData(), 1000);
    setInterval(() => this.getInfo(), 1000);
  }

  getData() {
    this.http.startProgram()
      .subscribe((data: string[]) => this.logList = data
      );
  }

  getInfo() {
    this.http.getInfo()
      .subscribe((data: RobotsInfo) => this.robotsInfo = data
      );
  }

  clear() {
    this.logList.fill('', 0);
    this.http.clear()
      .subscribe();
  }

}
