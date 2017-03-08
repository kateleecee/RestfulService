package edu.sjsu.cmpe281;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class GetEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String[] str = req.getRequestURL().toString().split("/");
		System.out.println("1:"+str.length);
		String[] urlbase = new String(URLextra.getBaseUrl(req)+"/cmpe281qili352/rest/employee/m").split("/");
		System.out.println("2:"+urlbase.length);
		if(req.getRequestURL().toString().equals(URLextra.getBaseUrl(req)+"/cmpe281qili352/rest/employee/")){
			Integer id = Integer.parseInt(req.getParameter("emp_id"));
			String contentType = req.getParameter("content-type");
			URLextra.setID(id);
			URLextra.setCType(contentType);
			resp.sendRedirect(URLextra.getBaseUrl(req)+"/cmpe281qili352/rest/employee/"+URLextra.getID());
		}
		if(str.length == urlbase.length){
			System.out.println("3:"+urlbase.length);
			String idstr = str[str.length-1];
			System.out.println(idstr);
			URLextra.setID(Integer.parseInt(idstr));
			DataStore ds = new DataStore();
			try{
			Integer id = URLextra.getID();
			Entity emp = ds.getEmployeeById(id);
			//
			try{
				String contentType = req.getParameter("content-type");
				if(contentType.equals("")){
					URLextra.setCType(URLextra.getCType());
				}
				else{
				URLextra.setCType(contentType);
				}
			}catch(Exception e){
				
			}
			if(URLextra.getCType().equals("XML")){
			MyXML xmld = new MyXML();
			resp.getWriter().println("<xmp>"+xmld.emploeeXML(id.toString(), (String)emp.getProperty("firstName"), (String)emp.getProperty("lastName"))+"</xmp>");
			}else if(URLextra.getCType().equals("JSON")){
			MyJSON jsond = new MyJSON();
			resp.getWriter().println("<pre><code>"+jsond.employeeJSON(id.toString(), (String)emp.getProperty("firstName"), (String)emp.getProperty("lastName"))+"</code></pre>");
			}
			}catch(Exception e)
			{
				e.printStackTrace();
				resp.sendError(404, "Not found (404)");
			}
		}else if(req.getRequestURI().toString().endsWith("employee"))
		{
			try{
			try{
				String contentType = req.getParameter("content-type");
				URLextra.setCType(contentType);
			}catch(Exception e){}
			if(URLextra.getCType().equals("XML")){
			MyXML xmld = new MyXML();
			resp.getWriter().println("<xmp>"+xmld.employeeAllXML()+"</xmp>");
			}else if(URLextra.getCType().equals("JSON")){
			MyJSON jsond = new MyJSON();
			resp.getWriter().println("<pre><code>"+jsond.employeeAllJSON()+"</code></pre>");
			}
			}catch(Exception e)
			{
				resp.sendError(404, "Not found (404)");
			}
		}
//		resp.getWriter().println("<body onload=\"myFunction()\"><script>function myFunction() {window.history.pushState({},0,'http://'+window.location.host+'/cmpe281qili352/rest/employee/'+"+id+");}</script></body>" );
		
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reqString = req.getReader().readLine();
		resp.getWriter().println(reqString);
		if(reqString.endsWith(">")){
			resp.setContentType("application/xml");
			resp.getWriter().println("Hi XML");
			DataStore dsDataStore = new DataStore();
			MyXML xmld = new MyXML();
			try {
				if(dsDataStore.isConflict(Integer.parseInt(xmld.parseEmployee(reqString).getProperty("id").toString())))
				{
					try {
						System.out.println("id"+xmld.parseEmployee(reqString).getProperty("id").toString());
					if(dsDataStore.updateEmployee(xmld.parseEmployee(reqString))){
						resp.getWriter().println("update an employee");
						
					}
				} catch (DocumentException | EntityNotFoundException e) {
//					 TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					resp.sendError(404, "Not found (404)");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		else{
			resp.setContentType("application/json");
			resp.getWriter().println("Hi JSON");
			DataStore dsDataStore = new DataStore();
			MyJSON jsond = new MyJSON();
			try {
				if(dsDataStore.isConflict(Integer.parseInt(jsond.parseEmployeeJson(reqString).getProperty("id").toString())))
				{
					try {
					if(dsDataStore.updateEmployee(jsond.parseEmployeeJson(reqString))){
						resp.getWriter().println("update an employee");
					}
				} catch (JSONException | EntityNotFoundException e) {
//					 TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					resp.sendError(404, "Not found (404)");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String empId = req.getParameter("emp_id_post");
//		String firstname = req.getParameter("emp_firstname");
//		String lastname = req.getParameter("emp_lastname");
//		if(empId!=""&&firstname!=""&&lastname!="")
//		{
//			DataStore ds = new DataStore();
//			if(!ds.isConflict(Integer.parseInt(empId))){
//				if(ds.createEmployee(Integer.parseInt(empId), firstname, lastname))
//				{
//					resp.getWriter().println();
//				};
//			}
//			else{
//				resp.sendError(409, "Conflict (409)");
//			}
//		}
		resp.setStatus(201);
		String reqString = req.getReader().readLine();
		resp.getWriter().println(reqString);
		if(reqString.endsWith(">")){
			resp.setContentType("application/xml");
			resp.getWriter().println("Hi XML");
			DataStore dsDataStore = new DataStore();
			MyXML xmld = new MyXML();
			try {
				if(!dsDataStore.isConflict(Integer.parseInt(xmld.parseEmployee(reqString).getProperty("id").toString())))
				{
					try {
					if(dsDataStore.createEmployee(xmld.parseEmployee(reqString))){
						resp.getWriter().println("create an employee");
						
					}
				} catch (DocumentException e) {
//					 TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					resp.sendError(409, "Conflict (409)");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		else{
			resp.setContentType("application/json");
			resp.getWriter().println("Hi JSON");
			DataStore dsDataStore = new DataStore();
			MyJSON jsond = new MyJSON();
			try {
				if(!dsDataStore.isConflict(Integer.parseInt(jsond.parseEmployeeJson(reqString).getProperty("id").toString())))
				{
					try {
					if(dsDataStore.createEmployee(jsond.parseEmployeeJson(reqString))){
						resp.getWriter().println("create an employee");
					}
				} catch (JSONException e) {
//					 TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					resp.sendError(409, "Conflict (409)");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated castch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] str = req.getRequestURL().toString().split("/");
		String id = str[str.length-1];
		DataStore ds = new DataStore();
		if(ds.isConflict(Integer.parseInt(id)))
		{
			ds.deleteEmployee();
			resp.getWriter().println("Delete an employee");
		}else{
			resp.sendError(404);
		}
	}
	
}
