package com;
 
import model.ResearcherMain;
import model.Researchers;
//For REST Service ----
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON ---
import com.google.gson.*;

//For XML ---
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Researchers")
public class ResearcherService {
	
	Researchers researcherObj = new Researchers();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readResearcher()
	{
		return researcherObj.readResearcher();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("RID") String RID,
			@FormParam("rName") String rName,
			@FormParam("rPhone") String rPhone,
			@FormParam("rEmail") String rEmail,
			@FormParam("rAddress") String rAddress,
			@FormParam("projectName") String projectName,
			@FormParam("rCost") String rCost) {
		
		System.out.println("CREATE RESEARCHER");
		ResearcherMain rs = new  ResearcherMain();
		rs.setRId(RID);
		rs.setProjectName(projectName);
		rs.setrPhone(rPhone);
		rs.setrEmail(rEmail);
		rs.setrAddress(rAddress);
		rs.setrCost(rCost);
		rs.setrName(rName);
	
		String output = researcherObj.insertResearcher(RID,rName, rPhone, rEmail, rAddress, projectName, rCost);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String researchData)
	{
		//Convert the input string to a JSON object
		JsonObject researchObject = new JsonParser().parse(researchData).getAsJsonObject();
		//Read the values from the JSON object
		String RID = researchObject.get("RID").getAsString();
		String rName = researchObject.get("rName").getAsString();
		String rPhone = researchObject.get("rPhone").getAsString();
		String rEmail = researchObject.get("rEmail").getAsString();
		String rAddress = researchObject.get("rAddress").getAsString();
		String projectName = researchObject.get("projectName").getAsString();
		String rCost = researchObject.get("rCost").getAsString();
		String output = researcherObj.updateResearcher(RID, rName, rPhone, rEmail, rAddress,projectName, rCost);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcher(String researcherData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String RID = doc.select("RID").text();
		String output = researcherObj.deleteResearcher(RID);
		return output;
	}
	

}

