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
public class TextMessageFirstProcessor extends TypedMessageProcessor<TextMessage> {
  @Override
  protected void doProcessTyped(TextMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {

    // 假定做了一些逻辑处理
    log.info("do somthing by TextMessageFirstProcessor");


    // 然后交给下个处理器去处理吧
    processorChain.doProcess(message, response);
  }

  @Override
  public int order() {
    return 1;
  }
}
