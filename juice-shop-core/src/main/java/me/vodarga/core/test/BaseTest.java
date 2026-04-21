package me.vodarga.core.test;

import static me.vodarga.core.config.AppConfig.APP_CFG;

import com.github.javafaker.Faker;
import me.vodarga.core.JuiceShopExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

public abstract class BaseTest {

  @RegisterExtension
  protected static final JuiceShopExtension appExtension = new JuiceShopExtension(APP_CFG.image(), APP_CFG.port());
  protected static final Faker faker = new Faker();

}
