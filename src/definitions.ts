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
}

export interface Projection {
  /**
   * @default true
   */
  id?: boolean;
  /**
   * @default true
   */
  threadId?: boolean;
  /**
   * @default true
   */
  type?: boolean;
  /**
   * @default true
   */
  address?: boolean;
  /**
   * @default false
   */
  creator?: boolean;
  /**
   * @default false
   */
  person?: boolean;
  /**
   * @default true
   */
  date?: boolean;
  /**
   * @default false
   */
  dateSent?: boolean;
  /**
   * @default true
   */
  subject?: boolean;
  /**
   * @default true
   */
  body?: boolean;
}
export interface SMSFilter {
  /**
   * @default MessageType.INBOX
   */
  type?: MessageType;
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
  getCount(options: { filter?: SMSFilter }): Promise<{ count: number }>;
  getSMSList(options: {
    projection?: Projection;
    filter?: SMSFilter;
  }): Promise<{ smsList: SMSObject[] }>;
  /**
   * Returns a raw sms object (all columns). Its like running SELECT * FROM ..
   * 
   * E.g.
   * ```json
   * {
       "_id": 33,
       "thread_id": 153,
       "address": "TEST",
       "person": null,
       "date": 1679232404564,
       "date_sent": 1679562604444,
       "protocol": 0,
       "read": 0,
       "status": -1,
       "type": 1,
       "reply_path_present": 0,
       "subject": null,
       "body": "SMS body",
       "service_center": "+918299901123",
       "locked": 0,
       "sub_id": 1,
       "error_code": 0,
       "creator": "com.google.android.apps.messaging",
       "seen": 1,
       "priority": -1
    }
    ```
   * **Note: This is a raw query and ineffecient. Use with caution**
   * @param options 
   */
  getRawSMSList(options: { filter?: SMSFilter }): Promise<{ rawSmsList: any }>;
}
