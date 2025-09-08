import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ChatService, Message } from '../../../services/chat.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  loading: boolean = false;

  userQuestion: string = '';

  constructor(private http: HttpClient,
              public chatService: ChatService) {}

  askQuestion () {
    if (!this.userQuestion.trim()) return;


    this.chatService.addMessage('user', this.userQuestion);

    const question = this.userQuestion;

    this.userQuestion = '';

    this.loading = true;

    this.http.get<{ answer: string }>(`http://localhost:8080/ask-ai-gpt5?prompt=${encodeURIComponent(question)}`)
      .subscribe({
        next: (res) => {
          this.chatService.addMessage('ai', res.answer);
          this.loading = false;
        },
        error: (err) => {
          console.error(err);
          this.chatService.addMessage('ai','⚠️ Error receiving a response');
          this.loading = false;
        }
      });
  }
}
