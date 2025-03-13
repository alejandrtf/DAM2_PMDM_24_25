import { TestBed } from '@angular/core/testing';

import { MiOtroServicioService } from './mi-otro-servicio.service';

describe('MiOtroServicioService', () => {
  let service: MiOtroServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MiOtroServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
