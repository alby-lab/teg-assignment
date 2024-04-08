package com.teg.message.assignment.services;

import java.util.List;

import com.teg.message.assignment.entity.Contacts;

public interface ContactServices {

	List<Contacts> fetchAllContacts(String regExp,Integer page);

	Contacts saveContacts(Contacts request);

}
