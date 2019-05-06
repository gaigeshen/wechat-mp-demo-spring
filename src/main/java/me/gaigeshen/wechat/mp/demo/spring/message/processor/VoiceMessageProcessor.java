package me.gaigeshen.wechat.mp.demo.spring.message.processor;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 语音消息处理
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class VoiceMessageProcessor extends TypedMessageProcessor<VoiceMessage> {
  @Override
  protected void doProcessTyped(VoiceMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    log.info("I received voice by VoiceMessageProcessor");
    response.write(new VoiceReplyMessage(message.getFromUserName(), message.getToUserName(),
            new VoiceReplyMessage.Voice(message.getMediaId())));
  }

  @Override
  public int order() {
    return 1;
  }
}
