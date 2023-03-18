import type { PermissionState } from '@capacitor/core';
export interface PermissionStatus {
  sms: PermissionState;
}

export enum MessageType {
  ALL = 0,
  INBOX = 1,
  SENT = 2,
  DRAFT = 3,
  OUTBOX = 4,
  FAILED = 5,
  QUEUED = 6,
}

// https://developer.android.com/reference/android/provider/Telephony.TextBasedSmsColumns
export interface SMSObject {
  id: number;
  threadId: number;
  type: MessageType;
  address: string; // sender address in case of received message
  creator: string; // The identity of the sender of a sent message.
  person: string; // The ID of the sender of the conversation, if present (from contacts)
  date: number;
  dateSent: number;
  subject: string;
  body: string;
  spamReport: number;
}

export interface Projection {
  /**
   * @default true
   */
  id: boolean;
  /**
   * @default true
   */
  threadId: boolean;
  /**
   * @default true
   */
  type: boolean;
  /**
   * @default true
   */
  address: boolean;
  /**
   * @default false
   */
  creator: boolean;
  /**
   * @default false
   */
  person: boolean;
  /**
   * @default true
   */
  date: boolean;
  /**
   * @default false
   */
  dateSent: boolean;
  /**
   * @default true
   */
  subject: boolean;
  /**
   * @default true
   */
  body: boolean;
  /**
   * @default false
   */
  spamReport: boolean;
}
export interface SMSFilter {
  /**
   * @default MessageType.INBOX
   */
  type: MessageType;
  id?: number;
  threadId?: number;
  body?: string;
  bodyRegex?: string;
  address?: string;
  addressRegex?: string;
  minDate?: number;
  maxDate?: number;
  indexFrom?: number;
  maxCount?: number;
}
export interface SMSInboxReaderPlugin {
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(): Promise<PermissionStatus>;
  getCount(filter: SMSFilter): Promise<number>;
  getSMSList(projection: Projection, filter: SMSFilter): Promise<SMSObject[]>;
}
