package com.example.levonovo.peta;

import android.app.Instrumentation;
import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Instrumentation InstrumentationRegistry = null;
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.levonovo.peta", appContext.getPackageName());
    }
}
