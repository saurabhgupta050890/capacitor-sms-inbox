package com.mobiverse.plugins.smsinbox;

import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.Telephony.TextBasedSmsColumns;
import com.getcapacitor.JSObject;

public class SMSObject {

    // _id
    private String smsId;
    // thread_id
    private String threadId;
    // Inbox etc
    private String type;
    private String address; // sender address in case of received message
    private String creator; // The identity of the sender of a sent message.
    private String person; // The ID of the sender of the conversation, if present (from contacts)
    private String date;
    private String dateSent;
    private String subject;
    private String body;

    public String getSmsId() {
        return smsId;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getCreator() {
        return creator;
    }

    public String getPerson() {
        return person;
    }

    public String getDate() {
        return date;
    }

    public String getDateSent() {
        return dateSent;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    private String getStringByColumnName(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index >= 0) {
            return cursor.getString(index);
        }
        return null;
    }

    private Integer parseStrToIntSafe(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Long parseStrToLongSafe(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void fillSMSObjectFromCursor(Cursor cursor) {
        this.smsId = getStringByColumnName(cursor, BaseColumns._ID);
        this.threadId = getStringByColumnName(cursor, TextBasedSmsColumns.THREAD_ID);
        this.type = getStringByColumnName(cursor, TextBasedSmsColumns.TYPE);
        this.address = getStringByColumnName(cursor, TextBasedSmsColumns.ADDRESS);
        this.creator = getStringByColumnName(cursor, TextBasedSmsColumns.CREATOR);
        this.person = getStringByColumnName(cursor, TextBasedSmsColumns.PERSON);
        this.date = getStringByColumnName(cursor, TextBasedSmsColumns.DATE);
        this.dateSent = getStringByColumnName(cursor, TextBasedSmsColumns.DATE_SENT);
        this.subject = getStringByColumnName(cursor, TextBasedSmsColumns.SUBJECT);
        this.body = getStringByColumnName(cursor, TextBasedSmsColumns.BODY);
    }

    public JSObject getJSONObject() {
        JSObject smsObject = new JSObject();
        smsObject.put("id", parseStrToIntSafe(this.smsId));
        smsObject.put("threadId", parseStrToIntSafe(this.threadId));
        smsObject.put("type", parseStrToIntSafe(this.type));
        smsObject.put("address", this.address);
        smsObject.put("creator", this.creator);
        smsObject.put("person", this.person);
        smsObject.put("date", parseStrToLongSafe(this.date));
        smsObject.put("dateSent", parseStrToLongSafe(this.dateSent));
        smsObject.put("subject", this.subject);
        smsObject.put("body", this.body);
        return smsObject;
    }

    @Override
    public String toString() {
        return (
            "SMSObject{" +
            "smsId='" +
            smsId +
            '\'' +
            ", threadId='" +
            threadId +
            '\'' +
            ", type='" +
            type +
            '\'' +
            ", address='" +
            address +
            '\'' +
            ", creator='" +
            creator +
            '\'' +
            ", person='" +
            person +
            '\'' +
            ", date='" +
            date +
            '\'' +
            ", dateSent='" +
            dateSent +
            '\'' +
            ", subject='" +
            subject +
            '\'' +
            ", body='" +
            body +
            '\'' +
            '}'
        );
    }
}
