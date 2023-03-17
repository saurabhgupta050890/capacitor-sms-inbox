package com.mobiverse.plugins.smsinbox;

import android.provider.BaseColumns;
import android.provider.Telephony.TextBasedSmsColumns;
import com.getcapacitor.JSObject;
import java.util.ArrayList;

public class GetSMSProjectionInput {

    private final boolean id;
    private final boolean threadId;
    private final boolean address;
    private final boolean creator;
    private final boolean person;
    private final boolean date;
    private final boolean dateSent;
    private final boolean subject;
    private final boolean body;
    private final boolean spamReport;

    GetSMSProjectionInput(JSObject fromJSONObject) {
        this.id = fromJSONObject.optBoolean("id");
        this.threadId = fromJSONObject.optBoolean("threadId");
        this.address = fromJSONObject.optBoolean("address");
        this.creator = fromJSONObject.optBoolean("creator");
        this.person = fromJSONObject.optBoolean("person");
        this.date = fromJSONObject.optBoolean("date");
        this.dateSent = fromJSONObject.optBoolean("dateSent");
        this.subject = fromJSONObject.optBoolean("subject");
        this.body = fromJSONObject.optBoolean("body");
        this.spamReport = fromJSONObject.optBoolean("spamReport");
    }

    public String[] getProjection() {
        ArrayList<String> projection = new ArrayList<>();
        if (this.id) {
            projection.add(BaseColumns._ID);
        }

        if (this.threadId) {
            projection.add(TextBasedSmsColumns.THREAD_ID);
        }

        if (this.address) {
            projection.add(TextBasedSmsColumns.ADDRESS);
        }
        if (this.creator) {
            projection.add(TextBasedSmsColumns.CREATOR);
        }
        if (this.person) {
            projection.add(TextBasedSmsColumns.PERSON);
        }
        if (this.date) {
            projection.add(TextBasedSmsColumns.DATE);
        }
        if (this.dateSent) {
            projection.add(TextBasedSmsColumns.DATE_SENT);
        }
        if (this.subject) {
            projection.add(TextBasedSmsColumns.SUBJECT);
        }
        if (this.body) {
            projection.add(TextBasedSmsColumns.BODY);
        }
        if (this.spamReport) {
            projection.add("spam_report");
        }

        return projection.toArray(new String[projection.size()]);
    }
}
