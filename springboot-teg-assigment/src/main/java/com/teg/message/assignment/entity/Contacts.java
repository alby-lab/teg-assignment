package com.teg.message.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "contact")
public class Contacts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(name = "name")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name")
	private String Name;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Contacts [Id=" + Id + ", Name=" + Name + "]";
	}

	
}
