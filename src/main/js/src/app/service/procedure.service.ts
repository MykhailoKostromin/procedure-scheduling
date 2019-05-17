import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Procedure} from '../model/procedure.model';

@Injectable({
  providedIn: 'root'
})
export class ProcedureService {

  private readonly path: string = '/api/v1/procedures/';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Procedure[]> {
    return this.httpClient.get<Procedure[]>(this.path);
  }

  scheduleProcedure(procedure: Procedure): Observable<Procedure> {
    return this.httpClient.post<Procedure>(this.path, procedure);
  }

  updateStatus(procedure: Procedure): Observable<any> {
    return this.httpClient.patch(this.path + procedure.id, {status: procedure.status})
  }

  deleteById(procedureId: number): Observable<any> {
    return this.httpClient.delete(this.path + procedureId);
  }
}
