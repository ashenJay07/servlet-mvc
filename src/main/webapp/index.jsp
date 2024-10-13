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
    <!-- <link rel="stylesheet" href="styles/common.css" /> -->
    <!-- <link rel="stylesheet" href="styles/index.css" /> -->
    <link rel="stylesheet" href="/oop-epay-crud/css/index.css" />

    <title>ePay | packages</title>
  </head>
  <body>
    <div class="package-wrapper">
      <div class="package__container">
        <c:forEach var="cusPackage" items="${pkgInstances}" varStatus="status">
          <form class="package" action="#" method="post">
            <h2 class="package__title">${cusPackage.packageName}</h2>

            <ul>
              <c:forEach var="detail" items="${cusPackage.packageDetails}">
                <li>${detail}</li>
              </c:forEach>
            </ul>

            <div
              class="btn-group"
              role="group"
              aria-label="Basic radio toggle button group"
            >
              <input
                type="radio"
                class="btn-check"
                name="duration"
                value="7"
                id="btnradio1-${status.index}"
                autocomplete="off"
                checked
              />
              <label
                class="btn btn-outline-primary"
                for="btnradio1-${status.index}"
              >
                <span>7 days</span>
                <span class="price"
                  >Rs.
                  <fmt:formatNumber
                    value="${cusPackage.weeklyPackagePrice}"
                    pattern="0.00"
                /></span>
              </label>

              <input type="radio" class="btn-check" name="duration" value="30"
              id="btnradio2-${status.index}" autocomplete="off"
              ${cusPackage.currentlyActiveDuration == 30 ? 'checked' : ''} />
              <label
                class="btn btn-outline-primary"
                for="btnradio2-${status.index}"
              >
                <span>30 days</span>
                <span class="price"
                  >Rs.
                  <fmt:formatNumber
                    value="${cusPackage.monthlyPackagePrice}"
                    pattern="0.00"
                /></span>
              </label>
            </div>

            <input
              type="hidden"
              name="package-name"
              value="${cusPackage.packageName}"
            />
            <input
              type="hidden"
              name="weekly-package-price"
              value="${cusPackage.weeklyPackagePrice}"
            />
            <input
              type="hidden"
              name="monthly-package-price"
              value="${cusPackage.monthlyPackagePrice}"
            />

            <button
              type="submit"
              class="btn btn-success btn-activate ${cusPackage.currentlyActiveDuration > 6 ? 'hide' : ''}"
              formaction="checkout"
            >
              Activate
            </button>

            <div
              class="btn-container ${cusPackage.currentlyActiveDuration < 7 ? 'hide' : ''}"
            >
              <button
                type="submit"
                class="btn btn-danger"
                formaction="deactivate-package"
              >
                Deactivate
              </button>
              <button
                type="submit"
                class="btn btn-warning"
                formaction="checkout?upgrade=true"
                ${cusPackage.currentlyActiveDuration == 30 ? 'disabled' : ''}
              >
                Upgrade
              </button>
            </div>
          </form>
        </c:forEach>
      </div>
    </div>
    
 	<div class="alert-box-wrapper hide">
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Holy guacamole!</strong> You should check in on some of those
        fields below.
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="alert"
          aria-label="Close"
        ></button>
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
    
  </body>
</html>