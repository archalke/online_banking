package com.banking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.banking.PageNames")
@Data
public class PageNames {

    public static String errorPage;// = "generalError";

}
