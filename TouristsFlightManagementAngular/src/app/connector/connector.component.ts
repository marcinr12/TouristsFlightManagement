import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-connector',
  templateUrl: './connector.component.html',
  styleUrls: ['./connector.component.css']
})
export class ConnectorComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}

export interface IConnector {
  id: number;
  touristID: number;
  flightID: number;
}
