package ne.application.com.tvs.login;

import ne.application.com.tvs.DataPojo;

/**
 * Created by Harikesh on 28/03/2019.
 */
public interface LoginView {
    void data(String dataGson);

    void error();
}
