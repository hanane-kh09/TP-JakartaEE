<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<c:choose>
    <c:when test="${user != null}">
        <h2>Modifier l'Utilisateur</h2>
        <form action="<%= request.getContextPath() %>/update-user" method="post">
        <input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
    </c:when>
    <c:otherwise>
        <h2>Ajouter un Utilisateur</h2>
        <form action="<%= request.getContextPath() %>/create-user" method="post">
    </c:otherwise>
</c:choose>
<div class="form-group">
    <label>Prénom</label>
    <input type="text" class="form-control" name="firstName" value="<c:out value='${user.firstName}'/>" required>
</div>
<div class="form-group">
    <label>Nom</label>
    <input type="text" class="form-control" name="lastName" value="<c:out value='${user.lastName}'/>" required>
</div>
<div class="form-group">
    <label>Email</label>
    <input type="email" class="form-control" name="email" value="<c:out value='${user.email}'/>" required>
</div>
<div class="form-group">
    <label>Mot de passe</label>
    <input type="password" class="form-control" name="password" <c:if test="${user == null}">required</c:if>>
    <c:if test="${user != null}"><small class="text-muted">Laissez vide pour conserver le mot de passe actuel.</small></c:if>
</div>
<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/users" class="btn btn-secondary">Annuler</a>
</form>
<%@ include file="footer.jsp" %>
