<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-6">
		<c:set var="imageFilename"
			value="/resources/images/${product.name}.jpg" />
		<img src="<c:url value="${imageFilename}" />" alt="image"
			style="width: 80%" />
	</div>


	<div class="col-md-6">
		<br />
		<h3>${product.name}</h3>
		<p>${product.description}</p>
		<p>Manufacturer :${product.manufacturer}</p>
		<p>Category :${product.category}</p>
		<p>Price : ${product.price}</p>

	</div>
</div>