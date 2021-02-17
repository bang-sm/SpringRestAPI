package com.toggle.study.interceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.toggle.study.util.TraceLogger;

/**
 * Spring RestTemplate 로깅 인터셉터
 */
@Component
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private String authServerUrl1 = "";

    private String authServerUrl2 = "";

    private String authzServerUrl1 = "";

    private String authzServerUrl2 = "";

    public RestTemplateLoggingInterceptor() {
        this("", "", "", "");
    }

    public RestTemplateLoggingInterceptor(String authServerUrl1, String authServerUrl2) {
        this.authServerUrl1 = authServerUrl1;
        this.authServerUrl2 = authServerUrl2;
    }

    public RestTemplateLoggingInterceptor(String authServerUrl1, String authServerUrl2, String authzServerUrl1, String authzServerUrl2) {
        this.authServerUrl1 = authServerUrl1;
        this.authServerUrl2 = authServerUrl2;
        this.authzServerUrl1 = authzServerUrl1;
        this.authzServerUrl2 = authzServerUrl2;
    }

    /**
     * <pre>
     * RestTemplate 로깅 Interceptor
     * <pre>
     * @param request HttpRequest
     * @param body Request Body
     * @param execution ClientHttpRequestExecution
     * @throws IOException */
    @Override public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        String requestURL = request.getURI().toString();
        // auth의 토큰 관련 API와 authz의 권한 체크 API는 연동 로그에서 제외
        if (requestURL.startsWith(authServerUrl1 + "/oauth/check_token") || requestURL.startsWith(authServerUrl2 + "/oauth/check_token") || (requestURL.startsWith(authzServerUrl1) && request.getMethod().matches("POST") && requestURL.contains("check")) || (requestURL.startsWith(authzServerUrl2) && request.getMethod().matches("POST") && requestURL.contains("check"))) {
            ClientHttpResponse response = execution.execute(request, body);
            return response;
        } else {
            TraceLogger traceLogger = new TraceLogger();
            // request log
            traceRequest(request, body, traceLogger);
            // execute
            ClientHttpResponse response = execution.execute(request, body);
            // response log
            traceResponse(response, traceLogger);
            return response;
        }

    }

    /**
     * <pre>
     * RestTemplate Request 로깅
     * <pre>
     * @param request HttpRequest
     * @param body Request Body
     */
    private void traceRequest(HttpRequest request, byte[] body, TraceLogger traceLogger) throws IOException {
        if (logger.isInfoEnabled()) {
            StringBuilder builder = new StringBuilder();

            traceLogger.setDirection("OU");
            traceLogger.setType("REQ");
            traceLogger.setMethodType(request.getMethod().toString());
            traceLogger.setRequestURL(request.getURI().toString());

            if (body.length > 0 && hasTextBody(request.getHeaders())) {
                String bodyText = new String(body, StandardCharsets.UTF_8);
                builder.append(bodyText);

                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map;
                String requestBody = builder.toString();

                map = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>(){});
                List<String> keys  = Lists.newArrayList();
                List<String> result = findKeys(map, keys);
                for (String s : result) {
                    if (StringUtils.containsIgnoreCase(s, "password")) {
                        map.remove(s);
                    }
                }
                if (!map.isEmpty()) {
                    traceLogger.setInformation(objectMapper.writeValueAsString(map));
                }
            }
            logger.info(traceLogger.toString());
        }
    }

    /**
     * <pre>
     * RestTemplate Response 로깅
     * <pre>
     * @param response ClientHttpResponse
     * @throws IOException
     */
    private void traceResponse(ClientHttpResponse response, TraceLogger traceLogger) throws IOException {
        traceLogger.setDirection("IN");
        traceLogger.setType("RES");
        traceLogger.setInformation(StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        traceLogger.setStatusCode(response.getRawStatusCode());
        traceLogger.setDifferenceTime(System.currentTimeMillis() - traceLogger.getStartTime());
        logger.info(traceLogger.toString());
    }

    private boolean hasTextBody(HttpHeaders headers) {
        MediaType contentType = headers.getContentType();
        if (contentType != null) {
            if ("text".equals(contentType.getType())) {
                return true;
            }
            String subtype = contentType.getSubtype();
            if (subtype != null) {
                return "xml".equals(subtype) || "json".equals(subtype) ||
                        subtype.endsWith("+xml") || subtype.endsWith("+json");
            }
        }
        return false;
    }

    private List<String> findKeys(Map<String, Object> treeMap , List<String> keys) {
        //request body의 keyName 조회
        treeMap.forEach((key, value) -> {
            if (value instanceof LinkedHashMap) {
                Map<String, Object> map = (LinkedHashMap) value;
                findKeys(map, keys);
            }
            keys.add(key);
        });
        return keys;
    }
}
