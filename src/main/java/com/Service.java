package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.model;

@Path("/Billing")
public class Service {

	model billObj = new model(); 
	
	@POST
	@Path("/insert") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertBill(@FormParam("CustomerName") String CustomerName, 
	 @FormParam("AccountNo") String AccountNo, 
	 @FormParam("BillDate") String BillDate, 
	 @FormParam("BillTime") String BillTime) 
	{ 
	 String output = billObj.insertBill(CustomerName, AccountNo, BillDate, BillTime); 
	return output; 
	}
	
	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBill() 
	 { 
	 return billObj.readBill(); 
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBill(String billData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String BillId = billObject.get("BillId").getAsString(); 
	 String CustomerName = billObject.get("CustomerName").getAsString(); 
	 String AccountNo = billObject.get("AccountNo").getAsString(); 
	 String BillDate = billObject.get("BillDate").getAsString(); 
	 String BillTime = billObject.get("BillTime").getAsString(); 
	 String output = billObj.updateBill(BillId, CustomerName, AccountNo, BillDate, BillTime); 
	return output; 
	}

	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBill(String billData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String BillId = doc.select("BillId").text(); 
	 String output = billObj.deleteBill(BillId); 
	return output; 
	}

}
