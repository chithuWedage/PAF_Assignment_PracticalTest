package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResearcherMain {
	
	private String RId;
	private String rName;
	private String rPhone;
	private String rEmail;
	private String rAddress;
	private String projectName;
	private String rCost;
	
	public String getRId() {
		return RId;
	}
	public void setRId(String rId) {
		RId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrPhone() {
		return rPhone;
	}
	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}
	public String getrEmail() {
		return rEmail;
	}
	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}
	public String getrAddress() {
		return rAddress;
	}
	public void setrAddress(String rAddress) {
		this.rAddress = rAddress;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getrCost() {
		return rCost;
	}
	public void setrCost(String rCost) {
		this.rCost = rCost;
	}
	
	@Override
	public String toString() {
		return "Researcher [rId=" + RId + ", rName=" + rName
				+ ", rPhone=" + rPhone + ", rEmail=" + rEmail + ", rAddress=" + rAddress
				+ ", projectName=" + projectName + ", rCost=" + rCost +"]";
	}
	
	

}
