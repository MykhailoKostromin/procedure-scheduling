import {Status} from '../enum/status.enum';
import {Doctor} from './doctor.model';
import {Room} from './room.model';
import {Patient} from './patient.model';

export class Procedure {
  constructor(
    public id: number,
    public patient: Patient,
    public doctor: Doctor,
    public room: Room,
    public description: string,
    public status: Status,
    public plannedStartTime: Date,
    public estimatedEndTime: Date,
  ) {}
}
