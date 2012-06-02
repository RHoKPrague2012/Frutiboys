/**
 * 
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.modules.mongo.MongoEntity;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
@MongoEntity("organization")
public class Organization extends Model {

	public String name;
	
	public String address;

	public String contactAddress;
	
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
	
}
