URL: http://centaurus-6.ics.uci.edu:9450/pa3/

Name of student: Tyler Vu
student id: 82249442

There are 3 main pages
- home page (pa3)
- detail page (ex: pa3/html/details.jsp?rock_id=1)
- cart page (pa3/html/cart.jsp)

The home page contains all of the rocks on the site as well as the users 5 last viewed rocks.
The detail page contains details about the selected rock as well as an add to cart button.
The cart page contains all of the rocks that the user added to the cart as well as an order form.

Requirement fulfillment locations:

1. The home page uses two servlets. One to grab the history of the last viewed items in the session and the other to grab all of the rocks from the database. These two servlets are GrabHistory.java and GrabRocks.java respectively. GrabRocks.java "includes" GrabHistory.java.

2. You can get to the details page by clicking on a rock picture on the home screen. The details.jsp page takes a product identifier as a parameter and uses the servlet GrabDetails.java to grab details about the rock. The "Add to cart" button calls the AddToCart.java servlet. These two servlets modify the session to track history of rocks viewed and cart items.

3. You can get to the checkout page by clicking on "cart" on the navbar. This page uses GrabCart.java servlet to grab all of the rocks currently in the session's cart. The page displays and calculates the total price of all of rocks in the cart. The page has a form where the user can input form data and post to the database. When the user enters the info for the form and passes the validity check, they can press the "place order" button. This will push all of the necessary data to the database and redirect the user to the order details page using the forward feature of java servlets.

note: I did not use jsp for this assignment even though my files have (.jsp). I only used java servlets.