<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>ePay | packages</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/oop-epay-crud/css/common.css" />
    <link rel="stylesheet" href="/oop-epay-crud/css/index.css" />
  </head>
  <body>
    <div class="package-wrapper">
      <c:forEach var="cusPackage" items="${pkgInstances}">
        <div class="package-container">
          <h2 class="pkg-title">${cusPackage.packageName}</h2>
          <ul class="pkg-details">
            <c:forEach var="detail" items="${cusPackage.packageDetails}">
              <li>${detail}</li>
            </c:forEach>
          </ul>
          <div class="pkg-footer">
            <span class="pkg-price"
              >Rs. <fmt:formatNumber
                value="${cusPackage.packagePrice}"
                pattern="0.00"
            /></span>
            <button type="button" class="btn btn-success btn-lg">
              Activate
            </button>
          </div>
        </div>
      </c:forEach>
    </div>
    
    <a href="payment">Go to payment</a>
  </body>
</html>

