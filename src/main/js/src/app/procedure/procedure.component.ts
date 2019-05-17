import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {PatientService} from '../service/patient.service';
import {Procedure} from '../model/procedure.model';
import {ProcedureService} from '../service/procedure.service';
import {Status} from '../enum/status.enum';
import {Patient} from '../model/patient.model';
import {Doctor} from '../model/doctor.model';
import {Room} from '../model/room.model';
import {DoctorService} from '../service/doctor.service';
import {RoomService} from '../service/room.service';

@Component({
  selector: 'app-procedure',
  templateUrl: './procedure.component.html',
  styleUrls: ['./procedure.component.scss']
})
export class ProcedureComponent implements OnInit {

  private subscription: Subscription = new Subscription();
  procedureList: Procedure[];
  patientList: Patient[];
  doctorList: Doctor[];
  roomList: Room[];
  newProcedure: Procedure = new Procedure(null, null, null, null, null, Status.Planned, null, null);
  statusList: string[] = Object.keys(Status);

  constructor(private patientService: PatientService,
              private procedureService: ProcedureService,
              private doctorService: DoctorService,
              private roomService: RoomService) {
  }

  ngOnInit() {
    this.subscription.add(this.procedureService.findAll().subscribe(
      response => this.procedureList = response
    ));
    this.subscription.add(this.patientService.findAll().subscribe(
      response => this.patientList = response
    ));
    this.subscription.add(this.doctorService.findAll().subscribe(
      response => this.doctorList = response
    ));
    this.subscription.add(this.roomService.findAll().subscribe(
      response => this.roomList = response
    ));
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  deleteProcedure(procedure: Procedure): void {
    this.subscription.add(this.procedureService.deleteById(procedure.id).subscribe(
      response => this.procedureList = this.procedureList.filter(u => u !== procedure)
    ));
  }

  public updateStatus(procedure: Procedure): void {
    this.subscription.add(this.procedureService.updateStatus(procedure).subscribe());
  }

  addProcedure(): void {
    console.log(JSON.stringify(this.newProcedure));
    this.subscription.add(this.procedureService.scheduleProcedure(this.newProcedure).subscribe(
      response => this.procedureList.push(response)
    ));
  }

}
