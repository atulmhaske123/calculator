package com.calculator.controller;

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

import com.calculator.AbstractTest;
import com.calculator.model.ChainOperation;
import com.calculator.model.ChainRequest;
import com.calculator.model.ErrorResponse;
import com.calculator.model.Response;
import com.calculator.model.SimpleRequest;
import com.calculator.operation.Operation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BasicOperationControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   
   @Test
   public void testBasicOperation() throws Exception {
      String uri = "/calculator/v1/basic";
      SimpleRequest request = new SimpleRequest(Operation.ADD, 5, 5);
      String inputJson = super.mapToJson(request);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Response response = super.mapFromJson(content, Response.class);
      assertEquals(response.getData(), 10.0);
   }
   
   
   @Test
   public void testDivideByZeroOperation() throws Exception {
	  String uri = "/calculator/v1/basic";
	  SimpleRequest request = new SimpleRequest(Operation.DIVIDE, 5, 0);
      String inputJson = super.mapToJson(request);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Response response = super.mapFromJson(content, Response.class);
      assertEquals(response.getData(), "Infinity");
   }
   
   @Test
   public void testWithLargeNumbers() throws Exception {
	  String uri = "/calculator/v1/basic";
      String inputJson = "{\r\n"
      		+ "    \"operation\": \"MULTIPLY\",\r\n"
      		+ "    \"operand1\": 899999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999,\r\n"
      		+ "    \"operand2\": 899999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999\r\n"
      		+ "}";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Response response = super.mapFromJson(content, Response.class);
      assertEquals(response.getData(), "Infinity");
   }
   
   @Test
   public void testWithoutOperation() throws Exception {
	  String uri = "/calculator/v1/basic";
	  SimpleRequest request = new SimpleRequest(null, 5, 0);
      String inputJson = super.mapToJson(request);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[operation value is a must.]");
   }
   
   @Test
   public void testWithoutFirstOperand() throws Exception {
	  String uri = "/calculator/v1/basic";
	  SimpleRequest request = new SimpleRequest(Operation.ADD, null, 0);
      String inputJson = super.mapToJson(request);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[operand1 value is a must.]");
   }
   
   @Test
   public void testWithoutSecondOperand() throws Exception {
	  String uri = "/calculator/v1/basic";
	  SimpleRequest request = new SimpleRequest(Operation.ADD, 1, null);
      String inputJson = super.mapToJson(request);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    		.contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(400, status);
      String content = mvcResult.getResponse().getContentAsString();
      ErrorResponse response = super.mapFromJson(content, ErrorResponse.class);
      assertEquals(response.getStatusCode(), 400);
      assertEquals(response.getMessage(), "[operand2 value is a must.]");
   }
}
