package me.vodarga.ui.selenide.ui.page;

import lombok.Getter;
import lombok.experimental.Accessors;
import me.vodarga.ui.selenide.ui.component.Header;

@Getter
@Accessors(fluent = true)
public abstract class BasePage {

  public final Header header = new Header();

  public abstract String pagePath() ;

}
