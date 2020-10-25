package com.assignment.shoppingcart;

import android.content.Context;
import android.content.SharedPreferences;

import com.assignment.shoppingcart.storage.DataStorage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * ToDo Need to get the tests working correctly
 */
public class DataStoreUnitTest {

    private Context mockedContext;
    private DataStorage mDataStorage;
    private SharedPreferences mockedSharedPreference;
    private SharedPreferences.Editor mockedEditor;

    @Before
    public void setUp() {
        mockedContext = Mockito.mock(Context.class);
        mDataStorage = new DataStorage(mockedContext);
        mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        mockedEditor = Mockito.mock(SharedPreferences.Editor.class);
        Mockito.when(mockedSharedPreference.edit()).thenReturn(mockedEditor);
    }

    @Test
    public void storeDataTest() throws Exception {

        String testString = "Test Data";
        String column = "Test Column";
        mDataStorage.store(column, testString);
        Mockito.verify(mockedEditor).apply();
        String loadedString = mDataStorage.load(column);
        assertEquals(testString,loadedString);

    }



}