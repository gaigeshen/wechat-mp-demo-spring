package me.gaigeshen.wechat.mp.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WechatMpDemoSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(WechatMpDemoSpringApplication.class, args);
  }

}
