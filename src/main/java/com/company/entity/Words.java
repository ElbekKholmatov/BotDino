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
    private String definition;
    private boolean isDeleted;
}
