package com.shaman.transactionpaymentschedule.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationMessage {
    @JsonProperty("Code")
    private Integer code;

    @JsonProperty("Message")
    private String message;

    public static ApplicationMessage parse(String message) {
        String[] splitted = message.split("#");
        return new ApplicationMessage(Integer.valueOf(splitted[0]), splitted[1]);
    }
}
