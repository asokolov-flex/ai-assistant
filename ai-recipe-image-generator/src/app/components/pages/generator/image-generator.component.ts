import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'image-generator',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf
  ],
  templateUrl: './image-generator.component.html',
  styleUrl: './image-generator.component.scss'
})
export class ImageGeneratorComponent {
  prompt: string = '';
  imageUrls: string[] = [];

  constructor(private http: HttpClient) {}

  generateImage() {
    this.http.get<string[]>(`http://localhost:8080/generate-image?prompt=${this.prompt}`)
      .subscribe({
        next: (urls) => {
          console.log(urls);

          this.imageUrls = urls;
        },
        error: (err) => {
          console.error("Error generating image: ", err);
        }
      });
  }
}
