package me.gaigeshen.wechat.demo.spring.message.processor;

import me.gaigeshen.wechat.mp.message.*;

import java.io.IOException;

/**
 * 回复普通文本消息
 *
 * @author gaigeshen
 */
public class TextMessageFirstProcessor extends TypedMessageProcessor<TextMessage> {
  @Override
  protected void doProcessTyped(TextMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    response.write(
            // 原封不动回复内容
            new TextReplyMessage(message.getFromUserName(), message.getToUserName(), message.getContent()));
  }

  @Override
  public int order() {
    return 0;
  }
}
