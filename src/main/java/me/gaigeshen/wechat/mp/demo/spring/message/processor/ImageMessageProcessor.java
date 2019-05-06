package me.gaigeshen.wechat.mp.demo.spring.message.processor;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 处理普通图片消息
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class ImageMessageProcessor extends TypedMessageProcessor<ImageMessage> {
  @Override
  protected void doProcessTyped(ImageMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    // 用户发了图片给我
    // 那么我也回复该图片回去吧
    log.info("I received image by ImageMessageProcessor");

    response.write(new ImageReplyMessage(message.getFromUserName(), message.getToUserName(),
            new ImageReplyMessage.Image(message.getMediaId())));
  }

  @Override
  public int order() {
    return 1;
  }
}
