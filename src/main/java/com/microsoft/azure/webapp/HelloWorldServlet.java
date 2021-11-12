package com.microsoft.azure.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.RemoteDependencyTelemetry;
import com.microsoft.applicationinsights.telemetry.Duration;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final TelemetryClient telemetryClient = new TelemetryClient();
	static int queueLength = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter printWriter  = response.getWriter();
		String info = "<h1>Hello Azure DevOps. Today is " + "01/01" + "</h1>";

		// Below for application insight Telemetry tracking demo

		// Track for event
		telemetryClient.trackEvent("Page Visit");
		// Track for custom Metrics
		telemetryClient.trackMetric("queueLength", queueLength++);
		// Track for dependencies
		boolean success = false;
		long startTime = System.currentTimeMillis();
		try {
			// Simulate call the dependency "Backend-A"
			// Backend-A.call();

			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//Set datetime format
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Set datetime format
			String datetime = df.format(new Date());// new Date() get system current time

			info = "<h1>Hello Azure DevOps. Today is " + datetime + "</h1>";
		} finally {
			String dependency = "Backend-A";
			//long endTime = System.currentTimeMillis();

			// Simulate fault call to dependency
			RemoteDependencyTelemetry telemetry = new RemoteDependencyTelemetry();
			telemetry.setSuccess(success);
			telemetry.setResultCode("404");
			telemetry.setName(dependency);
			telemetry.setTimestamp(new Date(startTime));
			telemetry.setDuration(new Duration(1000));
			telemetryClient.trackDependency(telemetry);
			// throw exception
			telemetryClient.trackException(new Exception("calling to " + dependency + " is causing exception!"));
		}

		printWriter.println(info);

		// Track for log
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("method", "doGet");
		telemetryClient.trackTrace("Logs collected by appInsight", SeverityLevel.Warning, properties);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
