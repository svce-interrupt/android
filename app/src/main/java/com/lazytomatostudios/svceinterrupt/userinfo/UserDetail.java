package com.lazytomatostudios.svceinterrupt.userinfo;

/**
 * Created by drpayyne on 6/9/17.
 */

public class UserDetail {

    private String mUserName;
    private String mUserMail;
    private String mUserPass;
    private String mUserMobileNum;
    private String mUserCollegeName;
    private String mUserEvents;

    public UserDetail() {
    }

    public UserDetail(String username, String userMail, String userPass, String userMobileNum, String userCollegeName, String userEvents) {
        this.mUserName = username;
        this.mUserMail = userMail;
        this.mUserPass = userPass;
        this.mUserMobileNum = userMobileNum;
        this.mUserCollegeName = userCollegeName;
        this.mUserEvents = userEvents;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String userName) {
        mUserName = userName;
    }


    public String getmUserMail() {
        return mUserMail;
    }

    public void setmUserMail(String userMail) {
        mUserMail = userMail;
    }

    public String getmUserMobileNum() {
        return mUserMobileNum;
    }

    public void setmUserMobileNum(String number) {
        mUserMobileNum = number;
    }

    public String getmUserCollegeName() {
        return mUserCollegeName;
    }

    public void setmUserCollegeName(String userCollegeName) {
        mUserCollegeName = userCollegeName;
    }

    public String getmUserEvents() {
        return mUserEvents;
    }

    public void setmUserEvents(String userEvents) {
        mUserEvents = userEvents;
    }
}
