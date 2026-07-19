package StructuralPattern;



// Handles payment
class PaymentService {

    public void makePayment(String accountId, double amount) {
        System.out.println("Payment of ₹" + amount +
                " successful for account " + accountId);
    }
}


// Handles seat reservation
class SeatReservationService {

    public void reserveSeat(String movieId, String seatNumber) {
        System.out.println("Seat " + seatNumber +
                " reserved for movie " + movieId);
    }
}


// Sends booking confirmation
class NotificationService {

    public void sendBookingConfirmation(String userEmail) {
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}


// Adds loyalty points
class LoyaltyPointsService {

    public void addPoints(String accountId, int points) {
        System.out.println(points +
                " loyalty points added to account " + accountId);
    }
}


// Generates ticket
class TicketService {

    public void generateTicket(String movieId, String seatNumber) {
        System.out.println("Ticket generated for movie "
                + movieId + ", Seat: " + seatNumber);
    }
}
//public class FacadePattern {
//    //Client code:
//    PaymentService payment = new PaymentService();
//    payment.makePayment(accountId, amount);
//
//    SeatReservationService seat = new SeatReservationService();
//seat.reserveSeat(movieId, seatNo);
//
//    TicketService ticket = new TicketService();
//ticket.generateTicket(movieId, seatNo);
//
//    NotificationService notification = new NotificationService();
//notification.sendBookingConfirmation(email);
//
//    LoyaltyPointsService loyalty = new LoyaltyPointsService();
//loyalty.addPoints(accountId, 100);
//}

//Facade class:
class MovieBookingFacade {

    private PaymentService paymentService = new PaymentService();
    private SeatReservationService seatService = new SeatReservationService();
    private TicketService ticketService = new TicketService();
    private NotificationService notificationService = new NotificationService();
    private LoyaltyPointsService loyaltyService = new LoyaltyPointsService();

    public void bookMovie(
            String movieId,
            String seatNo,
            String accountId,
            String email,
            double amount
    ) {

        paymentService.makePayment(accountId, amount);

        seatService.reserveSeat(movieId, seatNo);

        ticketService.generateTicket(movieId, seatNo);

        notificationService.sendBookingConfirmation(email);

        loyaltyService.addPoints(accountId, 100);
    }
}
//Facade Pattern → Complex system ke upar ek simple entry point provide karta hai. (Simplified Interface)
public class FacadePattern {

    public static void main(String[] args) {

        MovieBookingFacade facade = new MovieBookingFacade();

        facade.bookMovie(
                "Movie101",
                "A10",
                "ACC123",
                "abc@gmail.com",
                350
        );
    }
}

//Payment of ₹350 successful for account ACC123
//Seat A10 reserved for movie Movie101
//Ticket generated for movie Movie101, Seat: A10
//Booking confirmation sent to abc@gmail.com
//100 loyalty points added to account ACC123