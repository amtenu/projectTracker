package com.amtenu.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

    private Long senderId;
    private String messageContent;
    private Long projectId;
}
