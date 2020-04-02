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
    return this.http.get('http://localhost:8081/test');
  }
  public getInfo() {
    return this.http.get('http://localhost:8081/info');
  }

  public executeTask(taskName: string) {
    return this.http.get('http://localhost:8081/addtask\?task=' + taskName);
  }

  public findSum(sum: string) {
    return this.http.get('http://localhost:8081/addtask\?task=findSum');
  }

  public clear() {
    return this.http.get('http://localhost:8081/clear');
  }
}
