package com.compare.pcparts.pcandparts;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PcPartsLogImp implements PcPartsLog
{
	private static final String baseUrl1 = "https://pcandparts.com/computer-cases/";
	private static final String baseUrl2 = "https://pcandparts.com/cpu/";
	private static final String baseUrl = "https://pcandparts.com/video-card/";

	private final String computercase = "https://pcandparts.com/computer-cases/";
	private final String coolingfan = "https://pcandparts.com/cooling/";
	private final String cpu = "https://pcandparts.com/cpu/";
	private final String memory = "https://pcandparts.com/memory/";
	private final String motherboard = "https://pcandparts.com/motherboard/";
	private final String psu = "https://pcandparts.com/power-supplies/";
	private final String storage1 = "https://pcandparts.com/storage-hdd-hard-drives-nvme-ssd-m2/";
	private final String storage2 = "https://pcandparts.com/storage-hdd-hard-drives-nvme-ssd-m2/page/2/";
	private final String graphiccard = "https://pcandparts.com/video-card/";

	WebClient client = new WebClient();

	public PcPartsLogImp(PcPartsMapper pcPartsMapper)
	{
		this.pcPartsMapper = pcPartsMapper;
	}

	@Override
	public void ScrappingPcParts() throws IOException
	{

		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);

		HtmlPage page = client.getPage(cpu);

		List<HtmlDivision> items = page.getByXPath("//div[@class='product-small box ']");

		System.out.println("Items Size: " + items.size());
		HtmlElement productName;
		HtmlElement productPrice;
		HtmlElement productPrice2;
		HtmlAnchor element3;
		String itemName;
		String itemPrice = null;
		String itemUrl;
		for(HtmlElement element : items)
		{
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
				System.out.println("Trying 2nd option");
				try
				{

					productPrice2 = element.getFirstByXPath(".//span[@class='price']");
					itemPrice = productPrice2.getVisibleText();
				}
				catch(Exception e)
				{
					System.out.println("Something went wrong. check the Xpath.\nException at: " + e);
				}
			}
			itemUrl = element3.getHrefAttribute();
			String description = ("Product Name: " + itemName + "\nProduct Price: " + itemPrice + "\nURL: " + itemUrl);
			System.out.println(description);
			AddToItem(itemName, itemPrice, itemUrl);
		}
	}

	@Override
	public List<PcPartsItem> getAllCpuPcAndParts() throws IOException
	{
		return pcPartsMapper.getAllCpu();
	}

	@Autowired
	public PcPartsItem pcPartsItem;

	@Autowired
	public PcPartsMapper pcPartsMapper;

	private void AddToItem(String itemName, String itemPrice, String itemUrl)
	{
		pcPartsItem.setItem_name(itemName);
		pcPartsItem.setPrice(itemPrice);
		pcPartsItem.setCurrency("$");
		pcPartsItem.setUrl(itemUrl);
		pcPartsItem.setStore_id(1);
		pcPartsMapper.insertCpu(pcPartsItem);

	}

}
