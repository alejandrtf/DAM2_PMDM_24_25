import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  filtroSeccion: string = '';

  

  onInputChange(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    this.filtroSeccion = inputElement.value;
    console.log(this.filtroSeccion);
  }


  manejarExcursion(nuevaExcursion: string) {
    if (nuevaExcursion) {
      alert(`Nueva excursión añadida: ${nuevaExcursion}`);
    } else {
      alert('No se ha añadido ninguna excursión.');
    }
  }


}

