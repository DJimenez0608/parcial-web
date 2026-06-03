import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'facultades', pathMatch: 'full' },
  {
    path: 'facultades',
    loadComponent: () =>
      import('./components/facultad-list/facultad-list').then(
        (m) => m.FacultadListComponent
      ),
  },
  {
    path: 'facultades/nueva',
    loadComponent: () =>
      import('./components/facultad-form/facultad-form').then(
        (m) => m.FacultadFormComponent
      ),
  },
];
