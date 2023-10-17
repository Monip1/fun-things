---
title: Programming Assignment III
author: CSE 11
date: October 17, 2023
geometry: margin=2cm
output: pdf_document
---

# Learning Goals

In this assignment, we will learn.

# Positive Rate, Gear Up

_Jerry Likes Planes_

## Starter Code

We will use modified verions of the `Flight` and `Passenger` class.
We will make a new `Ticket` class.

## DIY Testing: The `FlightTest` Class

It's up to you to adaquately test your own code.
If you are selected for an in-person code review, you *will* be questioned on your testing.

## Library Class: `LocalDateTime`

To deal with time we will be using `LocalDateTime` which is a class built into Java like `String` is.
We will give you detailed descriptions of the functions you will need and how to use them but if you would like a full description of its features, you can read [the Java documentations here](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html).

**Creating a `LocalDateTime` object:** For this class, we do not use the traditional
`new` keyword to create an object. Instead, the library provides us with a convenient
static method, `LocalDateTime.of()`, that can be used like so:

```
/* LocalDateTime object for October 16, 2023, 09:26 PM. */
LocalDateTime dt1 = LocalDateTime.of(2023, 10, 16, 21, 26);

/* LocalDateTime object for July 29, 1954, 12:30 PM. */
LocalDateTime dt2 = LocalDateTime.of(1954, 7, 29, 12, 30);
```

As you have probably figured out, the arguments to this `of` method are integers for
the **year**, **month**, **day**, **hour**, and **minute**. You can find the original
documentation for this method [here](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html#of-int-int-int-int-int-).

**Creating a `LocalDateTime` object for the current time**: If you want to create
a `LocalDateTime` object that represents the current moment, you can use the method
`LocalDateTime.now()` to obtain such an object. For example:

```
LocalDateTime currentTime = LocalDateTime.now();
```

This method takes no argument. You can find the original documentation for it
[here](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html#now--).

**Comparing two `LocalDateTime` objects**: For this purpose, `LocalDateTime` objects
have two methods: `isBefore` and `isAfter`. They are very similar to our `costsMoreThan`
method in the `Flight` class in PA 2. Both of these methods return a `boolean` value.

You can use these two methods like so:

```
LocalDateTime time1 = /* some time */;
LocalDateTime time2 = /* some time */;
boolean time1BeforeTime2 = time1.isBefore(time2);
boolean time2AfterTime1 = time2.isAfter(time1);
```

You can find the official documentation for these methods here:
[`isBefore()`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html#isBefore-java.time.chrono.ChronoLocalDateTime-) &
[`isAfter()`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html#isAfter-java.time.chrono.ChronoLocalDateTime-)

**Adding an offset to a `LocalDateTime` object**: sometimes, we wish to obtain a new
time by adding some offset to anoter time. For intsance, what is `time1` plus 3 hours;
what is `time1` plus 1 day?

In this PA, we ask you to do something like that, and for that, the `LocalDateTime`
class has the `plusDays` method for us to use:

```
LocalDateTime time1 = /* some time */;
LocalDateTime dayAfterTime1 = time1.plusDays(1);  // 1 day after time1
LocalDateTime weekAfterTime1 = time1.plusDays(7); // 1 week (7 days) after time1
```

Note that the `plusDays` method **returns** a `LocalDateTime` object as well,
which means you can **chain** these methods together.

For example, suppose we have two `LocalDateTime` objects `time1` and `time2`,
and we want to find out if `time1` plus 3 days is earlier than `time2`:

```
LocalDateTime time1 = /* some time */;
LocalDateTime time2 = /* some time */;
boolean time1ThreeDaysBeforeTime2 = time1.plusDays(3).isBefore(time2);
```

Now that's pretty nifty.

## The New `Flight` Class

The `Flight` class will retain all of its fields from PA 2, and have a few new fields.
Here's a list of what you should have:

| Type              | Name                | Description                                                                                   |
| ----------------- | ------------------- | --------------------------------------------------------------------------------------------- |
| String            | `flightNo`          | The flight number. E.g. `"UAL 238"` for United Airlines Flight 238.                           |
| String            | `departureAirport`  | Departure airport ICAO code. E.g. `"KSAN"` for our own San Diego International Airport.       |
| String            | `arrivalAirport`    | Arrival airport ICAO code. E.g. `"KMSN"` for the Dane County Regional Airport in Madison, WI. |
| String            | `aircraftType`      | ICAO aircraft designator. E.g. `"B38M"` for Boeing 737 MAX 8.                                 |
| double            | `cost`              | The ticket cost of the flight in dollars.                                                     |
| **LocalDateTime** | **`departureTime`** | **The flight's gate departure time including year, month, day, hour, and minute.**            |
| **LocalDateTime** | **`arrivalTime`**   | **The flight's gate arrival time including year, month, day, hour, and minute.**              |
| **int**           | **`numBooked`**     | **The number of tickets booked on this flight.**                                              |
| **int**           | **`capacity`**      | **The total passenger capacity of this flight.**                                              |

(The bold items are the fields added in this PA.)

Here are the methods for this class:

**Method 1: The constructor**

As always, we begin with the constructor. In this PA, we will see that not all
fields in a class need to have a corresponding parameter in the constructor.
For our new `Flight` constructor, we will take in parameters for everything
except the `numBooked` field, which we always initialize to 0.

Your constructor signature should look like the following:

```
Flight(String flightNo, String departureAirport,
        String arrivalAirport, String aircraftType,
        double cost, LocalDateTime departureTime,
        LocalDateTime arrivalTime, int capacity);
```

This constructor should initialize all the fields according to the parameters.
For `numBooked`, which does **not** have a corresponding parameter, your constructor
should initialize it to `0`.

**Method 2: `departsBefore`**

Our next method tests if _this_ flight departs earlier than _another_ flight, by
comparing the `departureTime` fields in both `Flight` objects. This method takes
**one** `Flight` object as argument, and returns `true` if _this_ flight has an
earlier departure time, or `false` if the _other_ flight has an earlier departure
time.

Read the section on `LocalDateTime` usage above _very carefully_ to understand
how you can perform these comparisons.

**Method 3: `arrivesBefore`**

This method is basically the same thing as the method above, except comparing the
`arrivalTime` of _this_ flight and _another_ flight. Again, it takes **one**
`Flight` object as argument and returns a boolean value.

**Method 4: `costInSkyPoints`**

The `cost` field in the `Flight` class records the ticket cost in **dollars**.
The SkyPoints™ to dollars conversion ratio is 100:1. This method, `costInSkyPoints`,
returns how much the flight would cost in SkyPoints™ by multiplying the dollar
cost by 100. This method takes no argument and returns a double.

## The New `Ticket` Class

Next, let's write our brand new entry to our flight scheduling system -- a _ticket_,
represented by the very aptly named `Ticket` class. When a passenger successfully
books a flight, a `Ticket` object is created. We will write a method for ticket
booking later.

Our `Ticket` class contains the following fields:

| Type          | Name                     | Description                                                |
| ------------- | ------------------------ | ---------------------------------------------------------- |
| Flight        | `flight`                 | The flight for which this ticket was bought.               |
| Passenger     | `pax`                    | The passenger who purchased this ticket.                   |
| LocalDateTime | `purchaseTime`           | The time of purchase.                                      |
| LocalDateTime | `cancelDeadline`         | The cancellation deadline for this ticket.                 |
| boolean       | `purchasedWithSkyPoints` | Whether this ticket was purchased with SkyPoints™ or cash. |
| boolean       | `cancelled`              | Whether or not this ticket has been cancelled/refunded.    |

**Method 1: The constructor**

The constructor only takes three parameters:

```
Ticket(Passenger pax, Flight flight, boolean purchasedWithSkyPoints);
```

Notice that out of all six fields in the table above, the constructor only takes
three as parameters. You should use the parameters to initialize those fields.
The remaining should be handled like so:

- `purchaseTime`: record the current time using `LocalDateTime.now()`
- `cancelDeadline`: obtained by adding a 1-day offset to the `purchaseTime`
- `cancelled`: always initialize to `false`.

## The New `Passenger` Class

The `Passenger` class will also have some new fields:

| Type       | Name                 | Description                                       |
| ---------- | -------------------- | ------------------------------------------------- |
| int        | `skyId`              | The CSE 11 Loyalty Program's unique user ID.      |
| String     | `firstName`          | Well you know what this is.                       |
| String     | `lastName`           | See above.                                        |
| int        | `skyPoints`          | CSE 11 Loyalty Program reward points.             |
| ~~Flight~~ | ~~`upcomingFlight`~~ | ~~This passenger's upcoming flight.~~             |
| **double** | **`cashBalance`**    | **The cash balance in this passenger's account.** |

**Method 1: The constructor**

The constructor for the `Passenger` class should have the following signature:

```
Passenger(int skyId, String firstName, String lastName,
        int skyPoints, int cashBalance);
```

--and it should initialize all the fields according to the parameters. Note that we
have removed the `upcomingFlight` field from the class and its corresponding parameter
in the constructor method.

**Method 2: `book`**

Alright, here comes the big one.

```
Ticket book(Flight flight);
```

This method is used by a passenger object to book a flight. If the purchase is successful,
the method returns a newly created `Ticket` object; otherwise, it returns `null`,
indicating the purchase attempt has failed.

Here are some things you need to check in this method:

- If the flight is fully booked, _i.e._ the number of tickets booked is greater than
  or equal to the total capacity of the flight, then the booking should fail, and
  the method should return `null`.
- If the passenger has enough SkyPoints™ in their account for the ticket, we deduct
  the amount of SkyPoints™ from their balance, and return a new Ticket object. **Note**
  that the cost of a ticket in SkyPoints™ is different in amount than the dollar cost.
  Use the `costInSkyPoints` method you wrote earlier.
- If the passenger does not have enough SkyPoints™ for the purchase, then we check their
  cash balance. If there is enough for a cash purchase, then we deduct that amount from
  their account balance, and return a new Ticket object.
- If the passenger does not have enough SkyPoints™ or cash for the ticket, the booking
  attempt should fail.

There are several things to pay attention to here:

1. Depending on what currency (dollars vs. SkyPoints™) was used to purchase the ticket,
   you need to pass in the correct value for the `purchasedWithSkyPoints` argument of the
   `Ticket` constructor.
1. When a ticket is booked successfully, the `numBooked` field of the flight should be
   incremented to reflect that. You can either do it in this method, or, since the creation
   of a `Ticket` object is synonymous to a successful booking, you can do the incrementing
   in the `Ticket` constructor. Think about which option is the superior choice here!

## Final Problem: Cancelling a flight.

In the `Ticket` class, create a `cancel` method that, when called, cancels a Ticket
booking and issues a refund to the passenger. The method should return a boolean value
to indicate if the cancellation was successful.

Here's how it works:

1. Check that the cancellation deadline has not passed by comparing `cancelDeadline`
   against the current time. Again, this can be obtained by `LocalDateTime.now()`. If
   the cancellation deadline has passed, return `false`.
1. If this ticket was purchased with SkyPoints™ (`purchasedWithSkyPoints`), we issue
   the full refund in SkyPoints™ but increasing the passenger's SkyPoints™ balance. Again,
   remember to use your `costInSkyPoints` method in the `Flight` class to calculate the
   correct SkyPoints™ cost value.
1. If this ticket was instead purchased with cash, we issue a refund to the passenger's
   cash balance. **Except in this case, we charge a 5% cancellation fee.** So the passenger
   would only get 95% of the original dollar amount back. Sucks doesn't it? Well, join
   the SkyPoints™ Loyalty Program today! You are already pre-approved for our credit card!
1. If the ticket has been successfully cancelled, we need to decrement the
   `numBooked` field in the corresponding `Flight` object, and set the `cancelled` field
   in this ticket to `true` to indicate it is no longer a valid ticket. Finally, we
   return `true` to indicate the cancellation was successful.
