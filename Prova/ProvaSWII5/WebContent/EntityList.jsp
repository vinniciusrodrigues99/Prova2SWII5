<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listagem de Vendedores, Clientes e Pedidos</title>
</head>
<body>
    <h2>Lista de Vendedores</h2>
    <a href="newSalesman"><b>Adicionar Novo Vendedor</b></a>
    <table border="1" width="50%">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cidade</th>
            <th>Comissão</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="salesman" items="${listSalesman}">
            <tr>
                <td>${salesman.id}</td>
                <td>${salesman.name}</td>
                <td>${salesman.city}</td>
                <td>${salesman.commission}</td>
                <td>
                    <a href="editSalesman?id=${salesman.id}">Editar</a>
                    <a href="deleteSalesman?id=${salesman.id}" onclick="return confirm('Tem certeza que deseja deletar este vendedor?')">Deletar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Listagem de Clientes: </h2>
    <a href="newCustomer"><b>Adicionar Novo Cliente</b></a>
    <table border="1" width="50%">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cidade</th>
            <th>Grau</th>
            <th>Vendedor</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="customer" items="${listCustomer}">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.custName}</td>
                <td>${customer.city}</td>
                <td>${customer.grade}</td>
                <td>${customer.salesmanId}</td>
                <td>
                    <a href="editCustomer?id=${customer.customerId}">Editar</a>
                    <a href="deleteCustomer?id=${customer.customerId}" onclick="return confirm('Tem certeza que deseja deletar este cliente?')">Deletar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Listagem de Pedidos: </h2>
    <a href="newOrder"><b>Adicionar Novo Pedido</b></a>
    <table border="1" width="50%">
        <tr>
            <th>Número</th>
            <th>Valor da Compra</th>
            <th>Data</th>
            <th>Cliente</th>
            <th>Vendedor</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="order" items="${listOrder}">
            <tr>
                <td>${order.orderNo}</td>
                <td>${order.purchAmt}</td>
                <td>${order.ordDate}</td>
                <td>${order.customerId}</td>
                <td>${order.salesmanId}</td>
                <td>
                    <a href="editOrder?id=${order.orderNo}">Editar</a>
                    <a href="deleteOrder?id=${order.orderNo}" onclick="return confirm('Tem certeza que deseja deletar este pedido?')">Deletar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
