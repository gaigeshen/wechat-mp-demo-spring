package me.gaigeshen.wechat.mp.demo.spring.message.processor.event;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.MessageProcessorChain;
import me.gaigeshen.wechat.mp.message.ReplyMessageResponse;
import me.gaigeshen.wechat.mp.message.TextReplyMessage;
import me.gaigeshen.wechat.mp.message.TypedMessageProcessor;
import me.gaigeshen.wechat.mp.message.eventpush.ClickEventMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 另一种点击菜单按钮事件的处理方式，这种方式可以让服务器回复给用户，确定要这样做，请了解其中的缘由，因为有些事件是可以回复而有些不可以
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class ClickEventMessageReplyProcessor extends TypedMessageProcessor<ClickEventMessage> {
  @Override
  protected void doProcessTyped(ClickEventMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    log.info("click event message received by ClickEventMessageReplyProcessor");
    log.info("event key is: " + message.getEventKey());

    // 我开始回复消息
    response.write(new TextReplyMessage(message.getFromUserName(), message.getToUserName(),
            "我是服务器，你点击了我： " + message.getEventKey()));
  }

  @Override
  public int order() {
    return 1;
  }
}
