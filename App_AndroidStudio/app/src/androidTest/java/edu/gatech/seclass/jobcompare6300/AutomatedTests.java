package edu.gatech.seclass.jobcompare6300;

import static android.app.PendingIntent.getActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.contrib.RecyclerViewActions;
import android.view.View;
import android.widget.TextView;

import edu.gatech.seclass.jobcompare6300.ToastTest;
import edu.gatech.seclass.jobcompare6300.helpers.RecyclerViewOfferListHelper;


@RunWith(AndroidJUnit4.class)
public class AutomatedTests {

    public static ViewAction clickItemWithId(int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule(MainActivity.class);

//    @Rule
//    public ActivityScenarioRule<addJob> activityRule2 = new ActivityScenarioRule<>(addJob.class);

    @Test
    public void Test_001_MainPage_VerifyAddIconIsDisplayed() {
        onView(withId(R.id.addJobMain)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_002_MainPage_VerifyCompareIconIsDisplayed() {
        onView(withId(R.id.compareMain)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_003_MainPage_VerifySettingsIconIsDisplayed() {
        onView(withId(R.id.settingsMain)).check(matches(isDisplayed()));
    }

    @Test
    public void Test_004_AddAndDeleteJobOffer() {
        int randomNumber = (int) Math.floor(Math.random() * 100000);
        ToastTest TT;
        TT = new ToastTest();

        // Add a Job
        onView(withId(R.id.addJobMain)).check(matches(isDisplayed()));
        onView(withId(R.id.addJobMain)).perform(click());
        onView(withId(R.id.jobDetailsTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.titleFromInput)).perform(scrollTo(), typeText(String.format("Job Test %s", randomNumber)));
        onView(withId(R.id.companyFromInput)).perform(scrollTo(), typeText("Microsoft Inc."));
        onView(withId(R.id.locationFromIInput)).perform(scrollTo(), typeText("LA"));
        onView(withId(R.id.salaryFromInput)).perform(scrollTo(), typeText("150000"));
        onView(withId(R.id.bonusFromInput)).perform(scrollTo(), typeText("5000"));
        onView(withId(R.id.leaveTimeFromInput)).perform(scrollTo(), typeText("15"));
        onView(withId(R.id.stockSharesFromInput)).perform(scrollTo(), typeText("25"));
        onView(withId(R.id.homeFundFromInput)).perform(scrollTo(), typeText("1000"));
        onView(withId(R.id.wellnessFundFromInput)).perform(scrollTo(), typeText("200"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveJobBtn)).perform(click());
        TT.successToastDisplayed();

        // Verify Job added to the list
        onView(withId(R.id.backarrowAddJob)).perform(scrollTo(), click());
        onView(withId(R.id.compareMain)).perform(click());
        onView(withId(R.id.allOffersRecicler)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(String.format("Job Test %s", randomNumber))), scrollTo()));
        onView(withText(String.format("Job Test %s", randomNumber))).check(matches(isDisplayed()));

        // Delete the added Job
        onView(withId(R.id.allOffersRecicler)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(String.format("Job Test %s", randomNumber))), clickItemWithId(R.id.deleteIconJobOfferList)));
        TT.successToastDisplayed();
    }

    @Test
    public void Test_005_AddJob_AddJobOffer() {
        int randomNumber = (int) Math.floor(Math.random() * 100000);
        // Add a Job
        onView(withId(R.id.addJobMain)).check(matches(isDisplayed()));
        onView(withId(R.id.addJobMain)).perform(click());
        onView(withId(R.id.jobDetailsTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.titleFromInput)).perform(scrollTo(), typeText(String.format("Job Test %s", randomNumber)));
        onView(withId(R.id.companyFromInput)).perform(scrollTo(), typeText("Microsoft Inc."));
        onView(withId(R.id.locationFromIInput)).perform(scrollTo(), typeText("LA"));
        onView(withId(R.id.salaryFromInput)).perform(scrollTo(), typeText("150000"));
        onView(withId(R.id.bonusFromInput)).perform(scrollTo(), typeText("5000"));
        onView(withId(R.id.leaveTimeFromInput)).perform(scrollTo(), typeText("15"));
        onView(withId(R.id.stockSharesFromInput)).perform(scrollTo(), typeText("25"));
        onView(withId(R.id.homeFundFromInput)).perform(scrollTo(), typeText("1000"));
        onView(withId(R.id.wellnessFundFromInput)).perform(scrollTo(), typeText("200"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveJobBtn)).perform(click());

        ToastTest TT;
        TT = new ToastTest();
        TT.successToastDisplayed();
    }

    @Test
    public void Test_006_AddJob_MissingFields() {
        int randomNumber = (int) Math.floor(Math.random() * 100000);

        // Add a Job
        onView(withId(R.id.addJobMain)).check(matches(isDisplayed()));
        onView(withId(R.id.addJobMain)).perform(click());
        onView(withId(R.id.jobDetailsTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.titleFromInput)).perform(scrollTo(), typeText(String.format("Job Test %s", randomNumber)));
        onView(withId(R.id.companyFromInput)).perform(scrollTo(), typeText("Microsoft Inc."));
        onView(withId(R.id.wellnessFundFromInput)).perform(scrollTo(), typeText("200"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveJobBtn)).perform(click());

        onView(withId(R.id.locationFromIInput)).check(matches(hasErrorText("Required field")));
        onView(withId(R.id.salaryFromInput)).check(matches(hasErrorText("Required field")));
        onView(withId(R.id.bonusFromInput)).check(matches(hasErrorText("Required field")));
        onView(withId(R.id.leaveTimeFromInput)).check(matches(hasErrorText("Required field")));
        onView(withId(R.id.stockSharesFromInput)).check(matches(hasErrorText("Required field")));
        onView(withId(R.id.homeFundFromInput)).check(matches(hasErrorText("Required field")));
    }

    @Test
    public void Test_007_ComparisonSettings_InvalidValues() {
        ToastTest TT;
        TT = new ToastTest();

        onView(withId(R.id.addJobMain)).check(matches(isDisplayed()));
        onView(withId(R.id.settingsMain)).perform(click());
        onView(withId(R.id.comparisonSettingsTitle)).check(matches(isDisplayed()));

        // Populate with invalid values
        onView(withId(R.id.yearlySalaryWt)).perform(clearText(), typeText("2.5"));
        onView(withId(R.id.yearlyBonusWt)).perform(clearText(), typeText("2.5"));
        onView(withId(R.id.leaveTimeWt)).perform(clearText(), typeText("2.5"));
        onView(withId(R.id.sharesOfferedWt)).perform(clearText(), typeText("2.5"));
        onView(withId(R.id.homeFundWt)).perform(clearText(), typeText("2.5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.wellnessFundWt)).perform(clearText(), typeText("2.5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.saveComparisonButton)).perform(click());

        TT.invalidComparisonSettingsInputDisplayed();

        onView(withId(R.id.yearlySalaryWt)).check(matches(hasErrorText("Please provide correct value.")));
        onView(withId(R.id.yearlyBonusWt)).check(matches(hasErrorText("Please provide correct value.")));
        onView(withId(R.id.leaveTimeWt)).check(matches(hasErrorText("Please provide correct value.")));
        onView(withId(R.id.sharesOfferedWt)).check(matches(hasErrorText("Please provide correct value.")));
        onView(withId(R.id.homeFundWt)).check(matches(hasErrorText("Please provide correct value.")));
        onView(withId(R.id.wellnessFundWt)).check(matches(hasErrorText("Please provide correct value.")));
    }
}

