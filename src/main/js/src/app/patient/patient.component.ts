import {Component, OnDestroy, OnInit} from '@angular/core';
import {PatientService} from '../service/patient.service';
import {Patient} from '../model/patient.model';
import {Sex} from '../enum/sex.enum';
import {Subscription} from "rxjs";

@Component({
  selector: 'patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit, OnDestroy {

  private subscription: Subscription = new Subscription();
  patientList: Patient[];
  newPatient: Patient = new Patient(null, null, Sex.male, null);
  sexList: string[] = Object.keys(Sex);

  constructor(private patientService: PatientService) {
  }

  ngOnInit() {
    this.subscription.add(this.patientService.findAll().subscribe(
      response => this.patientList = response
    ));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  deletePatient(patient: Patient): void {
    this.subscription.add(this.patientService.deleteById(patient.id).subscribe(
      response => this.patientList = this.patientList.filter(u => u !== patient)
    ));
  }

  addPatient(): void {
    if (this.newPatient.dayOfBirth !== null) {
      this.newPatient.dayOfBirth = new Date(this.newPatient.dayOfBirth);
    }
    this.subscription.add(this.patientService.addPatient(this.newPatient).subscribe(
      response => this.patientList.push(response)
    ));
  }
}
