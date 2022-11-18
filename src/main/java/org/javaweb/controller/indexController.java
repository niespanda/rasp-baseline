package org.javaweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

@RestController
public class indexController {

	@RequestMapping(value = "/")
	public void indexFunction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println("ContextPath:" + request.getContextPath());
		writer.println("RequestURL:" + request.getRequestURL());
		writer.println("RequestURI:" + request.getRequestURI());
		writer.println("ServletPath:" + request.getServletPath());
		writer.println("PathInfo:" + request.getPathInfo());
		writer.println("PathTranslated:" + request.getPathTranslated());

		writer.println("id: " + request.getParameter("id"));
		writer.println("ClassLoader: " + this.getClass().getClassLoader());
		writer.println("\n\n----------------------ENV----------------------\n\n");
		Map<String, String> map = System.getenv();

		for (String k : map.keySet()) {
			writer.println(k + ": " + map.get(k));
		}

		writer.println("\n\n----------------------Properties----------------------\n\n");
		Properties     properties  = System.getProperties();
		Enumeration<?> enumeration = properties.propertyNames();

		while (enumeration.hasMoreElements()) {
			Object k = enumeration.nextElement();
			writer.println(k + ": " + properties.get(k));
		}
		writer.flush();
		writer.close();
	}
}
