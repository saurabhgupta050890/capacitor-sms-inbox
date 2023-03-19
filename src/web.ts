import { WebPlugin } from '@capacitor/core';

import type {
  PermissionStatus,
  Projection,
  SMSFilter,
  SMSInboxReaderPlugin,
  SMSObject,
} from './definitions';

export class SMSInboxReaderWeb
  extends WebPlugin
  implements SMSInboxReaderPlugin
{
  getCount(_options: { filter: SMSFilter }): Promise<{ count: number }> {
    throw new Error('Method not implemented.');
  }
  getSMSList(_options: {
    projection: Projection;
    filter: SMSFilter;
  }): Promise<{ smsList: SMSObject[] }> {
    throw new Error('Method not implemented.');
  }
  checkPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
  requestPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
}
