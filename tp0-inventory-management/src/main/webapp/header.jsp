<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion d'Inventaire</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/">Gestion d'Inventaire</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown">Utilisateurs</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/users">Liste des Utilisateurs</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/user-form">Ajouter Utilisateur</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown">Produits</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/products">Liste des Produits</a>
                            <a class="dropdown-item" href="<%= request.getContextPath() %>/product-form">Ajouter Produit</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-4">
