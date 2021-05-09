import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatRadioModule} from '@angular/material/radio';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatExpansionModule} from '@angular/material/expansion';
import {FlexLayoutModule} from '@angular/flex-layout';


const EXPORT_MODULES = [
  CommonModule,
  FlexLayoutModule,
];

const EXPORT_MATERIAL_MODULES = [
  MatToolbarModule,
  MatCardModule,
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatExpansionModule,
];

const EXPORT_DECLARATIONS = [

];

@NgModule({
  declarations: [
    ...EXPORT_DECLARATIONS,
  ],
  imports: [
    ...EXPORT_MODULES,
    ...EXPORT_MATERIAL_MODULES,
  ],
  exports: [
    ...EXPORT_MODULES,
    ...EXPORT_MATERIAL_MODULES,
    ...EXPORT_DECLARATIONS,
  ],
})
export class SharedModule {
}
