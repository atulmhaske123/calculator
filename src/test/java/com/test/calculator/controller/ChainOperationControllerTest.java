package com.test.calculator.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.test.calculator.AbstractTest;
import com.test.calculator.model.ChainOperation;
import com.test.calculator.model.ChainRequest;
import com.test.calculator.model.ErrorResponse;
import com.test.calculator.model.Response;
import com.test.calculator.operation.Operation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChainOperationControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   
   @Test
   public void testSingleOperation() throws Exception {
      String uri = "/calculator/chain";
      List<ChainOperation> chainOperations = Arrays.asList(new ChainOperation(Operation.ADD, 5));
      ChainRequest chainRequest = new ChainRequest(100, chainOperations);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Response response = super.mapFromJson(content, Response.class);
      assertEquals(response.getResult(), 105.0);
   }
   
   @Test
   public void testChainOperation() throws Exception {
	  String uri = "/calculator/chain";
      List<ChainOperation> chainOperations = Arrays
    		  .asList(
    				  new ChainOperation(Operation.ADD, 5)
    				  ,new ChainOperation(Operation.MULTIPLY, 2)
    				  );
      ChainRequest chainRequest = new ChainRequest(100, chainOperations);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Response response = super.mapFromJson(content, Response.class);
      assertEquals(response.getResult(), 210.0);
   }
   
   @Test
   public void testDivideByZeroOperation() throws Exception {
	  String uri = "/calculator/chain";
      List<ChainOperation> chainOperations = Arrays
    		  .asList(
    				  new ChainOperation(Operation.ADD, 5)
    				  ,new ChainOperation(Operation.DIVIDE, 0)
    				  );
      ChainRequest chainRequest = new ChainRequest(100, chainOperations);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Response response = super.mapFromJson(content, Response.class);
      assertEquals(response.getResult(), Double.POSITIVE_INFINITY);
   }
   
   @Test
   public void testWithoutInitialOperation() throws Exception {
	  String uri = "/calculator/chain";
      List<ChainOperation> chainOperations = Arrays
    		  .asList( new ChainOperation(Operation.ADD, 8));
      ChainRequest chainRequest = new ChainRequest(null, chainOperations);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[initial value is a must, make sure request contains intial value.]");
   }
   
   @Test
   public void testWithoutChainOperation() throws Exception {
	  String uri = "/calculator/chain";
      ChainRequest chainRequest = new ChainRequest(100, null);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[chainOperations value is a must, make sure request contains chainOperations value.]");
   }
   
   @Test
   public void testWithoutOperation() throws Exception {
	  String uri = "/calculator/chain";
	  List<ChainOperation> chainOperations = Arrays
    		  .asList( new ChainOperation(null, 8));
      ChainRequest chainRequest = new ChainRequest(100, chainOperations);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[operation value is a must, make sure chainOperations array contains operation in every object.]");
   }
   
   @Test
   public void testWithoutOperand() throws Exception {
	  String uri = "/calculator/chain";
	  List<ChainOperation> chainOperations = Arrays
    		  .asList( new ChainOperation(Operation.ADD, null));
      ChainRequest chainRequest = new ChainRequest(100, chainOperations);
      String inputJson = super.mapToJson(chainRequest);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[operand value is a must, make sure chainOperations array contains operand in every object.]");
   }
}
