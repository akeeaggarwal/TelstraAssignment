package assignment.telstra.com.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;


import assignment.telstra.com.R;
import assignment.telstra.com.Utility.AppConstant;
import assignment.telstra.com.Utility.AppUtil;
import assignment.telstra.com.model.AboutCountryModel;
import assignment.telstra.com.viewmodel.CountryViewModel;


public class AboutCountryFragment extends Fragment {

    public static final String TAG = "AboutCountryFragment";


    private RecyclerView rvCountryInfoList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CountryInfoAdapter countryInfoAdapter;

    public AboutCountryFragment() {
        // Required empty public constructor
    }

    private void initView(View view){
        rvCountryInfoList = view.findViewById(R.id.rv_about_country_list);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCountryInfoList.setLayoutManager(linearLayoutManager);
        setListener();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_country, container, false);
       initView(view);
        return view;
    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadCountryInfo();
                    }
                }
        );
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadCountryInfo();

    }

    private void loadCountryInfo() {

        if (AppUtil.isNetworkAvailable(getActivity())) {
            swipeRefreshLayout.setRefreshing(true);
            CountryViewModel model = ViewModelProviders.of(this).get(CountryViewModel.class);
            model.getLiveInfoObj().observe(this, new Observer<AboutCountryModel>() {
                @Override
                public void onChanged(@Nullable AboutCountryModel aboutCountryModel) {

                    if (countryInfoAdapter==null){
                        countryInfoAdapter = new CountryInfoAdapter(getActivity(), aboutCountryModel.getRows());
                    }else {
                        countryInfoAdapter.updateList(aboutCountryModel.getRows());
                    }

                    rvCountryInfoList.setAdapter(countryInfoAdapter);
                    getActivity().setTitle(aboutCountryModel.getTitle());
                    swipeRefreshLayout.setRefreshing(false);

                    String jsonAboutCountry=new Gson().toJson(aboutCountryModel);
                    AppUtil.saveDataInPref(getActivity(), AppConstant.PREF_KEY_ABOUT_COUNTRY,jsonAboutCountry);
                }
            });
        } else {
            swipeRefreshLayout.setRefreshing(false);
            String json=AppUtil.getDataFromPref(getActivity(),AppConstant.PREF_KEY_ABOUT_COUNTRY);
            if (!TextUtils.isEmpty(json)){
                AboutCountryModel aboutCountryModel=new Gson().fromJson(json,AboutCountryModel.class);
                if (countryInfoAdapter==null){
                    countryInfoAdapter = new CountryInfoAdapter(getActivity(), aboutCountryModel.getRows());
                }else {
                    countryInfoAdapter.updateList(aboutCountryModel.getRows());
                }
                rvCountryInfoList.setAdapter(countryInfoAdapter);
                getActivity().setTitle(aboutCountryModel.getTitle());
            }else {
                Toast.makeText(getActivity(), R.string.network_err, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


}
