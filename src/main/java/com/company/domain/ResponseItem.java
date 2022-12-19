package com.company.domain;

import java.util.List;
import lombok.Data;

@Data
public class ResponseItem{
	private License license;
	private List<PhoneticsItem> phonetics;
	private String word;
	private List<MeaningsItem> meanings;
	private List<String> sourceUrls;
}