package co.com.ceiba.mobile.pruebadeingreso

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.com.ceiba.mobile.pruebadeingreso.view.activities.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var stringToBeTyped: String
    private lateinit var textButton: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private fun sleep(time: Long) = apply {
        Thread.sleep(time)
    }

    object MyViewAction {

        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<Button>(id)
                    v.performClick()
                }
            }
        }

    }

    @Before
    fun initValidString() {
        stringToBeTyped = "Ervin"
        textButton = "Ver publicaciones"
    }

    @Test
    fun setNameUser_MainActivity() {
        sleep(1000)
        onView(withId(R.id.editTextSearch))
            .perform(typeText(stringToBeTyped), closeSoftKeyboard())

        sleep(2000)

        onView(withId(R.id.recyclerViewSearchResults))
            .inRoot(RootMatchers.withDecorView(
                Matchers.`is`(activityRule.activity.window.decorView)
            )).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, MyViewAction.clickChildViewWithId(R.id.btn_view_post)))

        sleep(3000)
    }

}