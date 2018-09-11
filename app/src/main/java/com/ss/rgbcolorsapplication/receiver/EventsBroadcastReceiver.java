package com.ss.rgbcolorsapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EventsBroadcastReceiver extends BroadcastReceiver {

    public static final String EVENT_ACTION = "com.ss.rgbcolorsapplication.EVENT_ACTION";

    public static final String EVENT_EXTRA = "EVENT_EXTRA";

    private OnEventReceiveListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String event = intent.getStringExtra(EVENT_EXTRA);
        if (action == null) {
            return;
        }
        switch (action) {
            case EVENT_ACTION:
                if (mListener != null) {
                    mListener.onEventListen();
                }
                break;
        }
    }

    public void setListener(OnEventReceiveListener listener) {
        mListener = listener;
    }

    public interface OnEventReceiveListener {
        void onEventListen();
    }
}
