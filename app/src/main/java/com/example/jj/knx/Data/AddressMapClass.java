package com.example.jj.knx.Data;

import android.util.ArrayMap;

import com.example.jj.knx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juhász József on 2018. 03. 24.
 */

public class AddressMapClass {

    private static Map<Integer, String> addressMap = new ArrayMap<>();

    private static boolean firstRun = true;
    public static void initialize() {
        if(firstRun) {
            addressMap.put(R.id.EditText1, "0/3/0");
            addressMap.put(R.id.EditText2, "0/3/1");
            addressMap.put(R.id.EditText3, "0/3/2");
            addressMap.put(R.id.EditText4, "0/3/3");
            firstRun = false;
        }
    }

    public static String getAddress(int id) {
        return addressMap.get(id);
    }

    public static Map<Integer, String> getAddressMap() {
        return addressMap;
    }

    public static void test() {
        for(Map.Entry<Integer, String> a : addressMap.entrySet()) {
            a.getValue();
        }
    }

}
