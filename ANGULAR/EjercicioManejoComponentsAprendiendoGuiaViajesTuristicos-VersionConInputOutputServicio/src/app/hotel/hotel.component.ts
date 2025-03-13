import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrl: './hotel.component.css'
})
export class HotelComponent implements OnInit {
  hoteles: string[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.hoteles = this.dataService.getDatos('hoteles');
  }
}
