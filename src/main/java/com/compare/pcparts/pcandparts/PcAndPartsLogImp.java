package com.compare.pcparts.pcandparts;

import com.compare.pcparts.mapper.PcAndPartsMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class PcAndPartsLogImp implements PcAndPartsLog
{
	private static final String baseUrl1 = "https://pcandparts.com/computer-cases/";
	private static final String baseUrl2 = "https://pcandparts.com/cpu/";
	private static final String baseUrl = "https://pcandparts.com/video-card/";

	private final String computercase = "https://pcandparts.com/computer-cases/";
	private final String coolingfan = "https://pcandparts.com/cooling/";
	private final String cpuurl = "https://pcandparts.com/cpu/";
	private final String memory = "https://pcandparts.com/memory/";
	private final String motherboard = "https://pcandparts.com/motherboard/";
	private final String psu = "https://pcandparts.com/power-supplies/";
	private final String storage1 = "https://pcandparts.com/storage-hdd-hard-drives-nvme-ssd-m2/";
	private final String storage2 = "https://pcandparts.com/storage-hdd-hard-drives-nvme-ssd-m2/page/2/";
	private final String graphiccard = "https://pcandparts.com/video-card/";

	WebClient client = new WebClient();

	@Autowired
	private PcAndPartsMapper pcAndPartsMapper;
/*
	@Autowired
    private PcAndPartsItem pcAndPartsItem;

 */

	private void AddToItem(String itemName, String itemPrice, String itemUrl)
	{
		PcAndPartsItem pcAndPartsItem = new PcAndPartsItem();
		pcAndPartsItem.setId(1);
		pcAndPartsItem.setItemName(itemName);
		pcAndPartsItem.setPrice(itemPrice);
		pcAndPartsItem.setUrl(itemUrl);
		pcAndPartsItem.setStoreId(1);
        System.out.println(pcAndPartsItem);
		pcAndPartsMapper.insertCpu(pcAndPartsItem);

	}

	@Override
	public void ScrappingPcAndParts() throws IOException
	{

		System.out.println("Hi");
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setUseInsecureSSL(true);
		HtmlPage page = client.getPage(cpuurl);
		List<HtmlDivision> items = page.getByXPath("//div[@class='product-small box ']");


		//try
		//{
			for(HtmlElement element : items)
			{

				HtmlElement productName = element.getFirstByXPath(".//p[@class='name product-title woocommerce-loop-product__title']/a[@href]");
				HtmlElement productPrice = element.getFirstByXPath(".//span[@class='woocommerce-Price-amount amount']");
				HtmlAnchor element3 = element.getFirstByXPath(".//p[@class='name product-title woocommerce-loop-product__title']/a");
				String itemName = productName.getVisibleText();
				String itemPrice = productPrice.getVisibleText();
				String itemUrl = element3.getHrefAttribute();
				String description = ("Product Name: " + itemName + "\nProduct Price: " + itemPrice + "\nURL: " + itemUrl);
				System.out.println(description);
				AddToItem(itemName, itemPrice, itemUrl);
			}
		//}
		/*
		catch(NullPointerException ne){
            System.out.println("Scrape stopped. Reason: Can not continue because of Null Pointer Exception."+ne);
        }

		 */
    }
}
