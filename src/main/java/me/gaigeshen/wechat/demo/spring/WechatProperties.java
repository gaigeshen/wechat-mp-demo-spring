package me.gaigeshen.wechat.demo.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gaigeshen
 */
@Data
@ConfigurationProperties("wechat.mp")
public class WechatProperties {
  private String appid;
  private String secret;
  private String token;
  private String encodingAesKey;
}
