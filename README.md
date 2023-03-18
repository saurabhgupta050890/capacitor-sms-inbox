# capacitor-sms-inbox

A capacitor plugin to read SMS inbox

## Install

```bash
npm install capacitor-sms-inbox
npx cap sync android
```

## API

<docgen-index>

* [`checkPermissions()`](#checkpermissions)
* [`requestPermissions()`](#requestpermissions)
* [`getCount(...)`](#getcount)
* [`getSMSList(...)`](#getsmslist)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkPermissions()

```typescript
checkPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### getCount(...)

```typescript
getCount(filter: SMSFilter) => Promise<number>
```

| Param        | Type                                            |
| ------------ | ----------------------------------------------- |
| **`filter`** | <code><a href="#smsfilter">SMSFilter</a></code> |

**Returns:** <code>Promise&lt;number&gt;</code>

--------------------


### getSMSList(...)

```typescript
getSMSList(projection: Projection, filter: SMSFilter) => Promise<SMSObject[]>
```

| Param            | Type                                              |
| ---------------- | ------------------------------------------------- |
| **`projection`** | <code><a href="#projection">Projection</a></code> |
| **`filter`**     | <code><a href="#smsfilter">SMSFilter</a></code>   |

**Returns:** <code>Promise&lt;SMSObject[]&gt;</code>

--------------------


### Interfaces


#### PermissionStatus

| Prop      | Type                                                        |
| --------- | ----------------------------------------------------------- |
| **`sms`** | <code><a href="#permissionstate">PermissionState</a></code> |


#### SMSFilter

| Prop               | Type                                                | Default                        |
| ------------------ | --------------------------------------------------- | ------------------------------ |
| **`type`**         | <code><a href="#messagetype">MessageType</a></code> | <code>MessageType.INBOX</code> |
| **`id`**           | <code>number</code>                                 |                                |
| **`threadId`**     | <code>number</code>                                 |                                |
| **`body`**         | <code>string</code>                                 |                                |
| **`bodyRegex`**    | <code>string</code>                                 |                                |
| **`address`**      | <code>string</code>                                 |                                |
| **`addressRegex`** | <code>string</code>                                 |                                |
| **`minDate`**      | <code>number</code>                                 |                                |
| **`maxDate`**      | <code>number</code>                                 |                                |
| **`indexFrom`**    | <code>number</code>                                 |                                |
| **`maxCount`**     | <code>number</code>                                 |                                |


#### SMSObject

| Prop             | Type                                                |
| ---------------- | --------------------------------------------------- |
| **`id`**         | <code>number</code>                                 |
| **`threadId`**   | <code>number</code>                                 |
| **`type`**       | <code><a href="#messagetype">MessageType</a></code> |
| **`address`**    | <code>string</code>                                 |
| **`creator`**    | <code>string</code>                                 |
| **`person`**     | <code>string</code>                                 |
| **`date`**       | <code>number</code>                                 |
| **`dateSent`**   | <code>number</code>                                 |
| **`subject`**    | <code>string</code>                                 |
| **`body`**       | <code>string</code>                                 |
| **`spamReport`** | <code>number</code>                                 |


#### Projection

| Prop             | Type                 | Default            |
| ---------------- | -------------------- | ------------------ |
| **`id`**         | <code>boolean</code> | <code>true</code>  |
| **`threadId`**   | <code>boolean</code> | <code>true</code>  |
| **`type`**       | <code>boolean</code> | <code>true</code>  |
| **`address`**    | <code>boolean</code> | <code>true</code>  |
| **`creator`**    | <code>boolean</code> | <code>false</code> |
| **`person`**     | <code>boolean</code> | <code>false</code> |
| **`date`**       | <code>boolean</code> | <code>true</code>  |
| **`dateSent`**   | <code>boolean</code> | <code>false</code> |
| **`subject`**    | <code>boolean</code> | <code>true</code>  |
| **`body`**       | <code>boolean</code> | <code>true</code>  |
| **`spamReport`** | <code>boolean</code> | <code>false</code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>


### Enums


#### MessageType

| Members      | Value          |
| ------------ | -------------- |
| **`ALL`**    | <code>0</code> |
| **`INBOX`**  | <code>1</code> |
| **`SENT`**   | <code>2</code> |
| **`DRAFT`**  | <code>3</code> |
| **`OUTBOX`** | <code>4</code> |
| **`FAILED`** | <code>5</code> |
| **`QUEUED`** | <code>6</code> |

</docgen-api>
