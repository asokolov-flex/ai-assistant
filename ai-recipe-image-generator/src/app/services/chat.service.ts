import { Injectable } from '@angular/core';

export interface Message {
  sender: 'user' | 'ai';
  text: string;
}

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  messages: Message[] = [];

  addMessage(sender: 'user' | 'ai', text: string) {
    this.messages.push({ sender, text });
  }

  clearMessages() {
    this.messages = [];
  }
}
