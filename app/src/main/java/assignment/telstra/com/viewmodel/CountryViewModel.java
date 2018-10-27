package assignment.telstra.com.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import assignment.telstra.com.model.AboutCountryModel;
import assignment.telstra.com.remotedao.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<AboutCountryModel> countryInfo;

    //we will call this method to get the data
    public LiveData<AboutCountryModel> getLiveInfoObj() {
        //if the list is null
        if (countryInfo == null)
            countryInfo = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadCountryInfo();


        //finally we will return the info object
        return countryInfo;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCountryInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<AboutCountryModel> call = api.getCountryInfo();


        call.enqueue(new Callback<AboutCountryModel>() {
            @Override
            public void onResponse(@NonNull Call<AboutCountryModel> call, @NonNull Response<AboutCountryModel> response) {
                countryInfo.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<AboutCountryModel> call, @NonNull Throwable t) {
                //countryInfo.setValue(t.getMessage());
            }
        });
    }

}
