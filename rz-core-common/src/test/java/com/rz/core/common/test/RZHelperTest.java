package com.rz.core.common.test;

import com.rz.core.common.RZHelper;

public class RZHelperTest {
    public static void main(String[] args) {
        RZHelperTest rzHelperTest = new RZHelperTest();
        try {
            rzHelperTest.test();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End HelperTest...");
    }

    private void test() {
        try {
            String value = RZHelper.encrypt("asd");
            System.out.println(value);
            value = RZHelper.decrypt(value);
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
