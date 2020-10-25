package com.assignment.shoppingcart;

import android.content.SharedPreferences;
import android.test.suitebuilder.annotation.SmallTest;

import com.assignment.shoppingcart.storage.CartStorage;
import com.assignment.shoppingcart.storage.DataStorage;
import com.assignment.shoppingcart.testUtils.MockModelsUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Class that tests storage and retrieval of Cart data from a normal and broken Shared Preferences
 */
@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class DataStoreUnitTest {

    @Mock
    SharedPreferences mMockSharedPreferences;
    @Mock
    SharedPreferences mMockBrokenSharedPreferences;
    @Mock
    SharedPreferences.Editor mMockEditor;
    @Mock
    SharedPreferences.Editor mMockBrokenEditor;
    private DataStorage mDataStorage;
    private String mTestString;

    @Before
    public void setUp() {

        initMocks();
    }

    @Before
    public void initMocks() {
        // Create SharedPreferenceEntry to persist.
        mTestString = MockModelsUtil.getTestCartData();

        // Create a mocked SharedPreferences.
        createMockSharedPreference();

        // Create a mocked SharedPreferences that fails at saving data.
        createBrokenMockSharedPreference();
    }

    @Test
    public void saveCartTest() {
        // Save the personal information to SharedPreferences
        mDataStorage = new DataStorage(mMockSharedPreferences);
        boolean success = mDataStorage.store(CartStorage.CART_DATA, mTestString);

        assertThat("Shared Preferences Save returns true",
                success, is(true));

        String strCartData = mDataStorage.load(CartStorage.CART_DATA);

        // Make sure both written and retrieved Cart data is equal.
        assertThat("Test that Cart data has been saved and read correctly",
                strCartData,
                is(equalTo(mTestString)));

    }

    @Test
    public void saveCartFailsTest() {
        // Read cart data from a broken SharedPreferencesHelper
        mDataStorage = new DataStorage(mMockBrokenSharedPreferences);
        boolean success =
                mDataStorage.store(CartStorage.CART_DATA, mTestString);

        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", success,
                is(false));
    }

    private void createMockSharedPreference() {
        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.
        when(mMockSharedPreferences.getString(eq(CartStorage.CART_DATA), anyString()))
                .thenReturn(mTestString);

        // Mocking a successful commit.
        when(mMockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting it.
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
    }

    /**
     * Creates a mocked SharedPreferences that fails when writing.
     */
    private void createBrokenMockSharedPreference() {
        // Mocking a commit that fails.
        when(mMockBrokenEditor.commit()).thenReturn(false);

        // Return the broken MockEditor when requesting it.
        when(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);
    }

}