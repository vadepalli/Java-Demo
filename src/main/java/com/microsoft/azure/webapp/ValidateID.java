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

//生成ValidateID servlet，访问路径为/ValidateID，实现方法doPost
@WebServlet("/ValidateID")
public class ValidateID extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static int queueLength = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateID() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //实现doPost方法，获取前端传入的身份证号码，调用身份证校验方法，返回校验结果
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter  = response.getWriter();
        String id = request.getParameter("id");
        String result = validateID(id);
        printWriter.println(result);
        printWriter.close();
    }

    //身份证校验方法，校验身份证号码是否合法
    public String validateID(String id) {
        String result = "身份证号码不合法";
        if (id.length() == 18) {
            result = "身份证号码合法";
        }
        return result;
    }
}

