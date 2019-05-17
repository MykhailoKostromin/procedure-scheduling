import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Doctor} from '../model/doctor.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private readonly path: string = '/api/v1/doctors/';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Doctor[]> {
    return this.httpClient.get<Doctor[]>(this.path);
  }
}
