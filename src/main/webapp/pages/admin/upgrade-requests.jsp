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
        <a href="#"><div class="action">Transactions</div></a>
        <a href="#"><div class="action">Activated Packages</div></a>
        <a href="/oop-epay-crud/admin/request/upgrade-requests"
          ><div class="action active">User Requests</div></a
        >
        <a href="#"><div class="action">Unsubscribed Packages</div></a>
      </div>

      <div class="dashboard__body">
        <div class="dashboard__req-btn-wrapper">
          <button type="button" class="btn btn-outline-secondary">
            Upgrade Requests
          </button>
          <button
            type="button"
            class="btn btn-outline-secondary"
            onclick="window.location.href='/oop-epay-crud/admin/request/deactivation-requests';"
          >
            Deactivation Requests
          </button>
        </div>
        <div class="table-wrapper table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Packge Id</th>
                <th scope="col">Package Name</th>
                <th scope="col">Current Duration</th>
                <th scope="col">Activated Date</th>
                <th scope="col">Expire Date</th>
                <th scope="col">User Id</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="request" items="${upgradeRequests}">
                <tr>
                  <th scope="row">${request.packageId}</th>
                  <td>${request.packageName}</td>
                  <td>${request.packageDuration}</td>
                  <td>${request.activatedDate}</td>
                  <td>${request.expireDate}</td>
                  <td>${request.userId}</td>
                  <td>
                    <button
                      type="button"
                      class="btn btn-warning"
                      onclick="window.location.href='upgrade?packageId=${request.packageId}&packageName=${request.packageName}&userId=${request.userId}';"
                    >
                      Upgrade
                    </button>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
