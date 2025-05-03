export interface Directory {
    directoryId: number;
    countFiles: number;
    department: string;
    directoryName: string;
    directorySize: number;
    files: any[]; 
  }

export interface Message {
    messageId: number;
    text: string;
    date: string;
    time: string;
    type: 'user' | 'bot';
  }