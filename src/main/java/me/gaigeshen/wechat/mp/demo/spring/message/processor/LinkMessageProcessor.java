package me.gaigeshen.wechat.mp.demo.spring.message.processor;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 链接消息处理器
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class LinkMessageProcessor extends TypedMessageProcessor<LinkMessage> {
  @Override
  protected void doProcessTyped(LinkMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    log.info("I received link by LinkMessageProcessor");
    response.write(new TextReplyMessage(message.getFromUserName(), message.getToUserName(), "服务器：" + message.getUrl()));
  }

  @Override
  public int order() {
    return 1;
  }
}
