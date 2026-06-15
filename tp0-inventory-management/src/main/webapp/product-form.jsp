<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<c:choose>
    <c:when test="${product != null}">
        <h2>Modifier le Produit</h2>
        <form action="<%= request.getContextPath() %>/update-product" method="post">
        <input type="hidden" name="id" value="<c:out value='${product.id}'/>"/>
    </c:when>
    <c:otherwise>
        <h2>Ajouter un Produit</h2>
        <form action="<%= request.getContextPath() %>/create-product" method="post">
    </c:otherwise>
</c:choose>
<div class="form-group">
    <label>Nom du produit</label>
    <input type="text" class="form-control" name="name" value="<c:out value='${product.name}'/>" required>
</div>
<div class="form-group">
    <label>Description</label>
    <textarea class="form-control" name="description" rows="3"><c:out value='${product.description}'/></textarea>
</div>
<div class="form-group">
    <label>Prix</label>
    <input type="number" step="0.01" class="form-control" name="price" value="<c:out value='${product.price}'/>" required>
</div>
<div class="form-group">
    <label>Quantité en stock</label>
    <input type="number" class="form-control" name="stockQuantity" value="<c:out value='${product.stockQuantity}'/>" required>
</div>
<div class="form-group">
    <label>SKU</label>
    <input type="text" class="form-control" name="sku" value="<c:out value='${product.sku}'/>" required>
</div>
<button type="submit" class="btn btn-primary">Enregistrer</button>
<a href="<%= request.getContextPath() %>/products" class="btn btn-secondary">Annuler</a>
</form>
<%@ include file="footer.jsp" %>
