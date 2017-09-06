package com.lazytomatostudios.svceinterrupt.userinfo;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by drpayyne on 6/9/17.
 */

public class UserInfoList {

    ArrayList<UserDetail> mArrayList = new ArrayList<UserDetail>();
    UserDBHelper mUserDbHelper;

    public String getmUserName() {
        return mArrayList.get(0).getmUserName();
    }

    public String getmUserMail() {
        return mArrayList.get(0).getmUserMail();
    }

    public String getmUserMobileNum() {
        return mArrayList.get(0).getmUserMobileNum();
    }

    public String getmUserCollegeName() {
        return mArrayList.get(0).getmUserCollegeName();
    }

    public String getmUserEvents() {
        return mArrayList.get(0).getmUserEvents();
    }


    public void UserDetail(Context context, String mail) {
        mUserDbHelper = new UserDBHelper(context);
        mArrayList = mUserDbHelper.getAllDetails(mail);
    }

}
