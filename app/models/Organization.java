/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;

import play.db.jpa.Model;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
@Entity
public class Organization extends Model {

	public String name;
	
	public String addressStreet;
	public String addressCity;
	public String addressZipCode;

	//TODO: pokud se bude scrapovat kontaktni adresa, tak posefit tady a v helpu
//	public String contactAddress;
	
	public String eRegistry;
	
	/**
	 * IC
	 */
	public String organizationId;
	
	/**
	 * DIC
	 */
	public String taxId;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	public String bankAccount;
	
	public String code;
	
	public String type;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	public String www;
	
	public String email;
	
	public String phone;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	public String officeHours;
	
	public String dataBoxId;

	public Double latitude;
	public Double longitude;
	
	public void copyStateFrom(Organization organization) {
		this.name = organization.name;
		this.addressStreet = organization.addressStreet;
		this.addressCity = organization.addressCity;
		this.addressZipCode = organization.addressZipCode;
		this.eRegistry = organization.eRegistry;
		this.organizationId = organization.organizationId;
		this.taxId = organization.taxId;
		this.bankAccount = organization.bankAccount;
		this.code = organization.code;
		this.type = organization.type;
		this.www = organization.www;
		this.email = organization.email;
		this.phone = organization.phone;
		this.officeHours = organization.officeHours;
		this.dataBoxId = organization.dataBoxId;
		this.latitude = organization.latitude;
		this.longitude = organization.longitude;
	}
	
	public String getAddress() {
		return addressStreet + ", " + addressCity + " " + addressZipCode + ", Czech Republic";
	}
	
	@Override
	public String toString() {
		if (StringUtils.isEmpty(name)) {
			return super.toString();
		} else {
			return name;
		}
	}
	
}
