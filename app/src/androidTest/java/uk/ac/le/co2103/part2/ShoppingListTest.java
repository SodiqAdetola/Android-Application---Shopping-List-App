package uk.ac.le.co2103.part2;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class ShoppingListTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddNewList() {
        fail("Not implemented yet.");
    }

    @Test
    public void testDeleteList() {
        fail("Not implemented yet.");
    }

    @Test
    public void testAddProduct() {
        fail("Not implemented yet.");
    }

    @Test
    public void testAddDuplicateProduct() {
        fail("Not implemented yet.");
    }

    @Test
    public void testEditProduct() {
        fail("Not implemented yet.");
    }

    @Test
    public void testDeleteProduct() {
        fail("Not implemented yet.");
    }
}