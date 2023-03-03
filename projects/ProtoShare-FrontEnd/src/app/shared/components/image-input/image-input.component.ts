import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-image-input',
  templateUrl: './image-input.component.html',
  styleUrls: ['./image-input.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: ImageInputComponent,
      multi: true
    }
  ]
})
export class ImageInputComponent implements OnInit, ControlValueAccessor {

  image: File | null = null;

  onChange: Function = () => {}

  @Output() change = new EventEmitter<File>();

  constructor() { }

  writeValue(obj: any): void {
    this.image = null;
  }
  registerOnChange(fn: any): void {
    this.onChange = fn;
  }
  registerOnTouched(fn: any): void {}

  ngOnInit(): void {
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    this.image = input.files![0];
    this.onChange(this.image)
    this.change.emit(this.image)
  }

}
