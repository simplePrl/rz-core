package com.rz.core.utils.test;

import com.rz.core.HttpAgent;

public class HttpAgentTest {
    public static void main(String[] args) {
        HttpAgentTest HttpAgentTest = new HttpAgentTest();
        try {
            HttpAgentTest.test();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End DateTimeUtilsTest...");
    }

    private void test() {
        HttpAgent httpAgent = new HttpAgent();
        
        //httpAgent.get(uri)
    }
}
