package com.android.internal.telephony;

/**
 * Created by Alex on 13/12/15.
 */
public interface ITelephony {
    boolean endCall();

    void answerRingingCall();

    void silenceRinger();
}
