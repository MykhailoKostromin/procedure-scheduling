import {Sex} from '../enum/sex.enum';

export class Patient {
  constructor(
    public id: number,
    public name: string,
    public sex: Sex,
    public dayOfBirth: Date,
  ) {}
}
