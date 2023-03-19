package com.mobiverse.plugins.smsinbox;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.Telephony;
import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<SMSObject> getSMSList(GetSMSProjectionInput projectionInput, GetSMSFilterInput filterInput) {
        String[] projection = projectionInput.getProjection();
        String selection = filterInput.getSelection();
        String[] selectionArgs = filterInput.getSelectionArgs();
        String sortOrder = filterInput.getSortOrder();

        ContentResolver cr = this.mActivity.getContentResolver();
        Cursor cursor = cr.query(filterInput.getContentUri(), projection, selection, selectionArgs, sortOrder);

        ArrayList<SMSObject> smsList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            SMSObject smsObj;
            while (cursor.moveToNext()) {
                smsObj = new SMSObject();
                smsObj.fillSMSObjectFromCursor(cursor);
                if (filterInput.isAddressMatch(smsObj) && filterInput.isBodyMatch(smsObj)) {
                    smsList.add(smsObj);
                }
            }
            cursor.close();
        }

        if (cursor != null) {
            cursor.close();
        }
        return smsList;
    }
}
