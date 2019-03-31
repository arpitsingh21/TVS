package ne.application.com.tvs.login;

import ne.application.com.tvs.DataPojo;
import ne.application.com.tvs.LoginBodyPojo;
import ne.application.com.tvs.LoginResponsePojo;
import ne.application.com.tvs.TvsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Harikesh on 28/03/2019.
 */
public class LoginPresenter {


    private LoginView loginView;
    private TvsService tvsService;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        if (this.tvsService == null) {
            this.tvsService = new TvsService();
        }
    }

    public void login(LoginBodyPojo loginBodyPojo) {
        tvsService
                .getAPI()
                .getLogin(loginBodyPojo).enqueue(new Callback<LoginResponsePojo>() {
            @Override
            public void onResponse(Call<LoginResponsePojo> call, Response<LoginResponsePojo> response) {
                if (response.isSuccessful()) {
                    LoginResponsePojo loginResponsePojo = response.body();
                    loginView.data(loginResponsePojo.getTABLEDATA());
                }
                else {
                    loginView.error();
                }
            }

            @Override
            public void onFailure(Call<LoginResponsePojo> call, Throwable t) {

            }
        });

    }
}
