package gwicks.com.facecrop;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.vision.face.FaceDetector;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private int FACE_DETECTION_RATIO_MINIMUM = 10;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    FaceDetector detector;

    float x1;
    float y1;
    float x2;
    float y2;

    private static final int PHOTO_REQUEST = 10;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions((Activity) this,
                        new String[]{Manifest.permission.CAMERA},
                        11);
            }
        }

        isStoragePermissionGranted();

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FaceDetectAndCrop fd = new FaceDetectAndCrop(mContext);

                

//                try{
//                    fd.execute();
//                }catch (Exception e){
//                    Log.d(TAG, "onClick: error " + e);
//                }

                //fd.detectAndCrop(mContext);
               // fd.execute();
            }
        });
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG, "onRequestPermissionsResult: 11");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //takePicture();
                    Log.d(TAG, "onRequestPermissionsResult: 1");
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 2");
                    //Toast.makeText(StudyCodeVerification.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
