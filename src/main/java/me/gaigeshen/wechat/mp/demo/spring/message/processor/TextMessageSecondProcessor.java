package me.gaigeshen.wechat.mp.demo.spring.message.processor;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 回复普通文本消息
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class TextMessageSecondProcessor extends TypedMessageProcessor<TextMessage> {
  @Override
  protected void doProcessTyped(TextMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {

    // 假定需要当前的处理器回复消息
    // 既然已经决定要回复消息了，如果交给下个处理器处理，那么下个处理器，是无法再继续回复了，当然只针对符合处理条件的处理器
    // 不符合条件的处理器（即接受到的消息不是文本消息类型），是不会去处理的

    log.info("reply message by TextMessageSecondProcessor");
    response.write(
            // 原封不动回复内容
            new TextReplyMessage(message.getFromUserName(), message.getToUserName(), "我是服务器：" + message.getContent()));
  }

  @Override
  public int order() {
    return 2;
  }
}
