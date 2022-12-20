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
    private List<String> synonym;//shu so'zni beruvchi boshqa soz'lar
    private List<String> definition;//<-example
    private boolean isDeleted;

    public Words(String word, String userChatId,List<String> translation, List<String> definition, List<String> synonym, boolean isDeleted) {
        this.word = word;
        this.translation = translation;
        this.userChatId = userChatId;
        this.definition = definition;
        this.synonym = synonym;
        this.isDeleted = isDeleted;
    }
}

