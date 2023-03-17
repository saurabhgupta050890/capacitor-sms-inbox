package com.mobiverse.plugins.smsinbox;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Telephony;
import java.util.ArrayList;

public class SMSInboxReader {

    private final Activity mActivity;

    SMSInboxReader(Activity activity) {
        this.mActivity = activity;
    }

    public Integer getCount() {
        ContentResolver cr = this.mActivity.getContentResolver();
        Cursor cursor = cr.query(Telephony.Sms.Inbox.CONTENT_URI, null, null, null, null);

        int smsCount = 0;
        if (cursor != null) {
            smsCount = cursor.getCount();
            cursor.close();
        }

        return smsCount;
    }

    public ArrayList<SMSObject> getSMSList(GetSMSProjectionInput projectionInput) {
        String[] projection = projectionInput.getProjection();

        ContentResolver cr = this.mActivity.getContentResolver();
        Cursor cursor = cr.query(Telephony.Sms.Inbox.CONTENT_URI, projection, null, null, null);

        ArrayList<SMSObject> smsList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            SMSObject smsObj;
            while (cursor.moveToNext()) {
                smsObj = new SMSObject();
                smsObj.fillSMSObjectFromCursor(cursor);
                smsList.add(smsObj);
            }
            cursor.close();
            return smsList;
        }

        if (cursor != null) {
            cursor.close();
        }
        return smsList;
    }
}
