package controllers;

import dao.FlightDAO;
import entities.Airport;
import entities.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/ticketmanagement")
public class TicketManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Set<Flight> flights = new FlightDAO().getAll();
            request.setAttribute("flights", flights);
            request.getRequestDispatcher("/ticketmanagement.jsp").forward(request, response);
        } catch (SQLException e) {}
    }
}
