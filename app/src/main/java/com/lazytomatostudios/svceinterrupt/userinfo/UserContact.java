package com.lazytomatostudios.svceinterrupt.userinfo;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by drpayyne on 6/9/17.
 */

public class UserContact {

    public static final String CONTENT_AUTHORITY = "com.extremeplayer.interrupt";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USER_DATA = "user";

    private UserContact() {
    }

    public static final class UserEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USER_DATA);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_DATA;

        public static final String TABLE_NAME = "users";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_USER_NAME = "username";
        public static final String COLUMN_USER_MAIL = "usermail";
        public static final String COLUMN_USER_PASSWORD = "password";
        public static final String COLUMN_USER_MOBILE_NUMBER = "mobile";
        public static final String COLUMN_COLLEGE_NAME = "college";
        public static final String COLUMN_USER_EVENTS = "events";
    }

}
