package ne.application.com.tvs;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Harikesh on 28/03/2019.
 */
public class DataPojo {
    @Expose
    private List<List<String>> data = null;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
