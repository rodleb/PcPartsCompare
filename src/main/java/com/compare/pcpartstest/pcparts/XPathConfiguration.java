package com.compare.pcpartstest.pcparts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("rawtypes")
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter
@Setter
public class XPathConfiguration
{
	String xPath;
	String nameXPath;
	String priceXPath;
	String urlXPath;
	String altXPath;
	String attempt;
}
