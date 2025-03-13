import { Component, Input, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-museo',
  templateUrl: './museo.component.html',
  styleUrl: './museo.component.css'
})
export class MuseoComponent implements OnInit {
  @Input() color: string = 'black';
  @Input() titulo!: string;
  
  museos: string[] = [];

  constructor(private dataService: DataService) {console.log("constructor: "+this.titulo)}

  ngOnInit() {
    console.log("ngOnInit: "+this.titulo);
    this.cargarDatos();
  }

  cargarDatos() {
    this.museos = this.dataService.getDatos('museos');
  }

  editarMuseo(nombreAntiguo: string, nuevoValor: string) {
    this.dataService.editDato('museos', nombreAntiguo, nuevoValor);
  }
}