import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-excursion',
  templateUrl: './excursion.component.html',
  styleUrl: './excursion.component.css'
})
export class ExcursionComponent implements OnInit {
  excursiones: string[] = [];
  nuevaExcursion2:string="";

  @Output() excursionAñadida = new EventEmitter<string>();

  constructor(private dataService: DataService) {
    
  }

  ngOnInit() {
    this.cargarDatos();
  
  }

  cargarDatos() {
    this.excursiones = this.dataService.getDatos('excursiones');
  }

  agregarExcursion(nuevaExcursion:string) {
    if (nuevaExcursion.trim()) {
      this.dataService.addDato('excursiones', nuevaExcursion);
      this.excursionAñadida.emit(nuevaExcursion);
      nuevaExcursion = '';
     
    }
  }
}
