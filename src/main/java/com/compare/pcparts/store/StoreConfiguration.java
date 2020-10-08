package com.compare.pcparts.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SuppressWarnings("rawtypes")
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter
@Setter
public class StoreConfiguration
{
	int id;
	String name;
	String url;
	String imageref;
	String operatingDaysOpenning;
	String operatingDaysClosing;
	String operatingHoursOpenning;
	String operatingHoursOpenningPeriod;
	String operatingHoursClosing;
	String operatingHoursClosingPeriod;
	String operatingHoursWeekendOpenning;
	String operatingHoursWeekendOpenningPeriod;
	String operatingHoursWeekendClosing;
	String operatingHoursWeekendClosingPeriod;
	Boolean pricesIncludingVAT;
	Float vatPercent;
	String contactAddress;
	String contactEmail;
	List contactNumber;
	String Xpath;
	String productNameXpath;
	String productPriceXpath;
	String productUrlXpath;
	String productAltPriceXpath;
	List cpuUrl;
	List computercaseUrl;
	List coolingfanUrl;
	List memoryUrl;
	List motherboardUrl;
	List psuUrl;
	List storageUrl;
	List graphiccardUrl;
}
