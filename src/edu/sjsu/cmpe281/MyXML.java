package edu.sjsu.cmpe281;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.google.appengine.api.datastore.Entity;

public class MyXML {
	public MyXML()
	{}
	public String emploeeXML(String id, String fName, String lName) throws IOException
	{
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("employee");
		Element emp_id = root.addElement("id");
		emp_id.setText(id);
		Element emp_f = root.addElement("firstName");
		emp_f.setText(fName);
		Element emp_l = root.addElement("lastName");
		emp_l.setText(lName);
	    return prettyPrint(doc);
	}
	
	public String employeeAllXML() throws IOException
	{
		DataStore ds = new DataStore();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("employeeList");
		
		Iterable<Entity> emps = ds.getEmployees();
		for (Entity emp : emps) {
			Element empxml = root.addElement("employee");
			Element emp_id = empxml.addElement("id");
			emp_id.setText(emp.getProperty("id").toString());
			Element emp_f = empxml.addElement("firstName");
			emp_f.setText(emp.getProperty("firstName").toString());
			Element emp_l = empxml.addElement("lastName");
			emp_l.setText(emp.getProperty("lastName").toString());
		}
		return prettyPrint(doc);
	}
	
	public String prettyPrint(Document doc) throws IOException
	{
		StringWriter sw = new StringWriter();
		OutputFormat opf = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(sw, opf);
		xmlWriter.write(doc);
		return sw.toString();
	}
	public String projectXML(String string, String property, String property2) throws IOException {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("project");
		Element pro_id = root.addElement("id");
		pro_id.setText(string);
		Element pro_f = root.addElement("name");
		pro_f.setText(property);
		Element emp_l = root.addElement("budget");
		emp_l.setText(property2);
		//
//		OutputFormat.createPrettyPrint();
		
		System.out.println(doc.asXML());
	    return prettyPrint(doc);
	}
	
	public String projectAllXML() throws IOException
	{
		DataStore ds = new DataStore();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("projectList");
		
		Iterable<Entity> pros = ds.getProjects();
		for (Entity pro : pros) {
			Element xml = root.addElement("project");
			Element pro_id = xml.addElement("id");
			pro_id.setText(pro.getProperty("id").toString());
			Element pro_name = xml.addElement("name");
			pro_name.setText(pro.getProperty("name").toString());
			Element pro_budget = xml.addElement("budget");
			pro_budget.setText(pro.getProperty("budget").toString());
		}
		return prettyPrint(doc);
	}
	
	public Entity parseEmployee(String xml) throws DocumentException, UnsupportedEncodingException
	{
		SAXReader sr = new SAXReader();
		System.out.println(xml);
		Document doc = sr.read(new InputSource(new StringReader(xml)));
		System.out.println(doc.asXML());
		Element root = doc.getRootElement();
		System.out.println(root.elementText("id"));
		Entity employee = new Entity("Employee");
		employee.setProperty("id", root.elementText("id"));
		employee.setProperty("firstName", root.elementText("firstName"));
		employee.setProperty("lastName", root.elementText("lastName"));
		return employee;
	}
	
	public Entity parseProject(String xml) throws DocumentException, UnsupportedEncodingException
	{
		SAXReader sr = new SAXReader();
		System.out.println(xml);
		Document doc = sr.read(new InputSource(new StringReader(xml)));
		System.out.println(doc.asXML());
		Element root = doc.getRootElement();
		System.out.println(root.elementText("id"));
		Entity project = new Entity("Project");
		project.setProperty("id", root.elementText("id"));
		project.setProperty("name", root.elementText("name"));
		project.setProperty("budget", root.elementText("budget"));
		return project;
	}
}
