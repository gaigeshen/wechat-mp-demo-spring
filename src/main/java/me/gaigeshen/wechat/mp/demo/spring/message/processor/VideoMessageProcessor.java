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
public class VideoMessageProcessor extends TypedMessageProcessor<VideoMessage> {
  @Override
  protected void doProcessTyped(VideoMessage message, ReplyMessageResponse response, MessageProcessorChain processorChain) throws IOException {
    log.info("I received video by VideoMessageProcessor");
    response.write
            (new VideoReplyMessage(message.getFromUserName(), message.getToUserName(),
                    new VideoReplyMessage.Video(message.getMediaId(), "this is video title", "this is video description")));
  }

  @Override
  public int order() {
    return 1;
  }
}
