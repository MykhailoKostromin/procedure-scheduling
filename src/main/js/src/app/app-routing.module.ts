import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PatientComponent} from './patient/patient.component';
import {ProcedureComponent} from './procedure/procedure.component';

const routes: Routes = [
  {path: 'patient', component: PatientComponent},
  {path: 'procedure', component: ProcedureComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
