package com.compare.pcparts.pcandparts;

import com.compare.pcparts.mapper.PcAndPartsMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PcAndPartsLogImp implements PcAndPartsLog
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

	public PcAndPartsLogImp(PcAndPartsMapper pcAndPartsMapper)
	{
		this.pcAndPartsMapper = pcAndPartsMapper;
	}

	@Override
	public void ScrappingPcAndParts() throws IOException
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
	public List<PcAndPartsItem> getAllCpuPcAndParts() throws IOException
	{
		return pcAndPartsMapper.getAllCpu();
	}

	@Autowired
	public PcAndPartsItem pcAndPartsItem;

	@Autowired
	public PcAndPartsMapper pcAndPartsMapper;

	private void AddToItem(String itemName, String itemPrice, String itemUrl)
	{
		pcAndPartsItem.setItemname(itemName);
		pcAndPartsItem.setPrice(itemPrice);
		pcAndPartsItem.setUrl(itemUrl);
		pcAndPartsItem.setStoreid(1);
		pcAndPartsMapper.insertCpu(pcAndPartsItem);

	}

}
