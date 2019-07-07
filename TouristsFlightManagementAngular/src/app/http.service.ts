import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IFlight} from './flight/flight.component';
import {ITourist} from './tourist/tourist.component';
import {Observable} from 'rxjs';
import {IConnector} from './connector/connector.component';

@Injectable()
export class HttpService {
  constructor(private http: HttpClient) {}

  // Tourist
  getTourists(): Observable<Array<ITourist>> {
    return this.http.get<Array<ITourist>>('http://localhost:8080/rest/tourist/all');
  }

  deleteTouristById(id: number): Observable<object> {
    return this.http.delete('http://localhost:8080/rest/tourist/delete/' + id);
  }

  postTourist(tourist: ITourist) {
    return this.http.post('http://localhost:8080/rest/tourist/load', tourist);
  }

  showFlightsByTouristId(id: number): Observable<object> {
    return this.http.get('http://localhost:8080/rest/tourist/select/' + id);
  }

  // Tourist and Flight
  deleteConnectionBetweenTouristAndFlight(touristId: number, flightId: number): Observable<object> {
    return this.http.delete('http://localhost:8080/rest/connector/' + touristId + '/' + flightId);
  }

  postConnector(connector: IConnector) {
    return this.http.post('http://localhost:8080/rest/connector', connector);
  }

  // Flight
  getFlights(): Observable<Array<IFlight>> {
    return this.http.get<Array<IFlight>>('http://localhost:8080/rest/flight/all');
  }

  deleteFlightById(id: number): Observable<object> {
    return this.http.delete('http://localhost:8080/rest/flight/delete/' + id);
  }

  postFlight(iFlight: IFlight) {
    return this.http.post('http://localhost:8080/rest/flight/load', iFlight);
  }

  showTouristsByFlightId(id: number): Observable<object> {
    return this.http.get('http://localhost:8080/rest/flight/select/' + id);
  }
}
