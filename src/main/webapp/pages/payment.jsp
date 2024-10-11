<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
   <link rel="stylesheet" href="/oop-epay-crud/css/payment.css" />
    <title>ePay | Payment</title>
  </head>
  <body>
    <div class="form-wrapper">
      <form
        action="/oop-epay-crud/submit-payment"
        method="post"
        class="payment-info-form"
      >
        <div class="mb-3">
          <label for="email" class="form-label">Email address</label>
          <input
            type="email"
            class="form-control"
            id="email"
            name="email"
            required
          />
        </div>

        <div class="mb-3">
          <label for="card-holder" class="form-label">Name on Card</label>
          <input
            type="text"
            class="form-control"
            id="card-holder"
            name="card-holder"
            required
          />
        </div>

        <div class="mb-3">
          <label for="card-number" class="form-label">Card Number</label>
          <input
            type="text"
            class="form-control"
            id="card-number"
            name="card-number"
            required
          />
        </div>

        <div class="card-expire mb-3">
          <div class="mb-3">
            <label for="exp-month" class="form-label">Exp Month</label>
            <input
              type="text"
              class="form-control"
              id="exp-month"
              name="exp-month"
              required
            />
          </div>

          <div class="mb-3">
            <label for="exp-year" class="form-label">Exp Year</label>
            <input
              type="text"
              class="form-control"
              id="exp-year"
              name="exp-year"
              required
            />
          </div>

          <div class="mb-3">
            <label for="cvv" class="form-label">CVV</label>
            <input
              type="text"
              class="form-control"
              id="cvv"
              name="cvv"
              required
            />
          </div>
        </div>

        <div class="form-text my-4">
          We'll never share your payment information with anyone else.
        </div>

        <button type="submit" class="btn btn-primary">
          Purchase & Activate
        </button>
      </form>
    </div>
  </body>
</html>
