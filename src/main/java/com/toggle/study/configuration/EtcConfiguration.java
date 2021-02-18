package com.toggle.study.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.toggle.study.filter.CustomReqResLogger;
import com.toggle.study.interceptor.PlusEncoderInterceptor;
import com.toggle.study.interceptor.RestTemplateLoggingInterceptor;

@Configuration
@EnableConfigurationProperties
public class EtcConfiguration {

  @Bean
  public RetryTemplate retryTemplate() {
    
    RetryTemplate retryTemplate = new RetryTemplate();
    // retry 간격 설정
    FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(0l);
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    // retry를 시도할 특정 Exception 을 설정(아래의 경우 ResourceAccessException 발생 시 만 retry 시도)
    Map<Class<? extends Throwable>, Boolean> retryableExceptions = 
        new HashMap<>();
    retryableExceptions.put(ResourceAccessException.class, true);
    // 재시도 최대 횟수를 2번으로 설정(restTemplate 호출을 2번 함)
    retryTemplate.setRetryPolicy(new SimpleRetryPolicy(2, retryableExceptions));
    return retryTemplate;
  }

  /**
   * Customized RestTemplate
   * 
   * @return
   */
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();

    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(5000);
    factory.setConnectTimeout(5000);
    factory.setConnectionRequestTimeout(5000);
    // @formatter:off
//    HttpClient httpClient = HttpClientBuilder.create()
//            .setMaxConnTotal(10000)
//            .setMaxConnPerRoute(1000)
//            .build();
//    factory.setHttpClient(httpClient);
    // @formatter:on

    restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
    if (interceptors.isEmpty()) {
      interceptors = new ArrayList<>();
    }
    interceptors.add(new RestTemplateLoggingInterceptor("", "", "", ""));
    interceptors.add(new PlusEncoderInterceptor());
    restTemplate.setInterceptors(interceptors);

    return restTemplate;
  }
  /**
   * Request & Response logging Bean
   *
   * @return
   */
  @Bean
  public CustomReqResLogger logFilter() {
    CustomReqResLogger filter = new CustomReqResLogger();
    return filter;
  }

}
