/**
 * 1. 建议继承 {@link me.gaigeshen.wechat.mp.message.TypedMessageProcessor}，这样的话，可以只关注指定类型的消息处理<br>
 * 2. 消息处理器是可以有顺序的，请查阅 {@code MessageProcessor#order()}<br>
 * 3. 支持多个消息处理器处理同一种类型的消息，但请注意，只有一个处理器能够回复消息。实现方式请参考过滤器模式。<br>
 * 4. 事件类的消息，不允许回复，建议继承 {@link me.gaigeshen.wechat.mp.message.eventpush.TypedEventMessageProcessor}<br>
 * 5. 如果事件类的消息需要回复的话，请继承 {@link me.gaigeshen.wechat.mp.message.TypedMessageProcessor}<br>
 * 5. 所有的消息类型和事件消息类型请查看 {@link me.gaigeshen.wechat.mp.message.MessageTypeDeclarer}
 *
 * @author gaigeshen
 */
package me.gaigeshen.wechat.mp.demo.spring.message.processor;