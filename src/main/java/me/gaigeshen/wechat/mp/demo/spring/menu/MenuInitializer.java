package me.gaigeshen.wechat.mp.demo.spring.menu;

import lombok.extern.slf4j.Slf4j;
import me.gaigeshen.wechat.mp.RequestExecutor;
import me.gaigeshen.wechat.mp.menu.Menu;
import me.gaigeshen.wechat.mp.menu.MenuCreateRequest;
import me.gaigeshen.wechat.mp.menu.MenuCreateResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 菜单初始化
 *
 * @author gaigeshen
 */
@Slf4j
@Component
public class MenuInitializer {

  private final RequestExecutor executor;

  public MenuInitializer(RequestExecutor executor) {
    this.executor = executor;
  }

  @PostConstruct
  public void init() {
    MenuCreateRequest request = MenuCreateRequest.builder()
            .menus(new Menu[]{
                    Menu.click("点我试试", "tryclickme")
            })
            .build();
    MenuCreateResponse response = executor.execute(request);
    log.info("menu initialized");
    log.info("error code: " + response.getErrorCode());
    log.info("error message: " + response.getErrorMessage());
  }

}
