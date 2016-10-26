package viper.comms.dao.cache;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;

public class StatisticsServlet
extends HttpServlet
{
    private Statistics[] statistics;

    public StatisticsServlet(Statistics[] statistics)  {
        this.statistics = statistics;
    }

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        // Write the title and table header.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<title>System statistics</title>");
        out.println("<body>");
        out.println("<h1>System Statistics</h1>");
        out.println("<table border>");
        out.println("<tr><th>Description</th>");
        out.println("<th>Hits</th>");
        out.println("<th>Accesses</th>");
        out.println("<th>Hit Ratio</th>");
        out.println("<th>Size</th></tr>");

        // Write a row for each statistics object.
        for(int i = 0; i < statistics.length; ++i) {
            long hits = statistics[i].getHits();
            long accesses = statistics[i].getAccesses();
            out.println("<tr><td>");
            out.println(statistics[i].getDescription());
            out.println("</td><td>");
            out.println(hits);
            out.println("</td><td>");
            out.println(accesses);
            out.println("</td><td>");
            out.println(NumberFormat.getPercentInstance()
                .format((double)hits/(double)accesses));
            out.println("</td><td>");
            out.println(statistics[i].getSize());
            out.println("</td></tr>");
        }

        // Write the table and page footer.
        out.println("</table></body>");
        out.close();
    }
}

