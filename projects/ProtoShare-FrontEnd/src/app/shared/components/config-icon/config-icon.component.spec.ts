import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigIconComponent } from './config-icon.component';

describe('ConfigIconComponent', () => {
  let component: ConfigIconComponent;
  let fixture: ComponentFixture<ConfigIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfigIconComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
