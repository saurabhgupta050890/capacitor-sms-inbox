package com.mobiverse.plugins.smsinbox;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.Telephony;
import com.getcapacitor.JSObject;
import java.util.ArrayList;
import java.util.Arrays;

public class GetSMSFilterInput {

    private final String id;
    private final Integer type;
    private final String threadId;
    private final String body;
    private final String bodyRegex;
    private final String address;
    private final String addressRegex;
    private final String minDate;
    private final String maxDate;
    private final String indexFrom;
    private final String maxCount;
    private final ArrayList<String> selectionArgs;
    private String selection;
    private final StringBuilder sortOrder;

    GetSMSFilterInput(JSObject fromJSONObject) {
        this.id = fromJSONObject.optString("id", null);
        this.type = fromJSONObject.optInt("type", Telephony.Sms.MESSAGE_TYPE_INBOX);
        this.threadId = fromJSONObject.optString("threadId", null);
        this.address = fromJSONObject.optString("address", null);
        this.bodyRegex = fromJSONObject.optString("bodyRegex", null);
        this.addressRegex = fromJSONObject.optString("addressRegex", null);
        this.minDate = fromJSONObject.optString("minDate", null);
        this.maxDate = fromJSONObject.optString("maxDate", null);
        this.indexFrom = fromJSONObject.optString("indexFrom", null);
        this.body = fromJSONObject.optString("body", null);
        this.maxCount = fromJSONObject.optString("maxCount", null);
        this.selectionArgs = new ArrayList<>();
        this.sortOrder = new StringBuilder(Telephony.Sms.DEFAULT_SORT_ORDER);
        prepareSelection();
    }

    public Uri getContentUri() {
        switch (this.type) {
            case 0:
                return Telephony.Sms.CONTENT_URI;
            case 2:
                return Telephony.Sms.Sent.CONTENT_URI;
            case 3:
                return Telephony.Sms.Draft.CONTENT_URI;
            case 4:
                return Telephony.Sms.Outbox.CONTENT_URI;
            default:
                return Telephony.Sms.Inbox.CONTENT_URI;
        }
    }

    private void prepareSelection() {
        ArrayList<String> selection = new ArrayList<>();

        if (this.id != null) {
            selection.add(BaseColumns._ID + " = ?");
            selectionArgs.add(this.id);
        }

        if (this.threadId != null) {
            selection.add(Telephony.TextBasedSmsColumns.THREAD_ID + " = ?");
            selectionArgs.add(this.threadId);
        }

        if (this.address != null) {
            selection.add(Telephony.TextBasedSmsColumns.ADDRESS + " = ?");
            selectionArgs.add(this.address.trim());
        }

        if (this.body != null) {
            selection.add(Telephony.TextBasedSmsColumns.BODY + " = ?");
            selectionArgs.add(this.body.trim());
        }

        if (minDate != null) {
            selection.add(Telephony.TextBasedSmsColumns.DATE + ">= ?");
            selectionArgs.add(minDate);
        }

        if (maxDate != null) {
            selection.add(Telephony.TextBasedSmsColumns.DATE + " <= ?");
            selectionArgs.add(maxDate);
        }

        if (indexFrom != null && maxCount != null) {
            this.sortOrder.append(" LIMIT ? ?");
            selectionArgs.add(indexFrom);
            selectionArgs.add(maxCount);
        } else if (maxCount != null) {
            this.sortOrder.append(" LIMIT ?");
            selectionArgs.add(maxCount);
        } else if (indexFrom != null) {
            this.sortOrder.append(" LIMIT -1 OFFSET ?");
            selectionArgs.add(indexFrom);
        }

        if (selection.size() == 0) {
            this.selection = null;
        } else {
            this.selection = String.join(" AND ", selection);
        }
    }

    public String getSelection() {
        return selection;
    }

    public String[] getSelectionArgs() {
        if (this.selectionArgs.size() == 0) {
            return null;
        }
        return this.selectionArgs.toArray(new String[this.selectionArgs.size()]);
    }

    public String getSortOrder() {
        return this.sortOrder.toString();
    }

    public boolean isBodyMatch(SMSObject sms) {
        if (sms.getBody() != null && this.bodyRegex != null) {
            return sms.getBody().matches(this.bodyRegex);
        }
        return true;
    }

    public boolean isAddressMatch(SMSObject sms) {
        if (sms.getAddress() != null && this.addressRegex != null) {
            return sms.getAddress().matches(this.addressRegex);
        }
        return true;
    }

    @Override
    public String toString() {
        return (
            "GetSMSFilterInput{" +
            "id='" +
            id +
            '\'' +
            ", type=" +
            type +
            ", threadId='" +
            threadId +
            '\'' +
            ", body='" +
            body +
            '\'' +
            ", bodyRegex='" +
            bodyRegex +
            '\'' +
            ", address='" +
            address +
            '\'' +
            ", addressRegex='" +
            addressRegex +
            '\'' +
            ", minDate='" +
            minDate +
            '\'' +
            ", maxDate='" +
            maxDate +
            '\'' +
            ", indexFrom='" +
            indexFrom +
            '\'' +
            ", maxCount='" +
            maxCount +
            '\'' +
            ", selectionArgs=" +
            selectionArgs +
            '}'
        );
    }
}
