package com.Accio;
/*
 * @Amresh Tripathy
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String keyWord = request.getParameter("keyword");
        System.out.println(keyWord);
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into `history` values(?,?)");
            preparedStatement.setString(1, keyWord);
            preparedStatement.setString(2, "http://localhost:8080/SimpleSearchEngineJava/Search?keyword=" + keyWord);
            preparedStatement.executeUpdate();

            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT pageTitle,pageLink,(LENGTH(LOWER(pageText))-LENGTH(REPLACE(LOWER(pageText),'"
                            + keyWord + "','')))/LENGTH('" + keyWord
                            + "') AS count_occurence FROM pages ORDER BY count_occurence DESC LIMIT 30;");
            
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            while(resultSet.next()) {
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }

            for (SearchResult searchResult : results) {
                System.out.println(searchResult.getTitle() + " " + searchResult.getLink() + "\n");
            }

            request.setAttribute("results", results);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

        } catch (SQLException | ServletException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
