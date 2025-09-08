import { Component } from '@angular/core';
import { ImageGeneratorComponent } from './components/pages/generator/image-generator.component';
import {NgIf} from "@angular/common";
import {ChatComponent} from "./components/pages/chat/chat.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ImageGeneratorComponent, NgIf, ChatComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ai-recipe-image-generator';
  activeTab: string = 'image-generator';

  handleTabChange(tab: string) {
    this.activeTab = tab;
  }

}
