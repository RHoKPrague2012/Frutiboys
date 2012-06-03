/**
 * 
 */
package models;

import javax.persistence.Entity;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
//@MongoEntity("organization")
@Entity
public class Organization extends Model /* MongoModel */ {

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
	
	public String bankAccount;
	
	public String code;
	
	public String type;
	
	public String www;
	
	public String email;
	
	public String phone;
	
	public String officeHours;
	
	public String dataBoxId;

	
	@Override
	public String toString() {
		if (StringUtils.isEmpty(name)) {
			return super.toString();
		} else {
			return name;
		}
	}
	
}
