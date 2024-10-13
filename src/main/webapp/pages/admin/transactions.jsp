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
          ><div class="action active">Transactions</div></a
        >
        <a href="/oop-epay-crud/admin/activated-packages"
          ><div class="action">Activated Packages</div></a
        >
        <a href="/oop-epay-crud/admin/request/upgrade-requests"
          ><div class="action">User Requests</div></a
        >
      </div>

      <div class="dashboard__body">
        <div class="table-wrapper table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Trans Id</th>
                <th scope="col">Trans Amount</th>
                <th scope="col">Card Holder</th>
                <th scope="col">Email</th>
                <th scope="col">Trans Date</th>
                <th scope="col">User Id</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="transaction" items="${transactions}">
                <tr>
                  <th scope="row">${transaction.id}</th>
                  <td>
                    Rs.
                    <fmt:formatNumber
                      value="${transaction.transAmount}"
                      pattern="0.00"
                    />
                  </td>
                  <td>${transaction.cardHolder}</td>
                  <td>${transaction.email}</td>
                  <td>${transaction.transactionDate}</td>
                  <td>${transaction.userId}</td>
                </tr>
              </c:forEach>
            </tbody>

            <!-- <tbody>
              <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>aliexpress@amazon.com</td>
                <td>
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Nihil, dolorem.
                </td>
                <td>
                  Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                  Asperiores, officia.
                </td>
              </tr>
            </tbody> -->
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
