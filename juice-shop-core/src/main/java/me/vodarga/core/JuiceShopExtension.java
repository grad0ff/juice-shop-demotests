package me.vodarga.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@Slf4j
@RequiredArgsConstructor
public class JuiceShopExtension implements BeforeAllCallback {

  private static GenericContainer<?> juiceShop;
  private final String imageName;
  private final int port;
  @Getter
  private String url;

  public void beforeSuite(ExtensionContext context) {
    log.info("Starting Juice Shop Container");
    juiceShop = new GenericContainer<>(imageName)
        .withExposedPorts(port)
        .waitingFor(Wait.forHttp("/").forStatusCode(200));
    juiceShop.start();
    url = "http://" + juiceShop.getHost() + ":" + juiceShop.getFirstMappedPort();
  }

  public void afterSuite(ExtensionContext context) {
    log.info("Juice Shop has been shut down");
    juiceShop.close();
  }

  @Override
  public void beforeAll(ExtensionContext context) {
    Store store = context.getRoot()
        .getStore(Namespace.GLOBAL);
    store.computeIfAbsent(getClass(), key -> {
          beforeSuite(context);
          return (AutoCloseable) () -> afterSuite(context);
        });
    store.put("APP", juiceShop);
    store.put("APP_URL", url);
  }

}
