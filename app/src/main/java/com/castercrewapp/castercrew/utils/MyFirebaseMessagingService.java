package com.castercrewapp.castercrew.utils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    SharedPreferenceUtils preferances;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData().size() > 0) {




        }



    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        preferances=SharedPreferenceUtils.getInstance(this);
        String refreshedToken = s;
        preferances.setValue(AccountUtils.FCM_TOKEN, refreshedToken);

    }
}
