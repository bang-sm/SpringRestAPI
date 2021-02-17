package com.toggle.study.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.toggle.study.util.TraceLogger;

/**
 * HTTP request logging
 *
 * There is also {@link org.springframework.web.filter.CommonsRequestLoggingFilter}  but it cannot log request method
 * And it cannot easily be extended.
 *
 * https://mdeinum.wordpress.com/2015/07/01/spring-framework-hidden-gems/
 * http://stackoverflow.com/questions/8933054/how-to-read-and-copy-the-http-servlet-response-output-stream-content-for-logging
 */
public class CustomReqResLogger extends OncePerRequestFilter {

    private int maxPayloadLength = 500;

    private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
        if (buf == null || buf.length == 0) return "";

        // response payload CharacterEncoding 이 UTF-8 인 경우는 payload 전체를 기록하고,
        //  UTF-8이 아닌 경우에는 maxPayloadLength 만큼 제한하여 기록한다.
        int length = !charsetName.equals("UTF-8") ? Math.min(buf.length, maxLength) : buf.length;

        try {
            return new String(buf, 0, length, charsetName);
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    /**
     * Log each request and response with full Request URI, content payload and duration of the request in ms.
     * @param request the request
     * @param response the response
     * @param filterChain chain of filters
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      TraceLogger traceLogger = new TraceLogger();
      // ========= Log request and response payload ("body") ========
      // We CANNOT simply read the request payload here, because then the InputStream would be consumed and cannot be read again by the actual processing/server.
      HttpServletRequest wrappedRequest = new RequestWrapper(request);
      ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

      traceRequest(wrappedRequest, traceLogger);

      filterChain.doFilter(wrappedRequest, wrappedResponse);

      traceResponse(wrappedResponse, traceLogger);

      wrappedResponse.copyBodyToResponse();
    }

    private void traceRequest(HttpServletRequest wrappedRequest, TraceLogger traceLogger) throws IOException {
        traceLogger.setDirection("IN");
        traceLogger.setType("REQ");
        traceLogger.setMethodType(wrappedRequest.getMethod());
        if (wrappedRequest.getUserPrincipal() != null) {
            traceLogger.setEntityId(wrappedRequest.getUserPrincipal().getName());
        }
        String requestUrl = URLDecoder.decode(wrappedRequest.getRequestURI(), "UTF-8");
        if (wrappedRequest.getQueryString() != null) {
            requestUrl += "?" + wrappedRequest.getQueryString();
        }
        traceLogger.setRequestURL(requestUrl);

        // 사용자 비번은 trace log에서 제외.
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map;
        String requestBody = ((RequestWrapper) wrappedRequest).convertToString();
        if (StringUtils.isNotEmpty(requestBody)) {
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

        if (wrappedRequest.getHeader("X-Device-OS") != null) {
            traceLogger.setDeviceOS(wrappedRequest.getHeader("X-Device-OS"));
        }
        if (wrappedRequest.getHeader("X-Device-OS-Version") != null) {
            traceLogger.setDeviceOSVersion(wrappedRequest.getHeader("X-Device-OS-Version"));
        }
        if (wrappedRequest.getHeader("X-Device-Model") != null) {
            traceLogger.setDeviceModel(wrappedRequest.getHeader("X-Device-Model"));
        }
        if (wrappedRequest.getHeader("X-Device-Vendor") != null) {
            traceLogger.setDeviceVendor(wrappedRequest.getHeader("X-Device-Vendor"));
        }
        if (wrappedRequest.getHeader("X-Device-App-Version") != null) {
            traceLogger.setDeviceAppVersion(wrappedRequest.getHeader("X-Device-App-Version"));
        }
        logger.info(traceLogger.toString());
    }

    private void traceResponse(ContentCachingResponseWrapper wrappedResponse, TraceLogger traceLogger) {
        traceLogger.setDirection("OU");
        traceLogger.setType("RES");
        traceLogger.setInformation(getContentAsString(wrappedResponse.getContentAsByteArray(), this.maxPayloadLength, wrappedResponse.getCharacterEncoding()));
        traceLogger.setStatusCode(wrappedResponse.getStatus());
        traceLogger.setDifferenceTime(System.currentTimeMillis() - traceLogger.getStartTime());
        logger.info(traceLogger.toString());
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

