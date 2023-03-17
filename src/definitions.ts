export interface SMSInboxReaderPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
