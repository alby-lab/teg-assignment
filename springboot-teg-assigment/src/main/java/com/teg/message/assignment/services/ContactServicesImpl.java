package com.teg.message.assignment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teg.message.assignment.entity.Contacts;
import com.teg.message.assignment.repository.ContactReposotory;

@Service
public class ContactServicesImpl implements ContactServices {

	private static final Logger logger = LoggerFactory.getLogger(ContactServicesImpl.class);
	@Autowired
	private ContactReposotory contactReposotory;

	@Override
	public List<Contacts> fetchAllContacts(String filterParam, Integer page) {
		List<Contacts> contactList = new ArrayList<Contacts>();
		Integer size = 500;
		try {
			Pageable pageable = PageRequest.of(page, size);
			Page<Contacts> pageContacts = contactReposotory.findAll(pageable);
			contactList = pageContacts.getContent();
			if (filterParam.equals("^A.$")) {
				Pattern pattern = Pattern.compile("^(?!A).*$");
				return contactList.stream().filter(contact -> pattern.matcher(contact.getName()).matches())
						.collect(Collectors.toList());
			} else if (filterParam.equals("^.[aei].*$")) {

				Pattern pattern = Pattern.compile("^[^aeiAEI]+$");
				return contactList.stream().filter(contact -> pattern.matcher(contact.getName()).matches())
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("ContactServicesImpl : fetchAllContacts{} " + e.getMessage());
		}
		return null;
	}

	@Override
	public Contacts saveContacts(Contacts request) {
		Contacts contact = new Contacts();
		try {
			contact = contactReposotory.save(request);
		} catch (Exception e) {
			logger.error("ContactServicesImpl : saveContacts{} " + e.getMessage());
		}
		return contact;
	}

}
