import { WebPlugin } from '@capacitor/core';

import type { PermissionStatus, Projection, SMSFilter, SMSInboxReaderPlugin, SMSObject } from './definitions';

export class SMSInboxReaderWeb
  extends WebPlugin
  implements SMSInboxReaderPlugin
{
  getCount(filters: SMSFilter): Promise<number> {
    throw new Error('Method not implemented.');
  }
  getSMSList(projections: Projection, filters: SMSFilter): Promise<SMSObject[]> {
    throw new Error('Method not implemented.');
  }
  checkPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
  requestPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
}
