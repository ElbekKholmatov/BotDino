package com.company.domain;

import lombok.Data;

@Data

public class PhoneticsItem{
	private String text;
	private String audio;
	private String sourceUrl;
	private License license;
}