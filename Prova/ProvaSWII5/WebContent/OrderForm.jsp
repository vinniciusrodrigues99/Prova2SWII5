<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário de Pedido</title>
</head>
<body>
    <h2>${order != null ? 'Editar Pedido' : 'Adicionar Novo Pedido'}</h2>
    <form action="${order != null ? 'updateOrder' : 'insertOrder'}" method="post">
        <input type="hidden" name="id" value="${order != null ? order.orderNo : ''}">
        <label>Valor da Compra:</label>
        <input type="text" name="purchAmt" value="${order != null ? order.purchAmt : ''}" required><br>
        <label>Data:</label>
        <input type="date" name="ordDate" value="${order != null ? order.ordDate : ''}" required><br>
        <label>ID do Cliente:</label>
        <input type="text" name="customerId" value="${order != null ? order.customerId : ''}" required><br>
        <label>ID do Vendedor:</label>
        <input type="text" name="salesmanId" value="${order != null ? order.salesmanId : ''}" required><br>
        <input type="submit" value="Salvar">
        <a href="list">Cancelar</a>
    </form>
</body>
</html>
