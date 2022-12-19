package com.company.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Users {
    private long id;
    private String chatId;
    private String name;
    private String username;
    private boolean isAutoAddWord;
}
