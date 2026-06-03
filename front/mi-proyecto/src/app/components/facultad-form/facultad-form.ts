import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { FacultadService } from '../../services/facultad.service';

@Component({
  selector: 'app-facultad-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './facultad-form.html',
  styleUrl: './facultad-form.css',
})
export class FacultadFormComponent {
  private fb = inject(FormBuilder);
  private service = inject(FacultadService);
  private router = inject(Router);

  form = this.fb.group({
    nombre: ['', [Validators.required]],
    decano: ['', [Validators.required]],
    ubicacion: ['', [Validators.required]],
  });

  enviado = false;
  error = '';

  submit(): void {
    if (this.form.invalid) return;
    this.enviado = true;
    this.service.create(this.form.value as any).subscribe({
      next: () => this.router.navigate(['/facultades']),
      error: () => {
        this.error = 'Error al crear la facultad';
        this.enviado = false;
      },
    });
  }
}
