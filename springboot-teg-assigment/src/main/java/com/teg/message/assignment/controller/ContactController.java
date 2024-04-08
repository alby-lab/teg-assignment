package com.teg.message.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teg.message.assignment.entity.Contacts;
import com.teg.message.assignment.producer.Producer;
import com.teg.message.assignment.request.FilterRequest;
import com.teg.message.assignment.services.ContactServices;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/hellow")
@Component
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	ContactServices contactServices;
	@Autowired
	Producer producer;

	@PostMapping("/createContacts")
	public ResponseEntity<Contacts> createContact(@Valid @RequestBody Contacts request) {
		
		Contacts resposnse = new Contacts();
		try {
			resposnse = contactServices.saveContacts(request);
			ObjectMapper Obj = new ObjectMapper();
			if (resposnse != null) {
				String jsonStr = Obj.writeValueAsString(resposnse);
				producer.sendMessageToTopic(jsonStr);
			}
		} catch (Exception e) {
			logger.error("ContactController: createContact{}" + e.getMessage());
		}
		return new ResponseEntity<>(resposnse, HttpStatus.CREATED);
	}

	@PostMapping("/contacts")
	public ResponseEntity<List<Contacts>> getContacts(
			@Valid @NotNull @NotEmpty @RequestBody FilterRequest filterParam) {
			List<Contacts> contacts = new ArrayList<Contacts>();
		try {

			contacts = contactServices.fetchAllContacts(filterParam.getNameFilter(), filterParam.getPage());

		} catch (Exception e) {
			logger.error("ContactController: getContacts{}" + e.getMessage());
		}
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

}
