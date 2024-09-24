package tw.rc.model;

public class Cust {
	private String companyName;
	private String city;
	private String country;
	
	public Cust() {	}
	public Cust(String compantName, String city, String country) {
		this.companyName = compantName;
		this.city = city;
		this.country = country;
	}
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
