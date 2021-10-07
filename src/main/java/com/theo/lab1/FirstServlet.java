package com.theo.lab1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "firstLaboratory", value = "/first-laboratory")
public class FirstServlet extends HttpServlet {
    private FileWriter fileWriter;

    public void init() {
        try {
            fileWriter = new FileWriter("repository.txt");
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String key = request.getParameter("key");
        String valueReq = request.getParameter("value");
        String mockReq = request.getParameter("mock");
        String syncReq = request.getParameter("sync");
        int value = Integer.parseInt(valueReq);
        boolean mock = Boolean.parseBoolean(mockReq);
        boolean sync = Boolean.parseBoolean(syncReq);

        String httpMethod = request.getMethod();
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String userLanguage = request.getHeader("Accept-Language");
        log("the HTTP method used: " + httpMethod + " the IP-address of the client: " + ipAddress + " the user-agent: " + userAgent + " the client language(s): " + userLanguage + " the parameters of the request: key: " + key + ", value: " + value + ", mock: " + mock + ", sync: " + sync);

        boolean htmlText = false;
        if (userAgent.contains("Chrome") || userAgent.contains("Firefox"))
            htmlText = true;

        if (mock==true){
            if (htmlText) {
                out.println("<html>");
                out.println("<body>");
                mockWritingBrowserRequest(out, key, value, mock, sync);
                out.println("</body>");
                out.println("</html>");
            }
            else{
                mockWritingApplicationRequest(out, key, value, mock, sync);
            }
        }
        else{
            if (sync) {
                synchronizedWriting(key, value);
            }
            else
            {
                basicWriting(key, value);
            }

            if (htmlText)
                out.println("<html><body><pre>");
            for (Map.Entry<String, String> entry : getOrderedContent().entrySet()) {
                out.println(entry.getKey() + " " + entry.getValue());
            }
            if (htmlText)
                out.println("</pre></body></html>");
        }
    }

    public void destroy() {
        try {
            fileWriter.close();
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    public synchronized boolean synchronizedWriting(String key, int value){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder textToBeWritten = new StringBuilder();
        while (value!=0){
            textToBeWritten.append(key);
            value--;
        }
        textToBeWritten.append(" ");
        textToBeWritten.append(formatter.format(date));
        textToBeWritten.append("\n");
        try {
            fileWriter.write(textToBeWritten.toString());
            fileWriter.flush();
            return true;
        }catch(Throwable e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean basicWriting(String key, int value){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder textToBeWritten = new StringBuilder();
        while (value!=0){
            textToBeWritten.append(key);
            value--;
        }
        textToBeWritten.append(" ");
        textToBeWritten.append(formatter.format(date));
        textToBeWritten.append("\n");
        try {
            fileWriter.write(textToBeWritten.toString());
            fileWriter.flush();
            return true;
        }catch(Throwable e){
            e.printStackTrace();
            return false;
        }
    }

    public void mockWritingBrowserRequest(PrintWriter out, String key, int value, boolean mock, boolean sync){
        out.println("Message confirmed!<br>");
        out.println("The parameters are:<br>");
        out.println("Key: " + key + "<br>");
        out.println("Value: " + value + "<br>");
        out.println("Mock: " + mock + "<br>");
        out.println("Sync: " + sync + "<br>");
    }

    public void mockWritingApplicationRequest(PrintWriter out, String key, int value, boolean mock, boolean sync){
        out.println("Message confirmed!");
        out.println("The parameters are:");
        out.println("Key: " + key);
        out.println("Value: " + value);
        out.println("Mock: " + mock);
        out.println("Sync: " + sync);
    }

    public TreeMap<String, String> getOrderedContent(){
        TreeMap<String, String> stringsOrderedByKey = new TreeMap<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get("repository.txt")));
            String[] partsOfContent = content.split("[ \n]");
            for (int i=0; i<partsOfContent.length; i++){
                if (i%3==0){
                    stringsOrderedByKey.put(partsOfContent[i], partsOfContent[i+1] + " " + partsOfContent[i+2]);
                }
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
        return stringsOrderedByKey;
    }
}