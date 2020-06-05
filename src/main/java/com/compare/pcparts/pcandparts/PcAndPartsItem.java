package com.compare.pcparts.pcandparts;

public class PcAndPartsItem
{
    private int id;
    private String itemname;
    private int storeid;
    private boolean isavailable = true;
    private String price;
    private String url;
    private String imageref= "null";

    public String getItemname()
    {
        return itemname;
    }

    public void setItemname(String itemname)
    {
        this.itemname = itemname;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getStoreid()
    {
        return storeid;
    }

    public void setStoreid(int storeid)
    {
        this.storeid = storeid;
    }

    public boolean isIsavailable()
    {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable)
    {
        this.isavailable = isavailable;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getImageref()
    {
        return imageref;
    }

    public void setImageref(String imageref)
    {
        this.imageref = imageref;
    }
}
