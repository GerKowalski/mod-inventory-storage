/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.folio.rest.api;

import static org.folio.rest.api.TestBase.client;
import static org.folio.rest.support.http.InterfaceUrls.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.folio.rest.api.entities.AlternativeTitleType;
import org.folio.rest.api.entities.CallNumberType;
import org.folio.rest.api.entities.ClassificationType;
import org.folio.rest.api.entities.ContributorNameType;
import org.folio.rest.api.entities.ContributorType;
import org.folio.rest.api.entities.ElectronicAccessRelationship;
import org.folio.rest.api.entities.HoldingsNoteType;
import org.folio.rest.api.entities.HoldingsType;
import org.folio.rest.api.entities.IdentifierType;
import org.folio.rest.api.entities.IllPolicy;
import org.folio.rest.api.entities.InstanceFormat;
import org.folio.rest.api.entities.InstanceStatus;
import org.folio.rest.api.entities.InstanceType;
import org.folio.rest.api.entities.ItemNoteType;
import org.folio.rest.api.entities.JsonEntity;
import org.folio.rest.api.entities.ModeOfIssuance;
import org.folio.rest.api.entities.StatisticalCode;
import org.folio.rest.api.entities.StatisticalCodeType;
import org.folio.rest.support.Response;
import org.folio.rest.support.ResponseHandler;
import org.junit.Test;

/**
 *
 * @author ne
 */
public class ReferenceTablesTest extends TestBase {

  @Test
  public void alternativeTitleTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = alternativeTitleTypesUrl("");
    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("alternative title types", searchResponse, 5, 40);
  }

  @Test
  public void alternativeTitleTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = alternativeTitleTypesUrl("");

    AlternativeTitleType entity =
            new AlternativeTitleType("Test alternative title type", "test source");

    Response postResponse = createReferenceRecord(apiUrl, entity);

    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = alternativeTitleTypesUrl("/" + entityUUID);

    String updateProperty = AlternativeTitleType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);

  }

  @Test
  public void callNumberTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = callNumberTypesUrl("");
    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("call number types", searchResponse, 5, 40);
  }

  @Test
  public void callNumberTypesBasicCrud()

          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {

    URL apiUrl = callNumberTypesUrl("");
    CallNumberType entity = new CallNumberType("Test call number type", "test source");
    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = callNumberTypesUrl("/" + entityUUID);

    String updateProperty = CallNumberType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void classificationTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = classificationTypesUrl("");
    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("classification types", searchResponse, 2, 20);
  }

  @Test
  public void classificationTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {

    URL apiUrl = classificationTypesUrl("");
    ClassificationType entity = new ClassificationType("Test classfication type");
    Response postResponse = createReferenceRecord(apiUrl, entity);

    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = classificationTypesUrl("/" + entityUUID);
    String updateProperty = ClassificationType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void contributorNameTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = contributorNameTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("contributor name types", searchResponse, 3, 10);
  }

  @Test
  public void contributorNameTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = contributorNameTypesUrl("");
    ContributorNameType entity = new ContributorNameType("Test contributor name type", "100");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = contributorNameTypesUrl("/" + entityUUID);
    String updateProperty = ContributorNameType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void contributorTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = contributorTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("contributor types", searchResponse, 20, 500);
  }

  @Test
  public void contributorTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = contributorTypesUrl("");
    ContributorType entity = new ContributorType("Test contributor type", "Test Code", "Test Source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = contributorTypesUrl("/" + entityUUID);
    String updateProperty = ContributorType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void electronicAccessRelationshipsLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = electronicAccessRelationshipsUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("electronic access relationship types", searchResponse, 2, 20);
  }

  @Test
  public void electronicAccessRelationshipsBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = electronicAccessRelationshipsUrl("");
    ElectronicAccessRelationship entity = new ElectronicAccessRelationship("Test electronic access relationship type");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = electronicAccessRelationshipsUrl("/" + entityUUID);
    String updateProperty = ElectronicAccessRelationship.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void holdingsNoteTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = holdingsNoteTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("holdings note types", searchResponse, 5, 20);
  }

  @Test
  public void holdingsNoteTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = holdingsNoteTypesUrl("");
    HoldingsNoteType entity = new HoldingsNoteType("Test holdings note type", "test source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = holdingsNoteTypesUrl("/" + entityUUID);
    String updateProperty = HoldingsNoteType.NAME_KEY;

  }

  @Test
  public void holdingsTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = holdingsTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("holdings types", searchResponse, 3, 20);
  }

  @Test
  public void holdingsTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = holdingsTypesUrl("");
    HoldingsType entity = new HoldingsType("Test holdings note type", "test source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = holdingsTypesUrl("/" + entityUUID);
    String updateProperty = HoldingsType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);

  }

  @Test
  public void identifierTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = identifierTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("identifier types", searchResponse, 8, 30);
  }

  @Test
  public void identifierTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = identifierTypesUrl("");
    IdentifierType entity = new IdentifierType("Test identifier type") ;

    Response postResponse = createReferenceRecord(apiUrl, entity);

    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = identifierTypesUrl("/" + entityUUID);
    String updateProperty = IdentifierType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void illPoliciesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = illPoliciesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("ILL policies", searchResponse, 5, 20);
  }

  @Test
  public void illPoliciesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = illPoliciesUrl("");
    IllPolicy entity = new IllPolicy("Test ILL policy", "Test source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = illPoliciesUrl("/" + entityUUID);
    String updateProperty = IllPolicy.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void instanceFormatsLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = instanceFormatsUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("instance formats", searchResponse, 20, 200);
  }

  @Test
  public void instanceFormatsBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = instanceFormatsUrl("");
    InstanceFormat entity = new InstanceFormat("Test instance format", "Test Code", "Test Source");

    Response postResponse = createReferenceRecord(apiUrl, entity);

    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = instanceFormatsUrl("/" + entityUUID);
    String updateProperty = InstanceFormat.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void instanceStatusesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = instanceStatusesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("instance statuses", searchResponse, 5, 20);
  }

  @Test
  public void instanceStatusesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = instanceStatusesUrl("");
    InstanceStatus entity = new InstanceStatus("Test instance status", "Test Code", "Test Source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = instanceStatusesUrl("/" + entityUUID);
    String updateProperty = InstanceStatus.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void instanceTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = instanceTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("instance types (resource types)", searchResponse, 10, 100);
  }

  @Test
  public void instanceTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = instanceTypesUrl("");
    InstanceType entity = new InstanceType("Test instance type", "Test Code", "Test Source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = instanceTypesUrl("/" + entityUUID);
    String updateProperty = InstanceType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void itemNoteTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = itemNoteTypesUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("item note types", searchResponse, 5, 20);
  }

  @Test
  public void itemNoteTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = itemNoteTypesUrl("");
    ItemNoteType entity = new ItemNoteType("Test item note type", "Test source");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = itemNoteTypesUrl("/" + entityUUID);
    String updateProperty = ItemNoteType.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void modesOfIssuanceLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = modesOfIssuanceUrl("");

    Response searchResponse = getReferenceRecords(apiUrl);
    validateNumberOfReferenceRecords("modes of issuance", searchResponse, 4, 10);
  }

  @Test
  public void modesOfIssuanceBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL apiUrl = modesOfIssuanceUrl("");
    ModeOfIssuance entity = new ModeOfIssuance("Test mode of issuance");

    Response postResponse = createReferenceRecord(apiUrl, entity);
    assertThat(postResponse.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUID = postResponse.getJson().getString("id");
    URL entityUrl = modesOfIssuanceUrl("/" + entityUUID);
    String updateProperty = ModeOfIssuance.NAME_KEY;

    testGetPutDelete(entityUrl, entity, updateProperty);
  }

  @Test
  public void statisticalCodeTypesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL statisticalCodeTypesUrl = statisticalCodeTypesUrl("");

    Response searchResponseCodeTypes = getReferenceRecords(statisticalCodeTypesUrl);
    validateNumberOfReferenceRecords("statistical code types", searchResponseCodeTypes, 2, 50);
  }

  @Test
  public void statisticalCodesLoaded()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {
    URL statisticalCodesUrl = statisticalCodesUrl("");

    Response searchResponseCodes = getReferenceRecords(statisticalCodesUrl);
    validateNumberOfReferenceRecords("statistical codes", searchResponseCodes, 10, 500);
  }

  @Test
  public void statisticalCodesAndCodeTypesBasicCrud()
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {

    URL statisticalCodeTypesUrl = statisticalCodeTypesUrl("");
    URL statisticalCodesUrl = statisticalCodesUrl("");

    String statisticalCodeTypeId = "8c5b634a-0a4a-47ec-b9b2-d66980656ffd";
    StatisticalCodeType statisticalCodeType = new StatisticalCodeType(statisticalCodeTypeId, "Test statistical code type", "Test source");
    StatisticalCode statisticalCode = new StatisticalCode("Test statistical name", "Test statistical code", statisticalCodeTypeId, "Test source");

    Response postResponseCodeType = createReferenceRecord(statisticalCodeTypesUrl, statisticalCodeType);
    assertThat(postResponseCodeType.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    Response postResponseCode = createReferenceRecord(statisticalCodesUrl, statisticalCode);
    assertThat(postResponseCode.getStatusCode(), is(HttpURLConnection.HTTP_CREATED));

    String entityUUIDCode = postResponseCode.getJson().getString("id");
    URL entityUrlCode = statisticalCodesUrl("/" + entityUUIDCode);
    String updatePropertyCode = StatisticalCode.NAME_KEY;

    testGetPutDelete(entityUrlCode, statisticalCode, updatePropertyCode);

    String entityUUIDCodeType = postResponseCodeType.getJson().getString("id");
    URL entityUrlCodeType = statisticalCodeTypesUrl("/" + entityUUIDCodeType);
    String updatePropertyCodeType = StatisticalCodeType.NAME_KEY;

    testGetPutDelete(entityUrlCodeType, statisticalCodeType, updatePropertyCodeType);

  }

  private Response getReferenceRecords(URL baseUrl)
          throws InterruptedException,
          MalformedURLException,
          TimeoutException,
          ExecutionException,
          UnsupportedEncodingException {

    CompletableFuture<Response> searchCompleted = new CompletableFuture<>();
    String url = baseUrl.toString() + "?limit=400&query="
            + URLEncoder.encode("cql.allRecords=1", StandardCharsets.UTF_8.name());
    client.get(url, StorageTestSuite.TENANT_ID, ResponseHandler.json(searchCompleted));
    Response searchResponse = searchCompleted.get(5, TimeUnit.SECONDS);
    return searchResponse;
  }

  private void validateNumberOfReferenceRecords(String dataDescription, Response searchResponse, int min, int max) {
    Integer totalRecords = searchResponse.getJson().getInteger("totalRecords");
    assertTrue(String.format("Could not retrieve record count for %s", dataDescription), totalRecords != null);
    assertTrue(String.format("Expected <=%s \"%s\", found %s", max, dataDescription, totalRecords), max >= totalRecords);
    assertTrue(String.format("Expected >=%s \"%s\", found %s", min, dataDescription, totalRecords), min <= totalRecords);
  }

  private Response createReferenceRecord(URL referenceUrl, JsonEntity referenceObject)
  throws ExecutionException, InterruptedException, TimeoutException {

    CompletableFuture<Response> createCompleted = new CompletableFuture<>();
    client.post(
            referenceUrl,
            referenceObject.getJson(),
            StorageTestSuite.TENANT_ID,
            ResponseHandler.json(createCompleted)
    );
    Response postResponse = createCompleted.get(5, TimeUnit.SECONDS);
    return postResponse;
  }

  private Response getById(URL getByIdUrl)
    throws MalformedURLException, InterruptedException,
    ExecutionException, TimeoutException {

    CompletableFuture<Response> getCompleted = new CompletableFuture<>();

    client.get(getByIdUrl, StorageTestSuite.TENANT_ID,
      ResponseHandler.any(getCompleted));

    Response getByIdResponse = getCompleted.get(5, TimeUnit.SECONDS);

    return getByIdResponse;
  }

  private Response deleteReferenceRecordById (URL entityUrl)
  throws ExecutionException, InterruptedException, TimeoutException {
    CompletableFuture<Response> deleteCompleted = new CompletableFuture<>();
    client.delete(
            entityUrl,
            StorageTestSuite.TENANT_ID,
            ResponseHandler.any(deleteCompleted)
    );
    Response deleteResponse = deleteCompleted.get(5, TimeUnit.SECONDS);
    return deleteResponse;
  }

  private Response updateRecord (URL entityUrl, JsonEntity referenceObject)
  throws ExecutionException, InterruptedException, TimeoutException {
    CompletableFuture<Response> updateCompleted = new CompletableFuture<>();
    client.put(
            entityUrl,
            referenceObject.getJson(),
            StorageTestSuite.TENANT_ID,
            ResponseHandler.any(updateCompleted)
    );
    Response putResponse = updateCompleted.get(5, TimeUnit.SECONDS);
    return putResponse;
  }

  private void testGetPutDelete (URL entityUrl, JsonEntity entity, String updateProperty)
          throws ExecutionException,
          InterruptedException,
          MalformedURLException,
          TimeoutException {

    entity.put(updateProperty, entity.getString(updateProperty)+" UPDATED");
    Response putResponse = updateRecord(entityUrl, entity);

    assertThat(putResponse.getStatusCode(), is(HttpURLConnection.HTTP_NO_CONTENT));

    Response getResponse = getById(entityUrl);

    assertThat(getResponse.getStatusCode(), is(HttpURLConnection.HTTP_OK));
    assertThat(getResponse.getJson().getString(updateProperty), is(entity.getString(updateProperty)));

    Response deleteResponse = deleteReferenceRecordById (entityUrl);

    assertThat(deleteResponse.getStatusCode(), is(HttpURLConnection.HTTP_NO_CONTENT));

    Response getResponse2 = getById(entityUrl);

    assertThat(getResponse2.getStatusCode(), is(HttpURLConnection.HTTP_NOT_FOUND));
  }
}