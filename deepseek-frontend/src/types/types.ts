export interface Directory {
    directoryId: number;
    countFiles: number;
    department: string;
    directoryName: string;
    directorySize: number;
  }

  export interface DirectoryFile {
    fileId: number;
    fileName: string;
    fileSize: number;
    directory: Directory; 
  }

export interface Message {
    messageId: number;
    text: string;
    date: string;
    time: string;
    type: 'user' | 'bot';
  }