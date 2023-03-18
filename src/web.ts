import { WebPlugin } from '@capacitor/core';

import type { PermissionStatus, Projection, SMSFilter, SMSInboxReaderPlugin, SMSObject } from './definitions';

export class SMSInboxReaderWeb
  extends WebPlugin
  implements SMSInboxReaderPlugin
{
  getCount(_filters: SMSFilter): Promise<{count: number}> {
    throw new Error('Method not implemented.');
  }
  getSMSList(_projections: Projection, _filters: SMSFilter): Promise<{smsList: SMSObject[]}> {
    throw new Error('Method not implemented.');
  }
  checkPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
  requestPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
}
