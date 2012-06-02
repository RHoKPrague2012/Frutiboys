/**
 * 
 */
package controllers;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Organization;

import org.apache.commons.lang.StringUtils;

import play.mvc.Controller;

import com.google.common.collect.Lists;

/**
 * @author Vlastimil Dolejs (vlasta.dolejs@gmail.com)
 *
 */
public class OrganizationsRest extends Controller {

	public static void organizations() {
		Map<String, String> parameters = params.allSimple();
		parameters.remove("body"); // hack - play puts empty body parameter to each request???
		
		if (parameters.isEmpty()) {
			//help();
			render("OrganizationsRest/help.html");
		}
		
		String queryFindBy = "";
		List<String> queryParams = Lists.newArrayList();

		for (Entry<String, String> entry : parameters.entrySet()) {
			String fieldName = entry.getKey();
			fieldName = StringUtils.lowerCase(fieldName);
			fieldName = StringUtils.capitalize(fieldName);
			
			String value = entry.getValue();
			value = value.replace("*", "%");
			
			if (StringUtils.isEmpty(queryFindBy)) {
				queryFindBy = "by";
			} else {
				queryFindBy += "And";
			}
			
			queryFindBy += fieldName + "Ilike";
			queryParams.add(value);
		}
		
		List<Organization> organizations;
		if (StringUtils.isEmpty(queryFindBy)) {
			organizations = Organization.all().fetch();
		} else {
			organizations = Organization.find(queryFindBy, queryParams.toArray()).fetch();
		}
		
		if (organizations.isEmpty()) {
			// nothing found
		} else if (organizations.size() == 1) {
			renderJSON(organizations.get(0));
		} else {
			renderJSON(organizations);
		}
	}
	
	public static void help() {
		render();
	}
}
