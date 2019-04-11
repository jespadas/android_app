package com.julioespadas.myapp.model;

import android.os.SystemClock;

public class WSUtils {

    public static EleveBean loadEleveFromWeb() throws Exception {
        SystemClock.sleep(3000);
        return new EleveBean("Snow", "John");
    }
}
