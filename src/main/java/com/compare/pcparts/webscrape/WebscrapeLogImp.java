package com.compare.pcparts.webscrape;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.store.items.StoreItem;
import com.compare.pcparts.store.items.StoreUrlItem;
import com.compare.pcparts.store.items.StoreXPathItem;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class WebscrapeLogImp implements WebscrapeLog
{
	@Autowired
	PcPartsMapper pcPartsMapper;

	@Autowired
	PcPartsItem pcPartsItem;

	WebClient client = new WebClient();

	private List<StoreItem> storeItemList;
	private List<StoreUrlItem> storeUrlItemsList;
	private List<StoreXPathItem> storeXPathItemsList;

	public void getStore() throws IOException
	{
		List<Integer> storeIdList = new ArrayList<Integer>();
		List<String> storeNameList = new ArrayList<String>();

		storeItemList = pcPartsMapper.getStoreInfo();

		for(StoreItem item : storeItemList)
		{
			storeIdList.add(item.getId());
			storeNameList.add(item.getStore_name());

			log.info("Id: " + storeIdList + " Name: " + storeNameList);
			storeUrlItemsList = pcPartsMapper.getStoreUrl(item.getId());
			storeXPathItemsList = pcPartsMapper.getStoreXPath(item.getId());
			for(StoreUrlItem urlItem : storeUrlItemsList)
			{
				for(StoreXPathItem xPathItem : storeXPathItemsList)
				{

					ScrappingPcParts(item.getId(), urlItem.getPart_name(), urlItem.getPage_url(), xPathItem.getXpath(),
							xPathItem.getProduct_name_xpath(), xPathItem.getProduct_price_xpath(), xPathItem.getProduct_url_xpath(),
							xPathItem.getAlt_xpath_1(), xPathItem.getAlt_xpath_2(), xPathItem.getAlt_xpath_3());
				}
			}
		}
	}


	private void ScrappingPcParts(int storeId, String partType, String url, String xPath, String nameXpath, String priceXpath, String urlXpath,
			String altXpath1, String altXpath2, String altXpath3) throws IOException
	{

		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);

		HtmlPage page = client.getPage(url);

		//List<HtmlDivision> items = page.getByXPath("//div[@class='product-small box ']");
		List<HtmlDivision> items = page.getByXPath(xPath);

		log.info("Items Size: " + items.size());
		HtmlElement productName;
		HtmlElement productPrice;
		HtmlAnchor productUrl;
		HtmlElement altProduct;
		HtmlElement altProduct2;
		HtmlElement altProduct3;

		String itemName;
		String itemPrice;
		String itemUrl;

		for(HtmlElement element : items)
		{
			itemName = null;
			itemPrice = null;
			itemUrl = null;
			String currency = "LBP";

			Boolean isAvailable;
			/*
			productName = element.getFirstByXPath(".//p[@class='name product-title woocommerce-loop-product__title']/a[@href]");
			productPrice = element.getFirstByXPath(".//span[@class='woocommerce-Price-amount amount']");
			productUrl = element.getFirstByXPath(".//p[@class='name product-title woocommerce-loop-product__title']/a");

			 */
			productName = element.getFirstByXPath(nameXpath);
			productPrice = element.getFirstByXPath(priceXpath);
			productUrl = element.getFirstByXPath(urlXpath);

			NumberFormat format = NumberFormat.getCurrencyInstance();

			//Todo: add nested try catch after this
			itemName = productName.getVisibleText();

			try
			{
				itemPrice = productPrice.getVisibleText();
				currency = format.getCurrency().getSymbol();
			}
			catch(NullPointerException ne)
			{
				log.warn("Trying 2nd option");
				try
				{
					//altProduct = element.getFirstByXPath(".//span[@class='price']");
					altProduct = element.getFirstByXPath(altXpath1);
					itemPrice = altProduct.getVisibleText();
					//log.info("alt xpath "+altXpath1+" alt product "+altProduct+" price: "+itemPrice );

				}
				catch(Exception e)
				{
					log.error("Something went wrong. Check the Xpath.\nException at: " + e);
				}
			}


			if(itemPrice.equals("Out Of Stock"))
			{
				isAvailable = false;
			}else {
				isAvailable = true;
			}

			//Todo: add nested try catch after this
			itemUrl = productUrl.getHrefAttribute();

			String description = ("Product Name: " + itemName + "\nProduct Price: " + itemPrice + "\nURL: " + itemUrl + "\nCurrency: " + currency +
					"\nIs Available: "+isAvailable+"\n");
			log.info(description);
			AddToItem(storeId, itemName, partType, currency, itemPrice, itemUrl, isAvailable);
		}
	}

	private void AddToItem(int storeId, String itemName, String partType,String currency , String itemPrice, String itemUrl, Boolean isAvailable)
	{
		pcPartsItem.setItem_name(itemName);
		pcPartsItem.setPrice(itemPrice);
		pcPartsItem.setItem_type(partType);
		pcPartsItem.setCurrency(currency);
		pcPartsItem.set_available(isAvailable);
		pcPartsItem.setUrl(itemUrl);
		pcPartsItem.setStore_id(storeId);
		pcPartsMapper.insertItem(pcPartsItem);

	}

}
