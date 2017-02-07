package com.rz.core.utils.test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.slf4j.MDC;

import com.rz.core.httpcomponent.HttpAgent;
import com.rz.core.httpcomponent.HttpAgentImpl;
import com.rz.core.httpcomponent.HttpDecompressionMethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpAgentTest {
    private static HttpAgent httpAgent = new HttpAgentImpl(Arrays.asList(
            // "http://localhost:5000"
            // "http://localhost/YesHJ.NotifyCenter.WebHost"
            "http://qa.notify-center-base.intra.yeshj.com"
    // "http://10.20.10.57:8033"
    // "http://ptpass.hjapi.com/center"
    // "http://ptpass.hjapi.com"
    // "http://localhost"
    // "http://10.20.10.57:8034"
    ), 20 * 1000, null, HttpDecompressionMethods.Both, "application/json", StandardCharsets.UTF_8.name(), "application/json;charset=utf-8");

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
        MDC.put("requestId", "requestid1111");
        MDC.put("serverIp", "serverIp1111");

        for (int i = 0; i < 100; i++) {
            log.error("asdasdasdasd");
        }
        
        MDC.clear();
        
        //httpAgent.delete(uri)
        // httpAgent.get(uri)
    }
}
