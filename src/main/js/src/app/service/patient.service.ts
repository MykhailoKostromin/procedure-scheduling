import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Patient} from '../model/patient.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private readonly path: string = '/api/v1/patients/';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Patient[]> {
    return this.httpClient.get<Patient[]>(this.path);
  }

  deleteById(patientId: number): Observable<any> {
    return this.httpClient.delete(this.path + patientId);
  }

  addPatient(patient: Patient): Observable<Patient> {
    return this.httpClient.post<Patient>(this.path, patient);
  }
}
