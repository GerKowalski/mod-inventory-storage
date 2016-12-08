package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import org.folio.rest.jaxrs.model.Instance;
import org.folio.rest.jaxrs.model.Instances;
import org.folio.rest.jaxrs.resource.InstanceStorageResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class InstanceStorageAPI implements InstanceStorageResource {

  @Override
  public void getInstanceStorageInstances(@DefaultValue("en") @Pattern(regexp = "[a-zA-Z]{2}") String lang,
                                         Map<String, String> okapiHeaders,
                                         Handler<AsyncResult<Response>> asyncResultHandler,
                                         Context vertxContext) throws Exception {

    List<Instance> instances = new ArrayList<Instance>();

    Instance instance = new Instance();

    instance.setTitle("Refactoring");
    instance.setId(UUID.randomUUID().toString());

    instances.add(instance);

    Instances instanceList = new Instances();
    instanceList.setInstances(instances);
    instanceList.setTotalRecords(instances.size());
    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(
      GetInstanceStorageInstancesResponse.withJsonOK(instanceList)));
  }

  @Override
  public void postInstanceStorageInstances(@DefaultValue("en") @Pattern(regexp = "[a-zA-Z]{2}") String lang,
                                          Instance entity,
                                          Map<String, String> okapiHeaders,
                                          Handler<AsyncResult<Response>> asyncResultHandler,
                                          Context vertxContext) throws Exception {

    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostInstanceStorageInstancesResponse.withPlainInternalServerError("Not implemented")));
  }

  @Override
  public void postInstanceStorageInstancesByInstanceId(@NotNull String instanceId, @DefaultValue("en") @Pattern(regexp = "[a-zA-Z]{2}") String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutInstanceStorageInstancesByInstanceIdResponse.withPlainInternalServerError("Not implemented")));
  }

  @Override
  public void getInstanceStorageInstancesByInstanceId(@NotNull String instanceId, @DefaultValue("en") @Pattern(regexp = "[a-zA-Z]{2}") String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetInstanceStorageInstancesByInstanceIdResponse.withPlainInternalServerError("Not implemented")));
  }

  @Override
  public void deleteInstanceStorageInstancesByInstanceId(@NotNull String instanceId, @DefaultValue("en") @Pattern(regexp = "[a-zA-Z]{2}") String lang, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteInstanceStorageInstancesByInstanceIdResponse.withPlainInternalServerError("Not implemented")));
  }

  @Override
  public void putInstanceStorageInstancesByInstanceId(@NotNull String instanceId, @DefaultValue("en") @Pattern(regexp = "[a-zA-Z]{2}") String lang, Instance entity, Map<String, String> okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {
    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutInstanceStorageInstancesByInstanceIdResponse.withPlainInternalServerError("Not implemented")));
  }
}
