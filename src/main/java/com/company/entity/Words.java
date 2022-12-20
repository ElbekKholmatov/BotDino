package com.company.entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@ToString
public class Words {
    private String id;
    private String userChatId;
    private String word;
    private List<String> translation;
    private List<String> definition;
    private List<String> example;
    private boolean isDeleted;

    public Words(String word, List<String> translation, List<String> definition, List<String> example, boolean isDeleted) {
        this.word = word;
        this.translation = translation;
        this.definition = definition;
        this.example = example;
        this.isDeleted = isDeleted;
    }
}

