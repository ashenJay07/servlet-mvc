<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="ISO-8859-1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <title>ePay | Admin</title>
    <!-- <link rel="stylesheet" href="/styles/admin.css" /> -->
    <link rel="stylesheet" href="/oop-epay-crud/css/admin.css" />

    <style>
      .active {
        background-color: lightblue;
      }
    </style>
  </head>
  <body>
    <h1 class="dashboard-title">Admin Dashboard</h1>
    <div class="dashboard-wrapper">
      <div class="dashboard__actions">
        <a href="/oop-epay-crud/admin/transactions"
          ><div class="action">Transactions</div></a
        >
        <div class="action active">Activated Packages</div>
        <div class="action">Unsubscribed Packages</div>
      </div>

      <div class="dashboard__body">
        <div class="table-wrapper table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Package Id</th>
                <th scope="col">Pkg Duration</th>
                <th scope="col">Activated Date</th>
                <th scope="col">Expire Date</th>
                <th scope="col">User Id</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="dataPackage" items="${activePackages}">
                <tr>
                  <th scope="row">${dataPackage.id}</th>
                  <td>${dataPackage.packageId}</td>
                  <td>${dataPackage.packageDuration} days</td>
                  <td>2024-10-12</td>
                  <td>2024-10-19</td>
                  <td>${dataPackage.userId}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
