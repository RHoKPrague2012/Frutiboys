/**
 * 
 */
package controllers;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
public class Security extends controllers.Secure.Security {
	
	static boolean authenticate(String username, String password) {
		if ("master".equals(username) && "mojemamajekrasnaholka".equals(password)) {
			return true;
		}
		
		return false;
    }
	
}
