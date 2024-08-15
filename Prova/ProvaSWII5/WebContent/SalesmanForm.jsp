<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário de Vendedor</title>
</head>
<body>
    <h2>${salesman != null ? 'Editar Vendedor' : 'Adicionar Novo Vendedor'}</h2>
    <form action="${salesman != null ? 'updateSalesman' : 'insertSalesman'}" method="post">
        <input type="hidden" name="id" value="${salesman != null ? salesman.id : ''}">
        <label>Nome:</label>
        <input type="text" name="name" value="${salesman != null ? salesman.name : ''}" required><br>
        <label>Cidade:</label>
        <input type="text" name="city" value="${salesman != null ? salesman.city : ''}" required><br>
        <label>Comissão:</label>
        <input type="text" name="commission" value="${salesman != null ? salesman.commission : ''}" required><br>
        <input type="submit" value="Salvar">
        <a href="list">Cancelar</a>
    </form>
</body>
</html>
