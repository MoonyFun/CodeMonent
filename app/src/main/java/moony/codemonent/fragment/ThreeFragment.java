package moony.codemonent.fragment;

import android.app.Fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moony.codemonent.MainActivity;
import moony.codemonent.R;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<新闻frag>
 */
public class ThreeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        getNotification();
        return view;
    }

    public void getNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("菜菜")
                .setContentText("welcome to china!")
                .setAutoCancel(true);
        PendingIntent intent = PendingIntent.getActivity(getActivity(), 0, new Intent(getActivity(), MainActivity.class), 0);
        builder.setContentIntent(intent);

        Notification notification = builder.build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(getActivity());
        nm.notify(0, notification);
//        nm.cancel();
//        NotificationManagerCompat.from(getActivity()).notify(0, notification);
    }
}
