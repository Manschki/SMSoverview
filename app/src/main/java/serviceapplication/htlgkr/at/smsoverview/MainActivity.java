package serviceapplication.htlgkr.at.smsoverview;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RQ_SMS = 1;
    private ListView listView;
    private MyAdapter adapter;
    private List<Message> messages = new LinkedList<>();
    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS},RQ_SMS);
        } else {
            getMessages();
        }


        listView = findViewById(R.id.listView);
        adapter = new MyAdapter(this, R.layout.my_adapter, messages);
        listView.setAdapter(adapter);
    }

    public void onRequestPermissionsResult( int requestCode,
                                            String[] permissions,
                                            int[] grantResults ) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);
        if (requestCode==RQ_SMS) {
            if (grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED) {
                //user does not allow
            } else {
                getMessages();
            }
        }
    }

    private void getMessages(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        //(address, body, date_sent
        //content://sms/inbox
        String[] mProjection =
                {
                       "address",
                        "body",
                        "date_sent"
                };

        Cursor cursor = getContentResolver().query(
                Uri.parse("content://sms/inbox"),
                mProjection,
                null,
                null,
                null);

        if(null == cursor){
            Log.e(TAG, "Cursor is null");
        }else if(cursor.getCount() < 1){
            Log.i(TAG, "Nothing selected");
        }else{
            while(cursor.moveToNext()){
                String msg = cursor.getString(1);
                String number = cursor.getString(0);
                String date = cursor.getString(2);
                Date d = new Date();
                try {
                    d = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Message m = new Message(msg, number, sdf.format(d));
                messages.add(m);
            }
        }
    }
}
