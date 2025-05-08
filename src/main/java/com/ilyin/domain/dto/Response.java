package com.ilyin.domain.dto;


public class Response {

    public record TopSubscriptionResponseDTO(String serviceName, Integer userCount) {}
}
