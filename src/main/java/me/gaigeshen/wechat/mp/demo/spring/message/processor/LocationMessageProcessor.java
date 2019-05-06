package me.gaigeshen.wechat.mp.demo.spring.message.processor;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 地理位置消息处理
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class LocationMessageProcessor extends TypedMessageProcessor<LocationMessage> {
  @Override
  protected void doProcessTyped(LocationMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    log.info("I received location by LocationMessageProcessor");
    response.write(new TextReplyMessage(message.getFromUserName(), message.getToUserName(),
            "我是服务器：你发送的位置是：" + message.getLabel() + "(" + message.getLocation_X() + ", " + message.getLocation_Y() + ")"));
  }

  @Override
  public int order() {
    return 1;
  }
}
