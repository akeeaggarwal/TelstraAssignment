package assignment.telstra.com.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import assignment.telstra.com.Utility.AppUtil;

import static org.junit.Assert.*;

public class AboutCountryFragmentTest {


    @Mock
    AboutCountryFragment aboutCountryFragment;

    @Test
    public void networkTest() {
        assertTrue(AppUtil.isNetworkAvailable(aboutCountryFragment.getActivity()));
    }

    @Test
    public void methodCallingTest(){
        if (AppUtil.isNetworkAvailable(aboutCountryFragment.getActivity())){
            aboutCountryFragment.loadCountryInfo();
            Mockito.verify(aboutCountryFragment,Mockito.times(1)).loadCountryInfo();
        }

    }


}