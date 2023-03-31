import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-close-button',
  templateUrl: './close-button.component.html',
  styleUrls: ['./close-button.component.css']
})
export class CloseButtonComponent implements OnInit {

  @Output() closeEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  close(): void {
    this.closeEvent.emit();
  }

}
