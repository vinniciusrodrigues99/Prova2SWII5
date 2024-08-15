package net.codejava.javaee.provaswii5;

//<!--     Vinnicius Oliveira Rodrigues -->


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SalesmanDAO salesmanDAO;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;

    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/prova?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        salesmanDAO = new SalesmanDAO(jdbcURL, jdbcUsername, jdbcPassword);
        customerDAO = new CustomerDAO(jdbcURL, jdbcUsername, jdbcPassword);
        orderDAO = new OrderDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newSalesman":
                    showNewSalesmanForm(request, response);
                    break;
                case "/insertSalesman":
                    insertSalesman(request, response);
                    break;
                case "/deleteSalesman":
                    deleteSalesman(request, response);
                    break;
                case "/editSalesman":
                    showEditSalesmanForm(request, response);
                    break;
                case "/updateSalesman":
                    updateSalesman(request, response);
                    break;

                case "/newCustomer":
                    showNewCustomerForm(request, response);
                    break;
                case "/insertCustomer":
                    insertCustomer(request, response);
                    break;
                case "/deleteCustomer":
                    deleteCustomer(request, response);
                    break;
                case "/editCustomer":
                    showEditCustomerForm(request, response);
                    break;
                case "/updateCustomer":
                    updateCustomer(request, response);
                    break;

                case "/newOrder":
                    showNewOrderForm(request, response);
                    break;
                case "/insertOrder":
                    insertOrder(request, response);
                    break;
                case "/deleteOrder":
                    deleteOrder(request, response);
                    break;
                case "/editOrder":
                    showEditOrderForm(request, response);
                    break;
                case "/updateOrder":
                    updateOrder(request, response);
                    break;

                default:
                    listEntities(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEntities(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Salesman> listSalesman = salesmanDAO.listAllSalesman();
        List<Customer> listCustomer = customerDAO.listAllCustomers();
        List<Order> listOrder = orderDAO.listAllOrders();
        request.setAttribute("listSalesman", listSalesman);
        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EntityList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewSalesmanForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SalesmanForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditSalesmanForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Salesman existingSalesman = salesmanDAO.getSalesman(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SalesmanForm.jsp");
        request.setAttribute("salesman", existingSalesman);
        dispatcher.forward(request, response);
    }

    private void insertSalesman(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        double commission = Double.parseDouble(request.getParameter("commission"));

        Salesman newSalesman = new Salesman(name, city, commission);
        salesmanDAO.insertSalesman(newSalesman);
        response.sendRedirect("list");
    }

    private void updateSalesman(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        double commission = Double.parseDouble(request.getParameter("commission"));

        Salesman salesman = new Salesman(id, name, city, commission);
        salesmanDAO.updateSalesman(salesman);
        response.sendRedirect("list");
    }

    private void deleteSalesman(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Salesman salesman = new Salesman(id);
        salesmanDAO.deleteSalesman(salesman);
        response.sendRedirect("list");
    }



    private void showNewCustomerForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditCustomerForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.getCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String custName = request.getParameter("custName");
        String city = request.getParameter("city");
        int grade = Integer.parseInt(request.getParameter("grade"));
        int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));

        Customer newCustomer = new Customer(custName, city, grade, salesmanId);
        customerDAO.insertCustomer(newCustomer);
        response.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String custName = request.getParameter("custName");
        String city = request.getParameter("city");
        int grade = Integer.parseInt(request.getParameter("grade"));
        int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));

        Customer customer = new Customer(id, custName, city, grade, salesmanId);
        customerDAO.updateCustomer(customer);
        response.sendRedirect("list");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = new Customer(id);
        customerDAO.deleteCustomer(customer);
        response.sendRedirect("list");
    }


    private void showNewOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrderForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order existingOrder = orderDAO.getOrder(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrderForm.jsp");
        request.setAttribute("order", existingOrder);
        dispatcher.forward(request, response);
    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        double purchAmt = Double.parseDouble(request.getParameter("purchAmt"));
        Date ordDate = Date.valueOf(request.getParameter("ordDate"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));

        Order newOrder = new Order(purchAmt, ordDate, customerId, salesmanId);
        orderDAO.insertOrder(newOrder);
        response.sendRedirect("list");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        double purchAmt = Double.parseDouble(request.getParameter("purchAmt"));
        Date ordDate = Date.valueOf(request.getParameter("ordDate"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));

        Order order = new Order(id, purchAmt, ordDate, customerId, salesmanId);
        orderDAO.updateOrder(order);
        response.sendRedirect("list");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Order order = new Order(id);
        orderDAO.deleteOrder(order);
        response.sendRedirect("list");
    }
}

