/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naeemiqbal.passport;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author naeem
 */
@WebServlet(name = "DataService", urlPatterns = {"/DataService"}/*, initParams = {
    @WebInitParam(name = "oper", value = ""),
    @WebInitParam(name = "nodeCount", value = "5")}*/)
public class DataService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        /* Input parameters
        oper: C, U, D. create, update & delete
        name: Name of Factory
        nodecount: number of nodes
        start : range start of children nodes
        end : range end of children nodes
         */

        TreeApp ta = TreeApp.getInstance();
        String oper = request.getParameter("oper");
        Map m = request.getParameterMap();

        try {
            if (OperationEnum.INSERT.isEqual(oper)) {
                Integer start = Integer.parseInt(request.getParameter("start"));
                Integer end = Integer.parseInt(request.getParameter("end"));
                Integer count = Integer.parseInt(request.getParameter("nodecount"));
                ta.addFactory(request.getParameter("name"), start, end, count);
                response.sendRedirect("index.html");
                return;
            } else if (OperationEnum.DELETE.isEqual(oper)) {
                Integer id = Integer.parseInt(request.getParameter("nid"));
                ta.deleteFactory(id);
            } else if (OperationEnum.UPDATE.isEqual(oper)) {
                Integer count = Integer.parseInt(request.getParameter("nodecount"));
                Integer id = Integer.parseInt(request.getParameter("nid"));
                ta.updateFactory(id, request.getParameter("name"), null, null, count);
            }
        } catch (Exception ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }

        Gson gson = new Gson();
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(ta.root));

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    Integer parseIntParam(HttpServletRequest request, String param) {
        if (param != null && param.length() > 0) {
            return Integer.parseInt(param);
        } else {
            return null;
        }
    }

}
