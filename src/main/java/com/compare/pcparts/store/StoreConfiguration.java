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
	String currency;
	Boolean pricesIncludingVAT;
	Float vatPercent;
	String contactAddress;
	String contactEmail;
	String contactNumber1;
	String contactNumber2;
	String contactNumber3;
	String Xpath;
	String productNameXpath;
	String productPriceXpath;
	String productUrlXpath;
	String productAltXpath1;
	String productAltXpath2;
	String productAltXpath3;
	List<String> cpuUrl;
	List<String> computercaseUrl;
	List<String> coolingfanUrl;
	List<String> memoryUrl;
	List<String> motherboardUrl;
	List<String> psuUrl;
	List<String> storageUrl;
	List<String> graphiccardUrl;
	List<String> laptopUrl;
	List<String> accessoriesUrl;
}
