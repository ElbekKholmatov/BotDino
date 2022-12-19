package com.company.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefinitionsItem{
	private List<Object> synonyms;
	private List<Object> antonyms;
	private String definition;
	private String example;
}