package ne.application.com.tvs.detail;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ne.application.com.tvs.R;
import ne.application.com.tvs.Utilities;

public class DetailActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.location)
    TextView mLocation;
    @BindView(R.id.specialization)
    TextView mSpecialization;
    @BindView(R.id.account)
    TextView mAccount;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.addImage)
    TextView mAddImage;
    @BindView(R.id.viewImage)
    ImageView mViewImage;
    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.mapView)
    ImageView mMapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setdetails();
    }

    private void setdetails() {
        mName.setText(getIntent().getStringExtra("name"));
        mNumber.setText(getIntent().getStringExtra("number"));
        mLocation.setText(getIntent().getStringExtra("place"));
        mAccount.setText(getIntent().getStringExtra("account"));
        mSpecialization.setText(getIntent().getStringExtra("role"));
        mDate.setText(getIntent().getStringExtra("date"));
        createMapView(getIntent().getStringExtra("account"));
    }

    private void createMapView(String account) {
        Glide.with(this).load("https://maps.googleapis.com/maps/api/staticmap?center="+getIntent().getStringExtra("place")+",+NY&zoom=13&scale=1&size=600x300&maptype=roadmap&format=png&visual_refresh=true&key=AIzaSyAFKD2l2cUeEIy6-lpjlYuDYQ95Cp32uLw").into(mMapView);
    }

    @OnClick(R.id.addImage)
    public void onViewClicked() {

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mViewImage.setImageBitmap(photo);
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            mTimestamp.setText(currentDateTimeString);
        }
    }

    private void createTimeStamp(Bitmap bitmap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(Calendar.getInstance().getTime()); // reading local time in the system
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap dest = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas cs = new Canvas(dest);
        Paint tPaint = new Paint();
        tPaint.setTextSize(35);
        tPaint.setColor(Color.WHITE);
        tPaint.setStyle(Paint.Style.FILL);
        cs.drawBitmap(bitmap, 0f, 0f, null);
        float height = tPaint.measureText("yY");
        cs.drawText(dateTime, 20f, height + 15f, tPaint);
        mViewImage.setImageBitmap(bitmap);
    }

//    private void timestampItAndSave(Bitmap toEdit){
//        Bitmap dest = Bitmap.createBitmap(toEdit.getWidth(), toEdit.getHeight(), Bitmap.Config.ARGB_8888);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateTime = sdf.format(Calendar.getInstance().getTime()); // reading local time in the system
//
//        Canvas cs = new Canvas(dest);
//        Paint tPaint = new Paint();
//        tPaint.setTextSize(35);
//        tPaint.setColor(Color.BLUE);
//        tPaint.setStyle(Paint.Style.FILL);
//        float height = tPaint.measureText("yY");
//        cs.drawText(dateTime, 20f, height+15f, tPaint);
//        mViewImage.setImageBitmap(dest);
//    }
}
