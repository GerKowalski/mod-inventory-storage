package org.folio.rest;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.folio.rest.persist.PostgresClient;
import org.folio.rest.support.HttpClient;
import org.folio.rest.support.Response;
import org.folio.rest.support.ResponseHandler;
import org.folio.rest.tools.utils.NetworkUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(Suite.class)

@Suite.SuiteClasses({
  InstanceStorageTest.class,
  ItemStorageTest.class
})
public class StorageTestSuite {

  public static final String TENANT_ID = "test_tenant";

  private static Vertx vertx;
  private static int port;

  public static URL storageUrl(String path) throws MalformedURLException {
    return new URL("http", "localhost", port, path);
  }

  public static Vertx getVertx() {
    return vertx;
  }

  @BeforeClass
  public static void before()
    throws InterruptedException, ExecutionException,
    TimeoutException, MalformedURLException {

    String postgresConfigPath = System.getProperty(
      "org.folio.inventory.storage.test.config",
      "/postgres-conf-local.json");

    PostgresClient.setConfigFilePath(postgresConfigPath);

    vertx = Vertx.vertx();

    port = NetworkUtils.nextFreePort();

    DeploymentOptions options = new DeploymentOptions();

    options.setConfig(new JsonObject().put("http.port", port));
    options.setWorker(true);

    startVerticle(options);

    prepareTenant(TENANT_ID);
  }

  @AfterClass
  public static void after()
    throws InterruptedException, ExecutionException,
    TimeoutException, MalformedURLException {

    removeTenant(TENANT_ID);

    CompletableFuture undeploymentComplete = new CompletableFuture<String>();

    vertx.close(res -> {
      if(res.succeeded()) {
        undeploymentComplete.complete(null);
      }
      else {
        undeploymentComplete.completeExceptionally(res.cause());
      }
    });

    undeploymentComplete.get(20, TimeUnit.SECONDS);
  }

  private static void startVerticle(DeploymentOptions options)
    throws InterruptedException, ExecutionException, TimeoutException {

    CompletableFuture deploymentComplete = new CompletableFuture<String>();

    vertx.deployVerticle(RestVerticle.class.getName(), options, res -> {
      if(res.succeeded()) {
        deploymentComplete.complete(res.result());
      }
      else {
        deploymentComplete.completeExceptionally(res.cause());
      }
    });

    deploymentComplete.get(20, TimeUnit.SECONDS);
  }

  private static void prepareTenant(String tenantId)
    throws MalformedURLException, InterruptedException,
    ExecutionException, TimeoutException {

    CompletableFuture<Response> tenantPrepared = new CompletableFuture();

    HttpClient client = new HttpClient(vertx);

    client.post(storageUrl("/tenant"), null, tenantId,
      ResponseHandler.empty(tenantPrepared));

    Response response = tenantPrepared.get(5, TimeUnit.SECONDS);

    if(response.getStatusCode() != 200) {
      throw new UnknownError("Tenant preparation failed");
    }
  }

  private static void removeTenant(String tenantId)
    throws MalformedURLException, InterruptedException,
    ExecutionException, TimeoutException {

    CompletableFuture<Response> tenantDeleted = new CompletableFuture();

    HttpClient client = new HttpClient(vertx);

    client.delete(storageUrl("/tenant"), tenantId,
      ResponseHandler.empty(tenantDeleted));

    Response response = tenantDeleted.get(5, TimeUnit.SECONDS);

    if(response.getStatusCode() != 200) {
      throw new UnknownError("Tenant cleanup failed");
    }
  }
}
