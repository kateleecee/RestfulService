package edu.sjsu.cmpe281;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class ProjectServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String[] str = req.getRequestURL().toString().split("/");
		String[] urlbase = new String(URLextra.getBaseUrl(req)+"/cmpe281qili352/rest/project/m").split("/");
		if(req.getRequestURL().toString().equals(URLextra.getBaseUrl(req)+"/cmpe281qili352/rest/project/")){
			Integer id = Integer.parseInt(req.getParameter("pro_id"));
			String contentType = req.getParameter("content-type");
			URLextra.setID(id);
			URLextra.setCType(contentType);
			resp.sendRedirect(URLextra.getBaseUrl(req)+"/cmpe281qili352/rest/project/"+URLextra.getID());
		}
//		resp.getWriter().println("<body onload=\"myFunction()\"><script>function myFunction() {window.history.pushState({},0,'http://'+window.location.host+'/cmpe281qili352/rest/employee/'+"+id+");}</script></body>" );
		if(str.length == urlbase.length){
			String idstr = str[str.length-1];
			URLextra.setID(Integer.parseInt(idstr));
		DataStore ds = new DataStore();
		try{
		Integer id = URLextra.getID();
		Entity pro = ds.getProjectById(id);
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
		resp.getWriter().println("<xmp>"+xmld.projectXML(id.toString(), (String)pro.getProperty("name"), (String)pro.getProperty("budget"))+"</xmp>");
		}else if(URLextra.getCType().equals("JSON")){
		MyJSON jsond = new MyJSON();
		resp.getWriter().println("<pre><code>"+jsond.projectJSON(id.toString(), (String)pro.getProperty("name"), (String)pro.getProperty("budget"))+"</code></pre>");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			resp.sendError(404, "Not found (404)");
		}
		}
		if(req.getRequestURI().toString().endsWith("project"))
		{
			try{
			try{
				String contentType = req.getParameter("content-type");
				URLextra.setCType(contentType);
			}catch(Exception e){}
			if(URLextra.getCType().equals("XML")){
			MyXML xmld = new MyXML();
			resp.getWriter().println("<xmp>"+xmld.projectAllXML()+"</xmp>");
			}else if(URLextra.getCType().equals("JSON")){
			MyJSON jsond = new MyJSON();
			resp.getWriter().println("<pre><code>"+jsond.projectAllJSON()+"</code></pre>");
			}
			}catch(Exception e)
			{
				resp.sendError(404, "Not found (404)");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reqString = req.getReader().readLine();
		resp.getWriter().println(reqString);
		if(reqString.endsWith(">")){
			resp.setContentType("application/xml");
			resp.getWriter().println("Hi XML");
			DataStore dsDataStore = new DataStore();
			MyXML xmld = new MyXML();
			try {
				if(!dsDataStore.isConflictPRO(Integer.parseInt(xmld.parseProject(reqString).getProperty("id").toString())))
				{
					try {
					if(dsDataStore.createProject(xmld.parseProject(reqString))){
						resp.getWriter().println("create a project");
						resp.setStatus(201);
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
				if(!dsDataStore.isConflict(Integer.parseInt(jsond.parseProjectJson(reqString).getProperty("id").toString())))
				{
					try {
					if(dsDataStore.createProject(jsond.parseProjectJson(reqString))){
						resp.getWriter().println("create a project");
						resp.setStatus(201);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				if(dsDataStore.isConflictPRO(Integer.parseInt(xmld.parseProject(reqString).getProperty("id").toString())))
				{
					try {
						System.out.println("id"+xmld.parseProject(reqString).getProperty("id").toString());
					if(dsDataStore.updateProject(xmld.parseProject(reqString))){
						resp.getWriter().println("update a project");
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
				if(dsDataStore.isConflictPRO(Integer.parseInt(jsond.parseProjectJson(reqString).getProperty("id").toString())))
				{
					try {
					if(dsDataStore.updateProject(jsond.parseProjectJson(reqString))){
						resp.getWriter().println("update a project");
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
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] str = req.getRequestURL().toString().split("/");
		String id = str[str.length-1];
		DataStore ds = new DataStore();
		if(ds.isConflictPRO(Integer.parseInt(id)))
		{
			ds.deleteProject();
			resp.getWriter().println("Delete a project");
		}else{
			resp.sendError(404);
		}
	}
}
