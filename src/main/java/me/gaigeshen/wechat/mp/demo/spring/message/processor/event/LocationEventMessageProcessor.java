package me.gaigeshen.wechat.mp.demo.spring.message.processor.event;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.message.eventpush.LocationEventMessage;
import me.gaigeshen.wechat.mp.message.eventpush.TypedEventMessageProcessor;
import org.springframework.stereotype.Component;

/**
 * 处理地理位置事件
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class LocationEventMessageProcessor extends TypedEventMessageProcessor<LocationEventMessage> {
  @Override
  protected void doProcessTyped(LocationEventMessage message) {
    log.info("I received location event message by LocationEventMessageProcessor");
    log.info("lat: " + message.getLatitude() + ", lng: " + message.getLongitude());
  }

  @Override
  public int order() {
    return 0;
  }
}
