package assignment.telstra.com.remotedao;

import assignment.telstra.com.model.AboutCountryModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://dl.dropboxusercontent.com/";

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<AboutCountryModel> getCountryInfo();
}