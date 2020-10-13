package com.compare.pcpartstest.pcparts;

import com.compare.pcpartstest.mapper.PcPartsMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class PcPartsLogImp implements PcPartsLog
{

	@Autowired
	public PcPartsItem pcPartsItem;

	@Autowired
	public PcPartsMapper pcPartsMapper;

	WebClient client = new WebClient();

	private final String Url = "https://pcandparts.com/cpu/";
	/*
		private final String xPath = "//div[@class='product-small box ']";
		private final String nameXPath = ".//p[@class='name product-title woocommerce-loop-product__title']/a[@href]";
		private final String priceXPath = ".//span[@class='price']";
		private final String urlXPath = ".//p[@class='name product-title woocommerce-loop-product__title']/a";
		private final String altXPath = ".//span[@class='price']";


	 */
	@Autowired
	XPathConfiguration xPathConfiguration;


	private String xPath;
	private String nameXPath;
	private String priceXPath;
	private String urlXPath;
	private String altXPath;
	private String attempt;

	@Override
	public String ScrappingPcAndParts() throws IOException
	{

		xPath = xPathConfiguration.getXPath();
		nameXPath = xPathConfiguration.getNameXPath();
		priceXPath = xPathConfiguration.getPriceXPath();
		urlXPath = xPathConfiguration.getUrlXPath();
		altXPath = xPathConfiguration.getAltXPath();
		attempt = xPathConfiguration.getAttempt();

		log.info("Scrapping started...\nUsing attempt: "+attempt);
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);

		HtmlPage page = client.getPage(Url);

		List<HtmlDivision> items = page.getByXPath(xPath);

		log.info("Items Size: " + items.size());

		String itemPrice;
		String itemName = null;
		String itemUrl;

		HtmlElement productName;
		HtmlElement productPrice;
		HtmlAnchor productUrl;

		HtmlElement altProduct;
		for(HtmlElement element : items)
		{
			itemPrice = null;
			productName = element.getFirstByXPath(nameXPath);
			productPrice = element.getFirstByXPath(priceXPath);
			productUrl = element.getFirstByXPath(urlXPath);

			itemName = productName.getVisibleText();
			try
			{
				itemPrice = productPrice.getVisibleText();

			}
			catch(NullPointerException ne)
			{
				log.info("Trying 2nd option");
				try
				{
					altProduct = element.getFirstByXPath(altXPath);
					//altProduct = element.getFirstByXPath(altXpath1);
					itemPrice = altProduct.getVisibleText();
					//log.info("alt xpath "+altXpath1+" alt product "+altProduct+" price: "+itemPrice );

				}
				catch(Exception e)
				{
					log.error("Something went wrong. Check the Xpath.\nException at: " + e);
				}
			}
			itemUrl = productUrl.getHrefAttribute();
			String description = ("Product Name: " + itemName + "\nProduct Price: " + itemPrice + "\nURL: " + itemUrl + "\nDone...\n");
			System.out.println(description);
			AddToItem(itemName, itemPrice, itemUrl);
		}
		log.info("Scrapped Successfully.");
		return "Scrapped Successfully.";
	}

	@Override
	public List<PcPartsItem> getAllItemsDB() throws IOException
	{
		return pcPartsMapper.getAllItems();
	}

	private void AddToItem(String itemName, String itemPrice, String itemUrl)
	{
		pcPartsItem.setItem_name(itemName);
		pcPartsItem.setPrice(itemPrice);
		pcPartsItem.setUrl(itemUrl);
		pcPartsMapper.insertItem(pcPartsItem);

	}

}
