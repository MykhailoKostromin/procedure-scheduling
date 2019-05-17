import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from '../model/room.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private readonly path: string = '/api/v1/rooms/';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Room[]> {
    return this.httpClient.get<Room[]>(this.path);
  }
}
