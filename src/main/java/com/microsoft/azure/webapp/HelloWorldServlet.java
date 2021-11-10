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
	static int visitTimes = 0;
       
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
		// Track for Metrics
		telemetryClient.trackMetric("visitTimes", visitTimes++);
		// Track for dependencies
		boolean success = false;
		long startTime = System.currentTimeMillis();
		try {
			//printWriter.println("<h1>Hello World Servlet running on Azure App Service!</h1>");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Set datetime format
			String datetime = df.format(new Date());// new Date() get system current time
			//info = "<h1>Hello Azure DevOps. Today is " + datetime + "</h1>";
		} finally {
			long endTime = System.currentTimeMillis();
			RemoteDependencyTelemetry telemetry = new RemoteDependencyTelemetry();
			telemetry.setSuccess(success);
			telemetry.setName("Calculate duration");
			telemetry.setTimestamp(new Date(startTime));
			telemetry.setDuration(new Duration(endTime - startTime));
			telemetryClient.trackDependency(telemetry);
		}

		printWriter.println(info);

		// Track for log
		telemetryClient.trackTrace("Logs collected by appInsight", SeverityLevel.Warning, null);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
