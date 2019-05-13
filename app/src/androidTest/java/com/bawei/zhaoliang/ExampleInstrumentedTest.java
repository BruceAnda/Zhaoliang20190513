package com.bawei.zhaoliang;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.bawei.zhaoliang.net.HttpUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = ExampleInstrumentedTest.class.getSimpleName();

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bawei.zhaoliang", appContext.getPackageName());
    }

    @Test
    public void testHttpUtil() {
        HttpUtil.getInstance().httpGet("https://code.aliyun.com/598254259/FristProject/raw/master/bw_list.txt", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
