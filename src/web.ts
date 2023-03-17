import { WebPlugin } from '@capacitor/core';

import type { SMSInboxReaderPlugin } from './definitions';

export class SMSInboxReaderWeb extends WebPlugin implements SMSInboxReaderPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
