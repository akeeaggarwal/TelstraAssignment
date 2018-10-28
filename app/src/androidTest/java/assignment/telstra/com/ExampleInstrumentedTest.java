package assignment.telstra.com;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import assignment.telstra.com.Utility.AppUtil;
import assignment.telstra.com.view.AboutCountryFragment;
import assignment.telstra.com.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Mock
    AboutCountryFragment aboutCountryFragment;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("assignment.telstra.com", appContext.getPackageName());
    }


    @Test
    public void shouldShowToast() {
        if (!AppUtil.isNetworkAvailable(aboutCountryFragment.getActivity())) {
            aboutCountryFragment.loadCountryInfo();
            onView(withText(R.string.network_err)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        }
    }


    @Test
    public void methodCallingTest(){
        if (AppUtil.isNetworkAvailable(aboutCountryFragment.getActivity())){
            aboutCountryFragment.loadCountryInfo();
            Mockito.verify(aboutCountryFragment,Mockito.times(1)).loadCountryInfo();
        }

    }

}
