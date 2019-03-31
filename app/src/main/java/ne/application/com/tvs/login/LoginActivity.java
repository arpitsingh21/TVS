package ne.application.com.tvs.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ne.application.com.tvs.list.ListActivity;
import ne.application.com.tvs.LoginBodyPojo;
import ne.application.com.tvs.R;

public class LoginActivity extends AppCompatActivity implements LoginView{

    LoginPresenter loginPresenter;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.signIn)
    Button mSignIn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.signIn)
    public void onViewClicked() {
        if (mUsername.getText().toString().equals("") || mPassword.getText().toString().equals("")) {

            Toast.makeText(this, "Enter Username/Password", Toast.LENGTH_SHORT).show();
        } else {
            loginPresenter.login(new LoginBodyPojo(mUsername.getText().toString(), mPassword.getText().toString()));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void data(String dataGson) {
        Intent i = new Intent(this, ListActivity.class);
        i.putExtra("data",dataGson);
        startActivity(i);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
    }


}
