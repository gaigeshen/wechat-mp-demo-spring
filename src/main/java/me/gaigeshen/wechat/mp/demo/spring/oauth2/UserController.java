package me.gaigeshen.wechat.mp.demo.spring.oauth2;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.Config;
import me.gaigeshen.wechat.mp.RequestExecutor;
import me.gaigeshen.wechat.mp.oauth2.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author gaigeshen
 */
@Slf4j
@Controller
@RequestMapping("/oauth2")
public class UserController {

  private final Config config;
  private final RequestExecutor executor;

  public UserController(Config config, RequestExecutor executor) {
    this.config = config;
    this.executor = executor;
  }

  @GetMapping
  public String authorizeUrl(UriComponentsBuilder builder) {
    return "redirect:" + OAuth2AuthorizeUrlBuilder.create(false, config.getAppid(),
            builder.path("/oauth2/info").build().toUriString(), "state").build();
  }

  @GetMapping("/info")
  public String showUserInfo(String code, Model model) {
    OAuth2AccessTokenRequest accessTokenRequest = new OAuth2AccessTokenRequest(code);
    OAuth2AccessTokenResponse accessTokenResponse = executor.execute(accessTokenRequest);
    if (accessTokenResponse.isSucceeded()) {
      OAuth2UserInfoRequest userInfoRequest = new OAuth2UserInfoRequest(accessTokenResponse.getAccessToken(), accessTokenResponse.getOpenid());
      OAuth2UserInfoResponse userInfoResponse = executor.execute(userInfoRequest);
      if (userInfoResponse.isSucceeded()) {
        model.addAttribute(userInfoResponse);
      } else {
        log.warn("oauth2 user info get failed: " + userInfoResponse.getErrorMessage());
      }
    } else {
      log.warn("oauth2 access token get failed: " + accessTokenResponse.getErrorMessage());
    }

    return "oauth2/index";
  }

}
