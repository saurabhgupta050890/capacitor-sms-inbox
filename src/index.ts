import { registerPlugin } from '@capacitor/core';

import type { SMSInboxReaderPlugin } from './definitions';

const SMSInboxReader = registerPlugin<SMSInboxReaderPlugin>('SMSInboxReader', {
  web: () => import('./web').then(m => new m.SMSInboxReaderWeb()),
});

export * from './definitions';
export { SMSInboxReader };
