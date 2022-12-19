package com.company.domain;

import java.util.List;
import lombok.Data;

@Data
public class MeaningsItem{
	private List<String> synonyms;
	private String partOfSpeech;
	private List<Object> antonyms;
	private List<DefinitionsItem> definitions;
}