import { Component, OnInit } from '@angular/core';
import {HttpService} from '../http.service';
import {IConnector} from '../connector/connector.component';




@Component({
  selector: 'app-tourist',
  templateUrl: './tourist.component.html',
  styleUrls: ['./tourist.component.css']
})
export class TouristComponent implements OnInit {

  touristsList = [];
  touristString = '';
  flightsString = '';
  id: number;
  flightId: number;

  name: string;
  surname: string;
  gender: string;
  country: string;
  notes: string;
  birthDate: string;

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  getTourists() {
    this.httpService.getTourists().subscribe(posts => {
      // console.log(posts);
      this.touristsList = posts as ITourist [];

      this.touristString = '';
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.touristsList.length; i++) {
        // tslint:disable-next-line:max-line-length
        this.touristString += 'ID: ' + this.touristsList[i].id + ' Name: ' + this.touristsList[i].name + ' Surname: ' + this.touristsList[i].surname + ' Gender: ' + this.touristsList[i].gender + ' Country: ' + this.touristsList[i].country + ' Notes: ' + this.touristsList[i].notes + ' Birth date: ' + this.touristsList[i].birthDate + '\n';
      }
    });
  }

  closeTouristsList() {
    this.touristString = '';
  }

  deleteTouristById() {
    this.httpService.deleteTouristById(this.id).subscribe( () => {
      this.getTourists();
    });
    console.log('Tty to delete tourist!');
  }

  postTourist() {
    const iTourist: ITourist = ({
      id: null,
      name: this.name,
      surname: this.surname,
      gender: this.gender,
      country: this.country,
      notes: this.notes,
      birthDate: this.birthDate
    });

    this.httpService.postTourist(iTourist).subscribe(() => {
      this.getTourists();
    });
    console.log('Try to add tourist');
  }

  getFlightsByTouristId() {
      this.httpService.showFlightsByTouristId(this.id).subscribe(flights => {
        this.flightsString = 'Tourist ID:' + this.id + ' has flights: ' + flights as unknown as string;
      });
  }

  closeFlightsList() {
    this.flightsString = '';
  }

  deleteFlightFromTourist() {
    this.httpService.deleteConnectionBetweenTouristAndFlight(this.id, this.flightId).subscribe(() => {
      this.closeFlightsList();
    });
    console.log('Try to delete flight!');
  }

  postConnection() {
    const iConnector: IConnector = ({
      id: null,
      touristID: this.id,
      flightID: this.flightId
    });
    this.httpService.postConnector(iConnector).subscribe();
    console.log('Try to add connection!');
  }
}

export interface ITourist {
  id: number;
  name: string;
  surname: string;
  gender: string;
  country: string;
  notes: string;
  birthDate: string;
}
