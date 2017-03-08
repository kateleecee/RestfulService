package edu.sjsu.cmpe281;

import java.util.List;

import org.apache.jasper.compiler.Node.GetProperty;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

public class DataStore {
	private String ID = "id";
	private String FIRSTNAME = "firstName";
	private String LASTNAME = "lastName";
	private String EMPLOYEETABLE = "Employee";
	private String PROJECTTABLE = "Project";
	private String P_NAME = "name";
	private String P_BUDGET = "budget";
	private DatastoreService dataService;
	private Key entityKey = null;
	
	public DataStore()
	{
		dataService = DatastoreServiceFactory.getDatastoreService();
	}
	
	public boolean createEmployee(int id, String firstName, String lastName)
	{
		Entity employee = new Entity(EMPLOYEETABLE);
		employee.setProperty(ID, id);
		employee.setProperty(FIRSTNAME, firstName);
		employee.setProperty(LASTNAME, lastName);
		dataService.put(employee);
		try {
			if(dataService.get(employee.getKey()) != null)
			{
				return true;
			}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean createEmployee(Entity emp)
	{
		dataService.put(emp);
		try {
			if(dataService.get(emp.getKey()) != null)
			{
				return true;
			}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public Entity getEmployeeById(int id) throws EntityNotFoundException
	{
		if(isConflict(id)){
			return dataService.get(entityKey);
		}
		else{
			return null;
		}
	}
	
	public Iterable<Entity> getEmployees()
	{
		Query q = new Query(EMPLOYEETABLE);
		PreparedQuery pq = dataService.prepare(q);
		return pq.asIterable();
	}
	
	public String printEmployee(Entity emp)
	{
		String id = emp.getProperty("id").toString();
		String firstName = (String)emp.getProperty(FIRSTNAME);
		String lastName = (String)emp.getProperty(LASTNAME);
		return new String("Employee: " + id + firstName + " " + lastName);
	}
	
	public void clear(Iterable<Entity> entity)
	{
		for(Entity e:entity)
			dataService.delete(e.getKey());
	}
	
	public boolean createProject(int id, String name, String budget)
	{
		Entity project = new Entity(PROJECTTABLE);
		project.setProperty(ID, id);
		project.setProperty(P_NAME, name);
		project.setProperty(P_BUDGET, budget);
		dataService.put(project);
		try {
			if(dataService.get(project.getKey()) != null)
			{
				return true;
			}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean createProject(Entity pro)
	{
		dataService.put(pro);
		try {
			if(dataService.get(pro.getKey()) != null)
			{
				return true;
			}
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean updateEmployee(Entity emp) throws EntityNotFoundException
	{
		Integer id = Integer.parseInt(emp.getProperty("id").toString());
		if(entityKey!=null)
		{
			Entity emp_update = dataService.get(entityKey);
			if(!emp.getProperty(FIRSTNAME).toString().equals("")&&!emp.getProperty(FIRSTNAME).toString().equals("null")){
				emp_update.setProperty(FIRSTNAME, emp.getProperty(FIRSTNAME));
			}
			if(!emp.getProperty(LASTNAME).toString().equals("")&&!emp.getProperty(LASTNAME).toString().equals("null")){
				emp_update.setProperty(LASTNAME, emp.getProperty(LASTNAME));
			}
			return createEmployee(emp_update);
		}
		return false;
	}
	
	public boolean updateProject(Entity pro) throws EntityNotFoundException
	{
		Integer id = Integer.parseInt(pro.getProperty("id").toString());
		if(entityKey!=null)
		{
			Entity pro_update = dataService.get(entityKey);
			if(!pro.getProperty(P_NAME).toString().equals("")&&!pro.getProperty(P_NAME).toString().equals("null")){
				pro_update.setProperty(P_NAME, pro.getProperty(P_NAME));
			}
			if(!pro.getProperty(P_BUDGET).toString().equals("")&&!pro.getProperty(P_BUDGET).toString().equals("null")){
				pro_update.setProperty(P_BUDGET, pro.getProperty(P_BUDGET));
			}
			return createProject(pro_update);
		}
		return false;
	}

	public Entity getProjectById(Integer id2) throws EntityNotFoundException {
		if(isConflictPRO(id2)){
			return dataService.get(entityKey);
		}
		else{
			return null;
		}
	}

	public Iterable<Entity> getProjects() {
		Query q = new Query(PROJECTTABLE);
		PreparedQuery pq = dataService.prepare(q);
		return pq.asIterable();
	}

	public String printProject(Entity pro) {
		String id = pro.getProperty("id").toString();
		String name = (String)pro.getProperty(P_NAME);
		String budget = (String)pro.getProperty(P_BUDGET);
		return new String("Project: " + id + name + " " + budget);
	}
	
	public boolean isConflict(Integer id)
	{
		Iterable<Entity> emps = getEmployees();
		for(Entity emp: emps)
		{
			if(id.toString().equals(emp.getProperty("id").toString()))
			{
				entityKey = emp.getKey();
				return true;
			}
		}
		return false;
	}
	
	public boolean isConflictPRO(Integer id)
	{
		Iterable<Entity> pros = getProjects();
		for(Entity pro: pros)
		{
			if(id.toString().equals(pro.getProperty("id").toString()))
			{
				entityKey = pro.getKey();
				return true;
			}
		}
		return false;
	}
	
	public void deleteEmployee()
	{
		dataService.delete(entityKey);
	}

	public void deleteProject() {
		dataService.delete(entityKey);
	}
}
