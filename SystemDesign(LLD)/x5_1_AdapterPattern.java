
interface PaymentGateway{
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid " + amount + "using PayU for order : " + orderId);
    }
}

class RazorpayAPI {
    public void makePayment(String invoiceId, double amountInRupees) {
        System.out.println("Paid " + amountInRupees + " using Razorpay for invoice: " + invoiceId);
    }
}

// Adapter patter: "RazorpayAPI" (a 3rd party API we have no control over) was not compatible with "PaymentGateway" [But "CheckoutService" only accepts a "PaymentGateway"] so you make an adapter (rather than modifying "PaymentGateway")
class RazorpayAdapter implements PaymentGateway {
    private RazorpayAPI razorpayAPI;

    public RazorpayAdapter() {
        this.razorpayAPI = new RazorpayAPI();
    }

    @Override
    public void pay (String orderId, double amount) {
        razorpayAPI.makePayment(orderId, amount); // adapting parameters
    }
}

class CheckoutService {
    private PaymentGateway paymentGateway ;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {
        paymentGateway.pay(orderId, amount);
    }
}

public class x5_1_AdapterPattern {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService(new RazorpayAdapter());
        checkoutService.checkout("123", 100.12);
    }
}
