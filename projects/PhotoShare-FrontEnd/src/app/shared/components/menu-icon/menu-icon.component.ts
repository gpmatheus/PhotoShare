import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-menu-icon',
  templateUrl: './menu-icon.component.html',
  styleUrls: ['./menu-icon.component.css']
})
export class MenuIconComponent implements OnInit {

  @Output() click = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  emit(): void {
    this.click.emit();
  }

}
