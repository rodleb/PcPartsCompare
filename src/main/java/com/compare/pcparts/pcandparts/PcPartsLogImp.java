package com.compare.pcparts.pcandparts;

import com.compare.pcparts.mapper.PcPartsMapper;
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

	private final String cpu = "https://pcandparts.com/cpu/";

	@Autowired
	PcPartsMapper pcPartsMapper;

	@Autowired
	public PcPartsItem pcPartsItem;

	WebClient client = new WebClient();

	@Override
	public void ScrappingPcParts() throws IOException
	{

		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);

		HtmlPage page = client.getPage(cpu);

		List<HtmlDivision> items = page.getByXPath("//div[@class='product-small box ']");

		log.info("Items Size: " + items.size());
		HtmlElement productName;
		HtmlElement productPrice;
		HtmlElement productPrice2;
		HtmlAnchor element3;
		String itemName;
		String itemPrice = null;
		String itemUrl;

		for(HtmlElement element : items)
		{
			Boolean isAvailable = true;
			productName = element.getFirstByXPath(".//p[@class='name product-title woocommerce-loop-product__title']/a[@href]");
			productPrice = element.getFirstByXPath(".//span[@class='woocommerce-Price-amount amount']");
			element3 = element.getFirstByXPath(".//p[@class='name product-title woocommerce-loop-product__title']/a");
			itemName = productName.getVisibleText();
			try
			{
				itemPrice = productPrice.getVisibleText();
			}
			catch(NullPointerException ne)
			{
				log.warn("Trying 2nd option");
				try
				{
					productPrice2 = element.getFirstByXPath(".//span[@class='price']");
					itemPrice = productPrice2.getVisibleText();

				}
				catch(Exception e)
				{
					log.error("Something went wrong. Check the Xpath.\nException at: " + e);
				}
			}
			if(itemPrice == "Out Of Stock")
			{
				isAvailable = false;
				log.error("is available in if" + isAvailable);
			}
			itemUrl = element3.getHrefAttribute();
			String description = ("Product Name: " + itemName + "\nProduct Price: " + itemPrice + "\nURL: " + itemUrl + "\n" + "Is Available: " +
					isAvailable);
			log.info(description);
			AddToItem(itemName, itemPrice, itemUrl, isAvailable);
		}
	}

	@Override
	public List<PcPartsItem> getAllCpuPcAndParts() throws IOException
	{
		return pcPartsMapper.getAllCpu();
	}

	private void AddToItem(String itemName, String itemPrice, String itemUrl, Boolean isAvailable)
	{
		pcPartsItem.setItem_name(itemName);
		pcPartsItem.setPrice(itemPrice);
		pcPartsItem.setCurrency("$");
		pcPartsItem.set_available(isAvailable);
		pcPartsItem.setUrl(itemUrl);
		pcPartsItem.setStore_id(1);
		pcPartsMapper.insertCpu(pcPartsItem);

	}

}
