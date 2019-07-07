import { Component, OnInit } from '@angular/core';
import {HttpService} from '../http.service';
import {ITourist} from '../tourist/tourist.component';
import {IConnector} from '../connector/connector.component';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  flightsList = [];
  flightsString;
  private touristString: string;
  flightId: number;
  touristId: number;
  departure: string;
  arrival: string;
  seatsNumber: number;
  occupiedSeats: number;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  getFlights() {
    this.httpService.getFlights().subscribe(posts => {
      // console.log(posts);
      this.flightsList = posts as IFlight [];

      this.flightsString = '';
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.flightsList.length; i++) {
        // tslint:disable-next-line:max-line-length
        this.flightsString += 'ID: ' + this.flightsList[i].id + ' Departure: ' + this.flightsList[i].departure + ' Arrival: ' + this.flightsList[i].arrival + ' Seats number: ' + this.flightsList[i].seatsNumber + ' Occupied seats: ' + this.flightsList[i].occupiedSeats + '\n';
      }
    });
  }

    closeFlightsList() {
      this.flightsString = '';
    }

  deleteFlightById() {
    this.httpService.deleteFlightById(this.flightId).subscribe(() => {
      this.getFlights();
    });
    console.log('Try to delete flight!');
  }

  postFlight() {
    const iFlight: IFlight = ({
      id: null,
      departure: this.departure,
      arrival: this.arrival,
      seatsNumber: this.seatsNumber,
      occupiedSeats: this.occupiedSeats
    });
    this.httpService.postFlight(iFlight).subscribe(() => {
      this.getFlights();
    });
    console.log('Try to add flight!');
  }

  getTouristsByFlightId() {
    this.httpService.showTouristsByFlightId(this.flightId).subscribe(tourists => {
      this.touristString = 'Flight ID:' + this.flightId + ' has tourists: ' + tourists as unknown as string;
    });
  }

  closeTouristsList() {
    this.flightsString = '';
  }

  deleteTouristFromFlight() {
    this.httpService.deleteConnectionBetweenTouristAndFlight(this.touristId, this.flightId).subscribe(() => {
      this.closeTouristsList();
    });
    console.log('Try to delete tourist!');
  }

  postConnection() {
    const iConnector: IConnector = ({
      id: null,
      touristID: this.touristId,
      flightID: this.flightId
    });
    this.httpService.postConnector(iConnector).subscribe();
    console.log('Try to add connection!');
  }
}

export interface IFlight {
  id: number;
  departure: string;
  arrival: string;
  seatsNumber: number;
  occupiedSeats: number;
}
