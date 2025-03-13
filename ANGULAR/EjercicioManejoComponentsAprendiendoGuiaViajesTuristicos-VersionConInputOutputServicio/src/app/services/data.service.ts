import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  

  private datos:   {[key:string ] : string[]}   = {
    excursiones: ['Excursión al bosque', 'Tour por la ciudad', 'Visita al lago'],
    restaurantes: ['La Trattoria', 'El Asador', 'Sushi House'],
    museos: ['Museo de Arte Moderno', 'Museo de Historia', 'Galería Nacional'],
    hoteles: ['Hotel Central', 'Resort Playa', 'Hostal Económico']
  
  };

  getDatos(seccion: string): string[] {
    return this.datos[seccion] || [];
  }

 
 
  addDato(seccion: string, nuevoDato: string): void {
    if (this.datos[seccion]) {
      this.datos[seccion].push(nuevoDato);
    }
  }

  removeDato(seccion: string, nombre: string): void {
    if (this.datos[seccion]) {
      const index = this.datos[seccion].findIndex(dato => dato === nombre);
      if (index !== -1) {
        this.datos[seccion].splice(index, 1);
      }
    }
  }

  editDato(seccion: string, valorAntiguo: string, nuevoValor: string): void {
    if (this.datos[seccion]) {
      const index = this.datos[seccion].findIndex(dato => dato === valorAntiguo);
      if (index !== -1) {
        this.datos[seccion][index] = nuevoValor;
      }
    }
  }
}
