import java.util.*;

abstract class NotificationSender {

    // =======================
    // Template Method Pattern (Behavioral Design Pattern)
    // Defines the overall algorithm for sending a notification.
    // Common steps are fixed, while specific steps are delegated
    // to subclasses through abstract methods (hooks).
    // =======================

    // Final template method
    public final void send(String to, String rawMessage) {
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);

        // Logic not common (implemented by subclasses)
        String composedMessage = composeMessage(formatted);
        sendMessage(to, composedMessage);

        // Common
        postSendAnalytics(to);
    }

    // Common Step 1
    private void rateLimitCheck(String to) {
        System.out.println("Checking rate limits for: " + to);
    }

    // Common Step 2
    private void validateRecipient(String to) {
        System.out.println("Validating recipient: " + to);
    }

    // Common Step 3
    private String formatMessage(String message) {
        return message.trim(); // could include HTML escaping, emoji processing, etc.
    }

    // Common Step 4
    private void preSendAuditLog(String to, String msg) {
        System.out.println("Logging before send: " + msg + " to " + to);
    }

    // Hook for subclasses
    protected abstract String composeMessage(String formattedMessage);

    protected abstract void sendMessage(String to, String message);

    // Common Step 5 (Optional Hook)
    protected void postSendAnalytics(String to) {
        System.out.println("Analytics updated for: " + to);
    }
}

class EmailNotification extends NotificationSender {

    @Override
    protected String composeMessage(String formattedMessage) {
        return "<html><body><p>" + formattedMessage + "</p></body></html>";
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending EMAIL to " + to + " with content:\n" + message);
    }
}

class SMSNotification extends NotificationSender {

    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS] " + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending SMS to " + to + " with message: " + message);
    }

    // Overriding optional hook
    @Override
    protected void postSendAnalytics(String to) {
        System.out.println("Custom SMS analytics for: " + to);
    }
}

public class templatePattern {
    public static void main(String[] args) {

        NotificationSender email = new EmailNotification();
        NotificationSender sms = new SMSNotification();

        email.send("abc@gmail.com", " Hello Email ");
        System.out.println();

        sms.send("9876543210", " Hello SMS ");
    }
}