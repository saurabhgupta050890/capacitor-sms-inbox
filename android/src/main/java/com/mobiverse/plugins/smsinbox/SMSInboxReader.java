package com.mobiverse.plugins.smsinbox;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.Telephony;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;

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

    public JSONArray getRawSMSList(GetSMSFilterInput filterInput) {
        String selection = filterInput.getSelection();
        String[] selectionArgs = filterInput.getSelectionArgs();
        String sortOrder = filterInput.getSortOrder();

        ContentResolver cr = this.mActivity.getContentResolver();
        Cursor cursor = cr.query(filterInput.getContentUri(), null, selection, selectionArgs, sortOrder);

        JSONArray rawSMSList = new JSONArray();
        if (cursor != null && cursor.getCount() > 0) {
            JSObject rawSmsObj;
            SMSObject smsObj;
            int columnCount = cursor.getColumnCount();

            while (cursor.moveToNext()) {
                smsObj = new SMSObject();
                smsObj.fillSMSObjectFromCursor(cursor);

                if (filterInput.isAddressMatch(smsObj) && filterInput.isBodyMatch(smsObj)) {
                    rawSmsObj = new JSObject();
                    for (int i = 0; i < columnCount; i++) {
                        if (cursor.getColumnName(i) != null) {
                            try {
                                rawSmsObj.put(cursor.getColumnName(i), cursor.getString(i));
                            } catch (Exception e) {
                                Logger.error("Error occurred while creating raw SMS Object", e);
                            }
                        }
                    }
                    rawSMSList.put(rawSmsObj);
                }
            }
            cursor.close();
        }

        if (cursor != null) {
            cursor.close();
        }
        return rawSMSList;
    }
}
