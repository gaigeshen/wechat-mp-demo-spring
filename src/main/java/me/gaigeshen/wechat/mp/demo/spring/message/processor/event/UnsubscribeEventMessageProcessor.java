package me.gaigeshen.wechat.mp.demo.spring.message.processor.event;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.eventpush.TypedEventMessageProcessor;
import me.gaigeshen.wechat.mp.message.eventpush.UnsubscribeEventMessage;
import org.springframework.stereotype.Component;

/**
 * 处理取消关注事件
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class UnsubscribeEventMessageProcessor extends TypedEventMessageProcessor<UnsubscribeEventMessage> {
  @Override
  protected void doProcessTyped(UnsubscribeEventMessage message) {
    log.info("I received unsubscribe event message by UnsubscribeEventMessageProcessor");
    log.info("Unsubscribe from: " + message.getFromUserName());
  }

  @Override
  public int order() {
    return 0;
  }
}
