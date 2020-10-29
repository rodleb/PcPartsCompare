package com.compare.pcparts.webscrape;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.store.items.StoreItem;
import com.compare.pcparts.store.items.StoreUrlItem;
import com.compare.pcparts.store.items.StoreXPathItem;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Log4j2
@Component
public class WebscrapeLogImp implements WebscrapeLog
{
	@Autowired
	PcPartsMapper pcPartsMapper;

	@Autowired
	PcPartsItem pcPartsItem;

	WebClient client = new WebClient();

	public void getStore() throws IOException
	{
		List<Integer> storeIdList = new ArrayList<Integer>();
		List<String> storeNameList = new ArrayList<String>();

		List<StoreItem> storeItemList = pcPartsMapper.getStoreInfo();

		for(StoreItem item : storeItemList)
		{
			storeIdList.add(item.getId());
			storeNameList.add(item.getStore_name());

			log.info("Id: " + storeIdList + " Name: " + storeNameList);
			List<StoreUrlItem> storeUrlItemsList = pcPartsMapper.getStoreUrl(item.getId());
			List<StoreXPathItem> storeXPathItemsList = pcPartsMapper.getStoreXPath(item.getId());
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
		try
		{
			HtmlPage page = client.getPage(url);
			List<HtmlDivision> items = page.getByXPath(xPath);
			log.info("Items Size: " + items.size());

			HtmlElement productName;
			HtmlElement productPrice;
			HtmlAnchor productUrl;

			HtmlElement altProduct;
			HtmlElement altProduct2;
			HtmlAnchor altProduct3;

			String itemName;
			String itemPrice;
			String itemUrl;

			float itemPurePrice;

			for(HtmlElement element : items)
			{
				itemName = null;
				itemPrice = null;
				itemUrl = null;
				String currency = "LBP";

				Boolean isAvailable;

				productName = element.getFirstByXPath(nameXpath);
				productPrice = element.getFirstByXPath(priceXpath);
				productUrl = element.getFirstByXPath(urlXpath);

				NumberFormat format = NumberFormat.getCurrencyInstance();

				try
				{
					itemName = productName.getVisibleText();

				}
				catch(NullPointerException ne)
				{
					try
					{
						altProduct2 = element.getFirstByXPath(altXpath2);
						itemName = altProduct2.getVisibleText();
					}
					catch(Exception e)
					{
						log.error("Something went wrong at the AltPath 2. Check the Xpath.\nException at: " + e);
					}
				}


				try
				{

					currency = format.getCurrency().getSymbol();
					itemPrice = productPrice.getVisibleText();
					log.info("Price 1st try xpath1 is " + itemPrice);
				}
				catch(NullPointerException ne)
				{
					log.warn("Trying 2nd option");
					try
					{
						altProduct = element.getFirstByXPath(altXpath1);
						itemPrice = altProduct.getVisibleText();
						log.info("Price 2nd try alt xpath is " + itemPrice);

					}
					catch(NullPointerException nee)
					{
						log.warn("Trying 3rd option");
						try
						{
							altProduct = element.getFirstByXPath(altXpath2);
							itemPrice = altProduct.getVisibleText();
							log.info("Price 3rd try alt xpath 2 is " + itemPrice);

						}
						catch(Exception e)
						{
							log.error("Something went wrong at AltPath 1. Check the Xpath.\nExing to contact her rn" +
									"ception at: " + e);

						}
					}
				}

				if(itemPrice.equals("Out Of Stock"))
				{
					isAvailable = false;

				}
				else
				{
					isAvailable = true;
				}

				String itemPurePriceStr = itemPrice.replaceAll("[^0-9.]", "");
				itemPurePriceStr = itemPurePriceStr.replace("$", "");
				log.info("Price at start is: " + itemPurePriceStr);

				if(itemPurePriceStr.isEmpty())
				{
					itemPurePrice = 0;
				}
				else
				{
					try
					{
						log.warn("Real price String before update: "+itemPurePriceStr);
						itemPurePrice = Float.parseFloat(itemPurePriceStr);
						log.warn("Real price Float after update: "+itemPurePriceStr);

					}
					catch(NumberFormatException ne)
					{
						log.warn("Warning wrong input, with exception: " + ne);
						itemPurePrice = 0;
					}

				}

				try
				{
					itemUrl = productUrl.getHrefAttribute();

				}
				catch(NullPointerException ne)
				{
					try
					{
						altProduct3 = element.getFirstByXPath(altXpath3);
						itemUrl = altProduct3.getHrefAttribute();
					}
					catch(Exception e)
					{
						log.error("Something went wrong at the AltPath 3. Check the Xpath.\nException at: " + e);
					}
				}

				String description = ("Product Name: " + itemName + "\nProduct Price: " + itemPrice + "\nURL: " + itemUrl + "\nCurrency: " +
						currency + "\nIs Available: " + isAvailable + "\nReal price: " + itemPurePrice + "\n");
				log.info(description);
				AddToItem(storeId, itemName, partType, currency, itemPrice, itemPurePrice, itemUrl, isAvailable);
			}
		}
		catch(MalformedURLException me)
		{

			log.error("Error for url: " + url + "\nException: " + me);
		}
		pcPartsMapper.updateItemNames();
	}

	private void AddToItem(int storeId, String itemName, String partType, String currency, String itemPrice, Float itemPurePrice, String itemUrl,
			Boolean isAvailable)
	{
		pcPartsItem.setItem_name(itemName);
		pcPartsItem.setPrice(itemPrice);
		pcPartsItem.setPrice_pure(itemPurePrice);
		pcPartsItem.setItem_type(partType);
		pcPartsItem.setCurrency(currency);
		pcPartsItem.set_available(isAvailable);
		pcPartsItem.setUrl(itemUrl);
		pcPartsItem.setStore_id(storeId);
		pcPartsMapper.insertItem(pcPartsItem);

	}

}
