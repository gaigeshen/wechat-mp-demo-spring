package me.gaigeshen.wechat.mp.demo.spring;

import me.gaigeshen.wechat.mp.Config;
import me.gaigeshen.wechat.mp.RequestExecutor;
import me.gaigeshen.wechat.mp.commons.HttpClientExecutor;
import me.gaigeshen.wechat.mp.jssdk.JsApiSignatureCalculator;
import me.gaigeshen.wechat.mp.message.DefaultMessageProcessorChain;
import me.gaigeshen.wechat.mp.message.MessageProcessor;
import me.gaigeshen.wechat.mp.message.MessageProcessorChain;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties(WechatProperties.class)
@Configuration
public class WechatConfiguration {

  private final WechatProperties properties;

  public WechatConfiguration(WechatProperties properties) {
    this.properties = properties;
  }

  // 用于消息处理
  @Bean
  public MessageProcessorChain messageProcessorChain(ApplicationContext ctx) {
    // 获取所有的消息处理器
    // 包含所有的事件消息处理器
    Map<String, MessageProcessor> processors = ctx.getBeansOfType(MessageProcessor.class);
    return new DefaultMessageProcessorChain(new ArrayList<>(processors.values()));
  }

  // 网页开发相关
  @Bean
  public JsApiSignatureCalculator jsApiSignatureCalculator(RequestExecutor executor) {
    return new JsApiSignatureCalculator(executor);
  }

  // 用于普通接口调用
  @Bean(destroyMethod = "close")
  public RequestExecutor requestExecutor(Config config) {
    return new RequestExecutor(
            new HttpClientExecutor(2000, 2000, 3000),
            config);
  }

  @Bean
  public Config config() {
    return Config.builder()
            .appid(properties.getAppid())
            .secret(properties.getSecret())
            .token(properties.getToken())
            .encodingAesKey(properties.getEncodingAesKey())
            .build();
  }

}
