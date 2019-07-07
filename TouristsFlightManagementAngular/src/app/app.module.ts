import { BrowserModule, Title} from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TouristComponent } from './tourist/tourist.component';
import {HttpService} from './http.service';
import { FlightComponent } from './flight/flight.component';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ConnectorComponent } from './connector/connector.component';

@NgModule({
  declarations: [
    AppComponent,
    TouristComponent,
    FlightComponent,
    ConnectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private titleService: Title) {
    this.titleService.setTitle('Tourists Flight Management Center');
  }
}


