import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FacultadService } from '../../services/facultad.service';
import { Facultad } from '../../models/facultad.model';

@Component({
  selector: 'app-facultad-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './facultad-list.html',
  styleUrl: './facultad-list.css',
})
export class FacultadListComponent implements OnInit {
  private service = inject(FacultadService);
  facultades: Facultad[] = [];
  error = '';

  ngOnInit(): void {
    this.service.getAll().subscribe({
      next: (data) => (this.facultades = data),
      error: () => (this.error = 'Error al cargar las facultades'),
    });
  }
}
