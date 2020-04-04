import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class BackEndService {
  constructor(private http: HttpClient) {
  }

  public startMyApp() {
    return this.http.get('http://localhost:8081/start');
  }

  public startProgram() {
    return this.http.get('http://localhost:8081/logs');
  }

  public getInfo() {
    return this.http.get('http://localhost:8081/info');
  }

  public executeTask(taskName: string, robotNumberStr: string, problem: string) {
    return this.http.get('http://localhost:8081/addtask\?task=' + taskName + '&robotNumberStr=' + robotNumberStr + '&problem=' + problem);
  }

  public clear() {
    return this.http.get('http://localhost:8081/clear');
  }
}
