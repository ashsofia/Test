package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class will fetch the file for download.
 * 
 * @author
 * @version 1.0.0
 * @since 10-JUL-2018
 */
public class download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter()
		String filename = "";
		String type = (String) request.getParameter("type");
		if (type.equals("search")) {
			filename = "Search_Report.xlsx";
		} else if (type.equals("dateRange")) {
			filename = "Date_Range_Report.xlsx";
		} else if (type.equals("timeRange")) {
			filename = "Time_Range_Report.xlsx";
		}
		ResourceBundle rb = ResourceBundle.getBundle("properties.config");
		String filepath = rb.getString("downloadablesFilePath") + request.getSession().getId() + File.separator;
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		FileInputStream fileInputStream = new FileInputStream(filepath + filename);
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}


