# Hotel Reservation Application 
### followed the project requirements (see below) on Udacity
### User Scenarios

Creating a customer account. The user needs to first create a customer account before they can create a reservation.

Searching for rooms. The app should allow the user to search for available rooms based on provided checkin and checkout dates. If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.

Booking a room. Once the user has chosen a room, the app will allow them to book the room and create a reservation.

Viewing reservations. After booking a room, the app allows customers to view a list of all their reservations.

### Admin Scenarios
The application provides four administrative scenarios:

Displaying all customers accounts.
Viewing all of the rooms in the hotel.
Viewing all of the hotel reservations.
Adding a room to the hotel application.
Reserving a Room – Requirements

### Room Requirements
Room cost. Rooms will contain a price per night. When displaying rooms, paid rooms will display the price per night and free rooms will display "Free" or have a $0 price.
Unique room numbers. Each room will have a unique room number, meaning that no two rooms can have the same room number.
Room type. Rooms can be either single occupant or double occupant.

### Customer Requirements
The application will have customer accounts. Each account has:A unique email for the customer. RegEx is used to check that the email is in the correct format. Customers have to register using the right format email address before they book a room.
