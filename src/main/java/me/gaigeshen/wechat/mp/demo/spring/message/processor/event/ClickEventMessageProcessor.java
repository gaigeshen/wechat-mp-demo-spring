package me.gaigeshen.wechat.mp.demo.spring.message.processor.event;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.eventpush.ClickEventMessage;
import me.gaigeshen.wechat.mp.message.eventpush.TypedEventMessageProcessor;
import org.springframework.stereotype.Component;

/**
 * 点击菜单按钮事件
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class ClickEventMessageProcessor extends TypedEventMessageProcessor<ClickEventMessage> {
  @Override
  protected void doProcessTyped(ClickEventMessage message) {
    log.info("click event message received by ClickEventMessageProcessor");
    log.info("event key is: " + message.getEventKey());
  }

  @Override
  public int order() {
    // 务必将事件处理类型的顺序放在可以回复消息的处理器后面
    // 因为一旦被此处理器处理，将无法将消息给到下个处理器
    return 2;
  }
}
