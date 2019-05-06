package me.gaigeshen.wechat.mp.demo.spring.message.processor.event;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.eventpush.TypedEventMessageProcessor;
import me.gaigeshen.wechat.mp.message.eventpush.ViewEventMessage;
import org.springframework.stereotype.Component;

/**
 * 点击跳转链接的事件处理
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class ViewEventMessageProcessor extends TypedEventMessageProcessor<ViewEventMessage> {
  @Override
  protected void doProcessTyped(ViewEventMessage message) {
    log.info("view event message received by ViewEventMessageProcessor");
    log.info("event key is: " + message.getEventKey());
  }

  @Override
  public int order() {
    return 1;
  }
}
