package edu.sjsu.cmpe281;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class CMPE281restQiLi352Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		MyXML xmld = new MyXML();
		resp.getWriter().println("<!DOCTYPE html>");
		resp.getWriter().println("<html><head><script type=\"text/javascript\">" );
//		resp.getWriter().println("window.onload=function(){");
//		resp.getWriter().println( "}");
		resp.getWriter().println("function postt(){");
		resp.getWriter().println("if(document.getElementById(\"xml\").checked){");
		
		resp.getWriter().println("postF();}");
		resp.getWriter().println("if(document.getElementById(\"json\").checked){");
		resp.getWriter().println("postJ();}}");
		resp.getWriter().println("function postF(){" );
		resp.getWriter().println("var http =  new XMLHttpRequest();");
		resp.getWriter().println("http.onreadystatechange = function() {");
		resp.getWriter().println("if (this.readyState == 4 && this.status == 200) {");
		resp.getWriter().println("alert(http.responseText);");
		resp.getWriter().println("}");
		resp.getWriter().println("};");
		resp.getWriter().println("http.open(\"POST\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/employee\", true);");
		resp.getWriter().println("http.send(getxml());}");
		
		resp.getWriter().println("function postJ(){" );
		resp.getWriter().println("var http =  new XMLHttpRequest();");
		resp.getWriter().println("http.onreadystatechange = function() {");
		resp.getWriter().println("if (this.readyState == 4 && this.status == 200) {");
		resp.getWriter().println("alert(http.responseText);");
		resp.getWriter().println("}");
		resp.getWriter().println("};");
		resp.getWriter().println("http.open(\"POST\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/employee\", true);");
		resp.getWriter().println("http.send(getjson());}");
		
		resp.getWriter().println("function getxml(){");
		resp.getWriter().println("var id = document.getElementById(\"post_id\").value;");
		resp.getWriter().println( "var fname = document.getElementById(\"post_EFName\").value;");
		resp.getWriter().println("var lname = document.getElementById(\"post_ELName\").value;");
		resp.getWriter().println("var xmlString = \"<employee><id>\"+id+\"</id><firstName>\"+fname+\"</firstName><lastName>\"+lname+\"</lastName></employee>\";");
		resp.getWriter().println("return xmlString;}");
		
		resp.getWriter().println("function getjson(){");
		resp.getWriter().println("var id = document.getElementById(\"post_id\").value;");
		resp.getWriter().println( "var fname = document.getElementById(\"post_EFName\").value;");
		resp.getWriter().println("var lname = document.getElementById(\"post_ELName\").value;");
		resp.getWriter().println("var json = {\"id\":id, \"firstName\":fname, \"lastName\":lname};");
		resp.getWriter().println("var jsonString = JSON.stringify(json);");
		
		resp.getWriter().println("return jsonString;}");
		
		//project_post
		resp.getWriter().println("function postProject(){");
		resp.getWriter().println("if(document.getElementById(\"xml_pro\").checked){");
		resp.getWriter().println("postFPRO();}");
		resp.getWriter().println("if(document.getElementById(\"json_pro\").checked){");
		resp.getWriter().println("postJPRO();}}");
		//post_method
		resp.getWriter().println("function postFPRO(){" );
		resp.getWriter().println("var http =  new XMLHttpRequest();");
		resp.getWriter().println("http.onreadystatechange = function() {");
		resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==409)) {");
		resp.getWriter().println("alert(http.responseText);");
		resp.getWriter().println("}");
		resp.getWriter().println("};");
		resp.getWriter().println("http.open(\"POST\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/project\", true);");
		resp.getWriter().println("http.send(getxmlPRO());}");
		
		resp.getWriter().println("function postJPRO(){" );
		resp.getWriter().println("var http =  new XMLHttpRequest();");
		resp.getWriter().println("http.onreadystatechange = function() {");
		resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==409)) {");
		resp.getWriter().println("alert(http.responseText);");
		resp.getWriter().println("}");
		resp.getWriter().println("};");
		resp.getWriter().println("http.open(\"POST\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/project\", true);");
		resp.getWriter().println("http.send(getjsonPRO());}");
		//getxmlPRO
		resp.getWriter().println("function getxmlPRO(){");
		resp.getWriter().println("var id = document.getElementById(\"post_id_pro\").value;");
		resp.getWriter().println( "var pname = document.getElementById(\"post_proName\").value;");
		resp.getWriter().println("var budget = document.getElementById(\"post_proBudget\").value;");
		resp.getWriter().println("var xmlString = \"<project><id>\"+id+\"</id><name>\"+pname+\"</name><budget>\"+budget+\"</budget></project>\";");
		resp.getWriter().println("return xmlString;}");
		//getjsonPRO
		resp.getWriter().println("function getjsonPRO(){");
		resp.getWriter().println("var id = document.getElementById(\"post_id_pro\").value;");
		resp.getWriter().println( "var pname = document.getElementById(\"post_proName\").value;");
		resp.getWriter().println("var budget = document.getElementById(\"post_proBudget\").value;");
		resp.getWriter().println("var json = {\"id\":id, \"name\":pname, \"budget\":budget};");
		resp.getWriter().println("var jsonString = JSON.stringify(json);");
		resp.getWriter().println("return jsonString;}");
		//putEmployee
		resp.getWriter().println("function putEmployee(){");
		resp.getWriter().println("if(document.getElementById(\"xml_putEmp\").checked){");
		resp.getWriter().println("putF();}");
		resp.getWriter().println("if(document.getElementById(\"json_putEmp\").checked){");
		resp.getWriter().println("putJ();}}");
		//put_method
		resp.getWriter().println("function putF(){" );
		resp.getWriter().println("var http =  new XMLHttpRequest();");
		resp.getWriter().println("http.onreadystatechange = function() {");
		resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==404)) {");
		resp.getWriter().println("alert(http.responseText);");
		resp.getWriter().println("}");
		resp.getWriter().println("};");
		resp.getWriter().println("var put_id = document.getElementById(\"put_id_emp\").value;");
		resp.getWriter().println("http.open(\"PUT\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/employee/\"+put_id, true);");
		resp.getWriter().println("http.send(getxmlPUTEMP());}");
		
		resp.getWriter().println("function putJ(){" );
		resp.getWriter().println("var http =  new XMLHttpRequest();");
		resp.getWriter().println("http.onreadystatechange = function() {");
		resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==404)) {");
		resp.getWriter().println("alert(http.responseText);");
		resp.getWriter().println("}");
		resp.getWriter().println("};");
		resp.getWriter().println("var put_id = document.getElementById(\"put_id_emp\").value;");
		resp.getWriter().println("http.open(\"PUT\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/employee/\"+put_id, true);");
		resp.getWriter().println("http.send(getjsonPUTEMP());}");
		//putString
		resp.getWriter().println("function getxmlPUTEMP(){");
		resp.getWriter().println("var id = document.getElementById(\"put_id_emp\").value;");
		resp.getWriter().println( "var fname = document.getElementById(\"put_fname\").value;");
		resp.getWriter().println("var lname = document.getElementById(\"put_lname\").value;");
		resp.getWriter().println("var xmlString = \"<employee><id>\"+id+\"</id><firstName>\"+fname+\"</firstName><lastName>\"+lname+\"</lastName></employee>\";");
		resp.getWriter().println("return xmlString;}");
		
		resp.getWriter().println("function getjsonPUTEMP(){");
		resp.getWriter().println("var id = document.getElementById(\"put_id_emp\").value;");
		resp.getWriter().println( "var fname = document.getElementById(\"put_fname\").value;");
		resp.getWriter().println("var lname = document.getElementById(\"put_lname\").value;");
		resp.getWriter().println("var json = {\"id\":id, \"firstName\":fname, \"lastName\":lname};");
		resp.getWriter().println("var jsonString = JSON.stringify(json);return jsonString;}");
		//putProject
				resp.getWriter().println("function putProject(){");
				resp.getWriter().println("if(document.getElementById(\"xml_putPro\").checked){");
				resp.getWriter().println("putFPRO();}");
				resp.getWriter().println("if(document.getElementById(\"json_putPro\").checked){");
				resp.getWriter().println("putJPRO();}}");
				//put_method
				resp.getWriter().println("function putFPRO(){" );
				resp.getWriter().println("var http =  new XMLHttpRequest();");
				resp.getWriter().println("http.onreadystatechange = function() {");
				resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==404)) {");
				resp.getWriter().println("alert(http.responseText);");
				resp.getWriter().println("}");
				resp.getWriter().println("};");
				resp.getWriter().println("var put_id = document.getElementById(\"put_id_pro\").value;");
				resp.getWriter().println("http.open(\"PUT\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/project/\"+put_id, true);");
				resp.getWriter().println("http.send(getxmlPUTPRO());}");
				
				resp.getWriter().println("function putJPRO(){" );
				resp.getWriter().println("var http =  new XMLHttpRequest();");
				resp.getWriter().println("http.onreadystatechange = function() {");
				resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==404)) {");
				resp.getWriter().println("alert(http.responseText);");
				resp.getWriter().println("}");
				resp.getWriter().println("};");
				resp.getWriter().println("var put_id = document.getElementById(\"put_id_pro\").value;");
				resp.getWriter().println("http.open(\"PUT\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/project/\"+put_id, true);");
				resp.getWriter().println("http.send(getjsonPUTPRO());}");
				//putString
				resp.getWriter().println("function getxmlPUTPRO(){");
				resp.getWriter().println("var id = document.getElementById(\"put_id_pro\").value;");
				resp.getWriter().println( "var pname = document.getElementById(\"put_proName\").value;");
				resp.getWriter().println("var budget = document.getElementById(\"put_proBudget\").value;");
				resp.getWriter().println("var xmlString = \"<project><id>\"+id+\"</id><name>\"+pname+\"</name><budget>\"+budget+\"</budget></project>\";");
				resp.getWriter().println("return xmlString;}");
				
				resp.getWriter().println("function getjsonPUTPRO(){");
				resp.getWriter().println("var id = document.getElementById(\"put_id_pro\").value;");
				resp.getWriter().println( "var pname = document.getElementById(\"put_proName\").value;");
				resp.getWriter().println("var budget = document.getElementById(\"put_proBudget\").value;");
				resp.getWriter().println("var json = {\"id\":id, \"name\":pname, \"budget\":budget};");
				resp.getWriter().println("var jsonString = JSON.stringify(json);");
				resp.getWriter().println("return jsonString;}");
				
				//delete
				resp.getWriter().println("function deleteEmployee(){" );
				resp.getWriter().println("var http =  new XMLHttpRequest();");
				resp.getWriter().println("http.onreadystatechange = function() {");
				resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==404)) {");
				resp.getWriter().println("alert(http.responseText);");
				resp.getWriter().println("}");
				resp.getWriter().println("};");
				resp.getWriter().println("var id = document.getElementById(\"emp_id_del\").value;");
				resp.getWriter().println("http.open(\"DELETE\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/employee/\"+id, true);");
				resp.getWriter().println("http.send();}");
				
				resp.getWriter().println("function deleteProject(){" );
				resp.getWriter().println("var http =  new XMLHttpRequest();");
				resp.getWriter().println("http.onreadystatechange = function() {");
				resp.getWriter().println("if (this.readyState == 4 && (this.status == 200 || this.status==404)) {");
				resp.getWriter().println("alert(http.responseText);");
				resp.getWriter().println("}");
				resp.getWriter().println("};");
				resp.getWriter().println("var id = document.getElementById(\"pro_id_del\").value;");
				resp.getWriter().println("http.open(\"DELETE\", window.location.protocol+\"//\"+window.location.host + \"/\"+\"cmpe281qili352/rest/project/\"+id, true);");
				resp.getWriter().println("http.send();}");
				
		resp.getWriter().println("</script></head><body>");
		DataStore ds = new DataStore();
//		ds.clear(ds.getEmployees());
//		ds.clear(ds.getProjects());
//		if (ds.createEmployee(7, "Bob", "Dyln")) {
//			ds.createEmployee(8, "Nancy", "Stone");
			Iterable<Entity> emps = ds.getEmployees();
			for (Entity emp : emps) {
				resp.getWriter().println("<p>" + ds.printEmployee(emp) + "</p>");
			}
//		}
//		if (ds.createProject(3, "Bob", "345.6")) {
//			ds.createProject(0, "Nancy", "759.7");
			Iterable<Entity> pros = ds.getProjects();
			for (Entity pro : pros) {
				resp.getWriter().println("<p>" + ds.printProject(pro) + "</p>");
			}
//		}
		resp.getWriter().println(
				"<p>GET</p><form action=\"/cmpe281qili352/rest/employee/\" method=\"GET\">Rechieve employee with id: <input type=\"text\" name=\"emp_id\">"
						+ "<br><input type=\"radio\" name=\"content-type\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" value=\"JSON\">JSON<br>"
						+ "<input type=\"submit\" value=\"Submit\"></form>");
		resp.getWriter().println(
				"<form action=\"/cmpe281qili352/rest/project/\" method=\"GET\">Rechieve project with id: <input type=\"text\" name=\"pro_id\">"
						+ "<br><input type=\"radio\" name=\"content-type\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" value=\"JSON\">JSON<br>"
						+ "<input type=\"submit\" value=\"Submit\"></form>");
		resp.getWriter().println("<p>POST</p>");
		resp.getWriter()
				.println("<form name=\"post_function\">"
						+ "Employee's id: <input type=\"text\" id=\"post_id\" name=\"emp_id_post\" required>" + "<br>"
						+ "Employee's First Name: <input type=\"text\" id=\"post_EFName\" name=\"emp_firstname\" required>" + "<br>"
						+ "Employee's Last Name: <input type=\"text\" id=\"post_ELName\" name=\"emp_lastname\" required>"
						+ "<br><input type=\"radio\" name=\"content-type\" id=\"xml\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" id=\"json\" value=\"JSON\">JSON<br>"
						+ "<input type=\"button\" value=\"sure\" onclick=\"postt()\">" + "<br><br>");
//						+ "<input type=\"submit\" value=\"Submit\"></form>");
		resp.getWriter().println("<p></p>");
		resp.getWriter()
				.println("<form name=\"post_project\">"
						+ "Project id: <input type=\"text\" id=\"post_id_pro\" name=\"pro_id_post\" required>" + "<br>"
						+ "Project Name: <input type=\"text\" id=\"post_proName\" name=\"pro_firstname\" required>" + "<br>"
						+ "Project Budget: <input type=\"text\" id=\"post_proBudget\" name=\"pro_lastname\" required>"
						+ "<br><input type=\"radio\" name=\"content-type\" id=\"xml_pro\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" id=\"json_pro\" value=\"JSON\">JSON<br>"
						+ "<input type=\"button\" value=\"sure\" onclick=\"postProject()\">" + "</form></body></html>");
		resp.getWriter().println("<p>PUT</p>");
		resp.getWriter()
				.println("<form name=\"put_emp\">"
						+ "Employee's id: <input type=\"text\" id=\"put_id_emp\" name=\"emp_id_put\" required>" + "<br>"
						+ "Employee's First Name: <input type=\"text\" id=\"put_fname\" name=\"put_firstname\" required>" + "<br>"
						+ "Employee's Last Name: <input type=\"text\" id=\"put_lname\" name=\"put_lastname\" required>"
						+ "<br><input type=\"radio\" name=\"content-type\" id=\"xml_putEmp\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" id=\"json_putEmp\" value=\"JSON\">JSON<br>"
						+ "<input type=\"button\" value=\"sure\" onclick=\"putEmployee()\">" + "</form></body></html>");
		resp.getWriter().println("<p></p>");
		resp.getWriter()
				.println("<form name=\"put_project\">"
						+ "Project id: <input type=\"text\" id=\"put_id_pro\" name=\"pro_id_put\" required>" + "<br>"
						+ "Project Name: <input type=\"text\" id=\"put_proName\" name=\"proput_firstname\" required>" + "<br>"
						+ "Project Budget: <input type=\"text\" id=\"put_proBudget\" name=\"proput_lastname\" required>"
						+ "<br><input type=\"radio\" name=\"content-type\" id=\"xml_putPro\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" id=\"json_putPro\" value=\"JSON\">JSON<br>"
						+ "<input type=\"button\" value=\"sure\" onclick=\"putProject()\">"
						+ "</form></body></html>");
		resp.getWriter().println(
				"<p>DELETE</p><form>Delete employee with id: <input type=\"text\" id=\"emp_id_del\">"
						+ "<input type=\"button\" value=\"delete\" onclick=\"deleteEmployee()\"></form>");
		resp.getWriter().println(
				"<form>Delete project with id: <input type=\"text\" id=\"pro_id_del\">"
						+ "<input type=\"button\" value=\"delete\" onclick=\"deleteProject()\"></form>");
		resp.getWriter().println(
				"<p>GETALL</p><form action=\"/cmpe281qili352/rest/employee\" method=\"GET\">Employees:"
				+ "<input type=\"radio\" name=\"content-type\" id=\"xml_allEMP\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" id=\"json_allEMP\" value=\"JSON\">JSON<br>"
//						+ "<input type=\"button\" value=\"getallemployee\" onclick=\"getAllEmployee()\"></form>"
//						+ "<div id=\"div_emp\"></div>");
						+ "<input type=\"submit\" value=\"Submit\"></form>");
		resp.getWriter().println(
				"<p>GETALL</p><form action=\"/cmpe281qili352/rest/project\" method=\"GET\">Projects:"
				+ "<input type=\"radio\" name=\"content-type\" id=\"xml_allPRO\" value=\"XML\">XML"
						+ "<input type=\"radio\" name=\"content-type\" id=\"json_allPRO\" value=\"JSON\">JSON<br>"
//						+ "<input type=\"button\" value=\"getallemployee\" onclick=\"getAllEmployee()\"></form>"
//						+ "<div id=\"div_emp\"></div>");
						+ "<input type=\"submit\" value=\"Submit\"></form>");
	}

}
