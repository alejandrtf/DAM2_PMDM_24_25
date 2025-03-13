import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExcursionComponent } from './excursion/excursion.component';
import { RestauranteComponent } from './restaurante/restaurante.component';
import { MuseoComponent } from './museo/museo.component';
import { HotelComponent } from './hotel/hotel.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    ExcursionComponent,
    RestauranteComponent,
    MuseoComponent,
    HotelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
   
 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
