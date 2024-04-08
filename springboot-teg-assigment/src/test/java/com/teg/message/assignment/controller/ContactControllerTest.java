package com.teg.message.assignment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teg.message.assignment.entity.Contacts;
import com.teg.message.assignment.request.FilterRequest;
import com.teg.message.assignment.services.ContactServices;


@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ContactController contactController;
	
	@Mock
	private ContactServices contactServices;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}

	@Test
	public void createContactTest() throws Exception {
		
		Contacts contact =new Contacts();
		contact.setId(1);
		contact.setName("Sivankutty");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/hellow/createContacts").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contact)))
        .andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
	
	}
	@Test
	public void createContactValidationFail() throws JsonProcessingException, Exception {
		
		Contacts contact =new Contacts();
		contact.setId(1);
		contact.setName("Sivan kutty");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/hellow/createContacts").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contact)))
        .andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(400);
		assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo("Invalid request content.");
		
	}
	
	  @Test 
	  public void testFindAll() {
	  
	  Contacts contactTest =new Contacts(); contactTest.setId(1);
	  contactTest.setName("Sivankutty"); 
	  Contacts contactTestObj =new Contacts();
	  contactTestObj.setId(1); 
	  contactTestObj.setName("Manoj"); Contacts
	  contacttestObj2 =new Contacts(); contacttestObj2.setId(1);
	  contacttestObj2.setName("Oormila"); 
	  List<Contacts>contactList=new  ArrayList<>(); contactList.add(contactTest); 
	  contactList.add(contactTestObj);
	  contactList.add(contacttestObj2);
	  FilterRequest request=new FilterRequest();
	  request.setNameFilter("^A.$");
	  request.setPage(0);
	  when(contactServices.fetchAllContacts(request.getNameFilter(),request.getPage())).thenReturn(contactList);
	  ResponseEntity<List<Contacts>> result =  contactController.getContacts(request);
	  assertThat(result.getBody().size()).isEqualTo(3);
	  assertThat(result.getBody().get(0).getName()).isEqualTo(contactTest.getName());
	  assertThat(result.getBody().get(0).getId()).isEqualTo(contactTest.getId());
	  }
	
	@Test
    public void entityTest() throws Exception {
        Integer Id = 123;
        Contacts contact=new Contacts();
        contact.setId(123);
        contact.setName("Lokesh");
        
        assertThat(contact).isNotNull();
        assertThat(contact.getId()) .isEqualTo(123) ;
          
    }
}
