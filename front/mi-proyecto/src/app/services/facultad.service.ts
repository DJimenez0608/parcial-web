import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Facultad } from '../models/facultad.model';

@Injectable({ providedIn: 'root' })
export class FacultadService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/facultad';

  getAll(): Observable<Facultad[]> {
    return this.http.get<Facultad[]>(this.apiUrl);
  }

  create(facultad: Facultad): Observable<void> {
    return this.http.post<void>(this.apiUrl, facultad);
  }
}
