package me.gaigeshen.wechat.mp.demo.spring;

import me.gaigeshen.wechat.mp.Config;
import me.gaigeshen.wechat.mp.message.MessageProcessorChain;
import me.gaigeshen.wechat.mp.message.ServletMessageRequest;
import me.gaigeshen.wechat.mp.message.ServletMessageSourceValidator;
import me.gaigeshen.wechat.mp.message.ServletReplyMessageResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信的消息会被此过滤器处理，请将此链接配置到微信管理后台
 *
 * @author gaigeshen
 */
@WebFilter("/messages")
public class MessageFilter implements Filter {

  @Autowired
  private Config config;

  @Autowired
  private MessageProcessorChain processorChain;

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    if (!(req instanceof HttpServletRequest)) {
      chain.doFilter(req, resp);
    } else {
      // 可能需要验证消息来源
      if (StringUtils.isNotBlank(req.getParameter("echostr"))) {
        ServletMessageSourceValidator.create(config, (HttpServletRequest) req, (HttpServletResponse) resp).doValidateAndRespond();
        return;
      }

      // 处理消息
      processorChain.doProcess(
          ServletMessageRequest.create(config, (HttpServletRequest) req).getMessage(),
          ServletReplyMessageResponse.create(config, (HttpServletResponse) resp));
    }
  }
}
