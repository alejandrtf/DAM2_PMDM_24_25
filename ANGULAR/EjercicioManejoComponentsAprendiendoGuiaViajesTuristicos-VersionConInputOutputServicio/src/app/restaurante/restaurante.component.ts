import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-restaurante',
  templateUrl: './restaurante.component.html',
  styleUrl: './restaurante.component.css'
})
export class RestauranteComponent implements OnInit {
  restaurantes: string[] = [];

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.restaurantes = this.dataService.getDatos('restaurantes');
  }

  eliminarRestaurante(nombre: string) {
    this.dataService.removeDato('restaurantes', nombre);
  }
}