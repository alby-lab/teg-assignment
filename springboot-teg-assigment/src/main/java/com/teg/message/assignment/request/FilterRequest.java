package com.teg.message.assignment.request;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Component
public class FilterRequest {
	
	@NotNull
	@NotEmpty
	private String nameFilter;
	private Integer page;
	
	public String getNameFilter() {
		return nameFilter;
	}
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
}
