package me.gaigeshen.wechat.mp.demo.spring.message.processor.event;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.eventpush.SubscribeEventMessage;
import me.gaigeshen.wechat.mp.message.eventpush.TypedEventMessageProcessor;
import org.springframework.stereotype.Component;

/**
 * 处理关注事件
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class SubscribeEventMessageProcessor extends TypedEventMessageProcessor<SubscribeEventMessage> {
  @Override
  protected void doProcessTyped(SubscribeEventMessage message) {
    log.info("I received subscribe event message by SubscribeEventMessageProcessor");
    log.info("Subscribe from: " + message.getFromUserName());

    // 如果是扫描带参二维码的话，就有以下两个值
    log.info("Event key:  " + message.getEventKey());
    log.info("Ticket  " + message.getTicket());
  }

  @Override
  public int order() {
    return 0;
  }
}
