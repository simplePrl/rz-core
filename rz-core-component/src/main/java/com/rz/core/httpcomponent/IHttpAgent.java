package com.rz.core.httpcomponent;

import java.io.IOException;
import java.util.Map;

public interface IHttpAgent {
    String get(String uri) throws IOException;

    String get(String uri, Map<String, String> headers) throws IOException;

    String delete(String uri) throws IOException;

    String delete(String uri, Map<String, String> headers) throws IOException;

    String post(String uri, String body) throws IOException;

    String post(String uri, String body, Map<String, String> headers) throws IOException;

    String put(String uri, String body) throws IOException;

    String put(String uri, String body, Map<String, String> headers) throws IOException;
}
