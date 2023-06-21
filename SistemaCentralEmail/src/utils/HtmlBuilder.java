/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.List;

/**
 *
 * @author HP
 */
public class HtmlBuilder {

    private static final String HTML_OPEN = "<html>";
    private static final String HTML_CLOSE = "</html>";
    private static final String BODY_OPEN = "<body>";
    private static final String BODY_CLOSE = "</body>";

    public static String generateTable(String title, String[] headers, List<String[]> data) {
        String table_headers_html = "";
        for (String header : headers) {
            table_headers_html += "<th style= \"border: 1px solid back;\">" + header + "</th>";
        }
        String table_body_html = "";
        for (String[] element : data) {
            table_body_html += "<tr style= \"border: 1px solid back;\">";
            for (String value : element) {
            table_body_html += "<td style= \"border: 1px solid back;\">";
            }
            table_body_html+="</tr>";
        }
        
        String html
                ="<center><h2>"+ title+"</h2></center>"
                +"<table style=\"border: 1px solid black;\" bgcolor=\"#CCCCCC\">"
                +"<thead>"
                + table_headers_html
                +"</thead>"
                +"<tbody>"
                +table_body_html
                + "</tbody>"
                +"</table>";
        return insertInHtml(html);
    }

    public static String generateText(String[] args) {
        String acumulative = "<center><h2>"+ args[0]+ "</h2></center>";
        for (int i = 1; i < args.length; i++) {
            acumulative += "<center><h3>"+ args[i]+"</h3></center>";
        }
        return insertInHtml(acumulative);
    }

    public static String generateTableForSimpleData(String title, String[] headers, String[] data) {
        String acumulative = "";
        for (int i=0 ; i <headers.length ; i++) {
            acumulative+=
                    "<tr>"+
                        "<td>" + headers[i] + "</td>" +
                        "<td>" + data[i] + "</td>" +
                    "</tr>";
        }
        String table = 
                "<div align=\"center\">\n"+
                    "<h2>"+ title +"<>br\n"+
                    "<h2>\n"+
                "</div>\n"+
                "<table>\n"+
                "</table>\n";
        return table;
     }

    public static String insertInHtml(String data) {
        return HTML_OPEN+BODY_OPEN+data+BODY_CLOSE+HTML_CLOSE;
    }

}
