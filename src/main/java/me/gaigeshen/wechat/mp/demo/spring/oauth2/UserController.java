package me.gaigeshen.wechat.mp.demo.spring.oauth2;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.Config;
import me.gaigeshen.wechat.mp.RequestExecutor;
import me.gaigeshen.wechat.mp.jssdk.JsApiSignatureCalculator;
import me.gaigeshen.wechat.mp.oauth2.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gaigeshen
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

  private final Config config;
  private final RequestExecutor executor;
  private final JsApiSignatureCalculator jsApiSignatureCalculator;

  public UserController(Config config, RequestExecutor executor, JsApiSignatureCalculator jsApiSignatureCalculator) {
    this.config = config;
    this.executor = executor;
    this.jsApiSignatureCalculator = jsApiSignatureCalculator;
  }

  @GetMapping
  public String authorizeUrl() {
    return "redirect:" + OAuth2AuthorizeUrlBuilder.create(false, config.getAppid(),
            "http://ggs.4kb.cn/user/info", "state").build();
  }

  @GetMapping("/info")
  public String showUserInfo(String code, String state, Model model) {
    OAuth2AccessTokenRequest accessTokenRequest = new OAuth2AccessTokenRequest(code);
    OAuth2AccessTokenResponse accessTokenResponse = executor.execute(accessTokenRequest);
    if (accessTokenResponse.isSucceeded()) {
      OAuth2UserInfoRequest userInfoRequest = new OAuth2UserInfoRequest(accessTokenResponse.getAccessToken(), accessTokenResponse.getOpenid());
      OAuth2UserInfoResponse userInfoResponse = executor.execute(userInfoRequest);
      if (userInfoResponse.isSucceeded()) {
        model.addAttribute("user", userInfoResponse);
      } else {
        log.warn("oauth2 user info get failed: " + userInfoResponse.getErrorMessage());
      }
    } else {
      log.warn("oauth2 access token get failed: " + accessTokenResponse.getErrorMessage());
    }

    // 设置该页面的分享信息

    String nonceStr = RandomStringUtils.randomAlphanumeric(16);
    long timestamp = System.currentTimeMillis() / 1000;
    String signature = jsApiSignatureCalculator.calculate(nonceStr, timestamp, "http://ggs.4kb.cn/user/info?code=" + code + "&state=" + state);

    model.addAttribute("appid", config.getAppid());
    model.addAttribute("timestamp", timestamp);
    model.addAttribute("nonceStr", nonceStr);
    model.addAttribute("signature", signature);

    return "oauth2/index";
  }

}
