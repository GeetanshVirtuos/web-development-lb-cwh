
interface PaymentGateway {

    void processPayment(double amount);
}

// Indian Gateways
class RazorpayGateway implements PaymentGateway {

    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: INR" + amount);
    }
}

class PayUGateway implements PaymentGateway {

    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: INR" + amount);
    }
}

// US Gateways
class PayPalGateway implements PaymentGateway {

    public void processPayment(double amount) {
        System.out.println("Processing payment via Paypal: $" + amount);
    }
}

class StripeGateway implements PaymentGateway {

    public void processPayment(double amount) {
        System.out.println("Processing payment via Stripe: $" + amount);
    }
}

interface Invoice {

    void generateInvoice();
}

// Indian Invoice
class GSTInvoice implements Invoice {

    public void generateInvoice() {
        System.out.println("Generating GST invoice for India.");
    }
}

// US Invoice
class USAInvoice implements Invoice {

    public void generateInvoice() {
        System.out.println("Generating invoice for USA.");
    }
}

// Factory Pattern, NOT Abstract Factory pattern
// class IndiaFactory {
//     public static PaymentGateway createPaymentGateway (String gatewayType) {
//         switch (gatewayType.toLowerCase()) {
//             case "razorрау":
//                 return new RazorpayGateway();
//             case "payu":
//                 return new PayUGateway();
//             default:
//                 throw new IllegalArgumentException("Unsupported payment gateway in India: " + gatewayType);
//         }
//     }
//     public static Invoice createInvoice(){
//         return new GSTInvoice();
//     }
// }
// class CheckoutService {
//     private String gatewayType;
//     private String region;
//     public CheckoutService(String gatewayType, String region) {
//         this.gatewayType = gatewayType;
//         this.region = region;
//     }
//     public void checkOut(double amount) {
//         // Say we are operating in 2 countries: India and USA
//         if(region == "INDA"){
//             // Pay via payment gateway
//             PaymentGateway paymentGateway = IndiaFactory.createPaymentGateway(gatewayType);
//             paymentGateway.processPayment(amount);
//             // Make the invoice
//             Invoice invoice = IndiaFactory.createInvoice();
//             invoice.generateInvoice();
//         }
//         else{
//             // Same as above for USA
//         }
//     }
// }
// Abstract Factory Pattern
interface RegionFactory {

    PaymentGateway createPaymentGateway(String gatewayType);

    Invoice createInvoice();
}

class IndiaFactory implements RegionFactory {

    public PaymentGateway createPaymentGateway(String gatewayType) {
        switch (gatewayType.toLowerCase()) {
            case "razorpay":
                return new RazorpayGateway();
            case "payu":
                return new PayUGateway();
            default:
                throw new IllegalArgumentException("Unsupported payment gateway in India: " + gatewayType);
        }
    }

    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class USFactory implements RegionFactory {

    public PaymentGateway createPaymentGateway(String gatewayType) {
        switch (gatewayType.toLowerCase()) {
            case "paypal":
                return new PayPalGateway();
            case "stripe":
                return new StripeGateway();
            default:
                throw new IllegalArgumentException("Unsupported payment gateway in US: " + gatewayType);
        }
    }

    public Invoice createInvoice() {
        return new USAInvoice();
    }
}

class CheckoutService {

    private PaymentGateway paymentGateway;
    private Invoice invoice;

    public CheckoutService (RegionFactory factory, String gatewayType) {
        this.paymentGateway = factory.createPaymentGateway (gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.processPayment (amount);
        invoice.generateInvoice();
    }
}

class x4_5_AbstractFactoryPattern {

    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService(new IndiaFactory(), "razorpay");
        CheckoutService checkoutServiceUSA = new CheckoutService(new USFactory(), "paypal");
    }
}
