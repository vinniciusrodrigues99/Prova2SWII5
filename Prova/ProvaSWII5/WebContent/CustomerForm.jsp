<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário de Cliente</title>
</head>
<body>
    <h2>${customer != null ? 'Editar Cliente' : 'Adicionar Novo Cliente'}</h2>
    <form action="${customer != null ? 'updateCustomer' : 'insertCustomer'}" method="post">
        <input type="hidden" name="id" value="${customer != null ? customer.customerId : ''}">
        <label>Nome:</label>
        <input type="text" name="custName" value="${customer != null ? customer.custName : ''}" required><br>
        <label>Cidade:</label>
        <input type="text" name="city" value="${customer != null ? customer.city : ''}" required><br>
        <label>Grau:</label>
        <input type="text" name="grade" value="${customer != null ? customer.grade : ''}" required><br>
        <label>ID do Vendedor:</label>
        <input type="text" name="salesmanId" value="${customer != null ? customer.salesmanId : ''}" required><br>
        <input type="submit" value="Salvar">
        <a href="list">Cancelar</a>
    </form>
</body>
</html>
