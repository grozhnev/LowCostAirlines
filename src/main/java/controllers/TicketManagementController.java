package controllers;

import dao.AirportDAO;
import entities.Airport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

@WebServlet("/ticketmanagement")
public class TicketManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Set<Airport> airports = new AirportDAO().getAll();
            request.setAttribute("airports", airports);
            request.getRequestDispatcher("/ticketmanagement.jsp").forward(request, response);
        } catch (SQLException e) {}
    }
}
