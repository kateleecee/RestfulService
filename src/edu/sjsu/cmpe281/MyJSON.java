package edu.sjsu.cmpe281;

import java.io.IOException;

import org.dom4j.Element;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class MyJSON {
	private JSONObject Json = null;
	
	public String employeeJSON(String id, String fName, String lName) throws JSONException
	{
		Json = new JSONObject();
		Json.put("id", id);
		Json.put("firstName", fName);
		Json.put("lastName", lName);
		System.out.println(Json.toString(4));
		return Json.toString(4);
	}
	
	public String employeeAllJSON() throws JSONException
	{
		JSONArray jarr = new JSONArray();
		DataStore ds = new DataStore();
		Iterable<Entity> emps = ds.getEmployees();
		for (Entity emp : emps) {
			Json = new JSONObject();
			Json.put("id", emp.getProperty("id").toString());
			Json.put("firstName", emp.getProperty("firstName").toString());
			Json.put("lastName", emp.getProperty("lastName").toString());
			jarr.put(Json);
		}
		
		System.out.println(jarr.toString(4));
		return jarr.toString(4);
	}
	
	public String projectAllJSON() throws JSONException
	{
		JSONArray jarr = new JSONArray();
		DataStore ds = new DataStore();
		Iterable<Entity> pros = ds.getProjects();
		for (Entity emp : pros) {
			
			Json = new JSONObject();
			Json.put("id", emp.getProperty("id").toString());
			Json.put("name", emp.getProperty("name").toString());
			Json.put("budget", emp.getProperty("budget").toString());
			jarr.put(Json);
		}
		
		System.out.println(jarr.toString(4));
		return jarr.toString(4);
	}

	public String projectJSON(String string, String property, String property2) throws JSONException {
		Json = new JSONObject();
		Json.put("id", string);
		Json.put("name", property);
		Json.put("budget", property2);
		return Json.toString(4);
	}
	
	public Entity parseEmployeeJson(String json) throws JSONException
	{
		JSONObject jobj = new JSONObject(json);
		Integer id = jobj.getInt("id");
		String firstname = jobj.getString("firstName");
		String lastname = jobj.getString("lastName");
		Entity employee = new Entity("Employee");
		employee.setProperty("id", id);
		employee.setProperty("firstName", firstname);
		employee.setProperty("lastName", lastname);
		return employee;
	}
	
	public Entity parseProjectJson(String json) throws JSONException
	{
		JSONObject jobj = new JSONObject(json);
		Integer id = jobj.getInt("id");
		String firstname = jobj.getString("name");
		String lastname = jobj.getString("budget");
		Entity project = new Entity("Project");
		project.setProperty("id", id);
		project.setProperty("name", firstname);
		project.setProperty("budget", lastname);
		return project;
	}
}
