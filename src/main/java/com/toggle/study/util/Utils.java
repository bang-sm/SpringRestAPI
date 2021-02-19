package com.toggle.study.util;

import java.beans.FeatureDescriptor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class Utils {
  static Logger logger = LoggerFactory.getLogger(Utils.class);

  //등록ID생성 PK
  public static String getRandomCustQustRegId(){
      //////등록ID만드는 함수로 변경예정//////
      Random r = new Random();
      StringBuilder sb = new StringBuilder(4);

      for(int i = 0; i < 4; i++) {
          char tmp = (char) ('a' + r.nextInt('z' - 'a'));
          sb.append(tmp);
      }

      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      Date srvrTime = new Date();

      String time = format.format(srvrTime);
      //고객문의등록ID생성
      String id=time+sb.toString();
      return id;
  }
  
  public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    return Stream.of(wrappedSource.getPropertyDescriptors())
	        .map(FeatureDescriptor::getName)
	        .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
	        .toArray(String[]::new);
  }
}
