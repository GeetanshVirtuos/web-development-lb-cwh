import java.util.HashMap;
import java.util.Map;

interface EmailTemplate extends Cloneable {
    EmailTemplate clone(); // Deep Copy recommended
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate {
    private String subject;
    private String content;

    public WelcomeEmail() {
        this.subject = "Welcome to TUF+!";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public WelcomeEmail clone() {
        try {
            return (WelcomeEmail) super.clone(); // super refers to the parent class of WelcomeEmail. Since you didn't extend anything explicitly, the parent is: "Object" class. "Object.clone()" is shallow cloning. You must implement deep cloning yourself [RECOMMENDED IRL].
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }
    
    @Override
    public void setContent(String content) {
      this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending to " + to + "subject: " +  content);
    }
}

// You could have used the "EmailTemplate" class as it is but the proper Prototype pattern would be to make "EmailTemplateRegistry" class [Might be an overkill in some situations though]
/*
    The EmailTemplateRegistry will store various objects for various types of emails like:
        i) Welcome email
        ii) Update password email
        iii) Login email etc.

*/
class EmailTemplateRegistry {
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        templates.put("welcome", new WelcomeEmail());
        // Add more templates like "discount", "feature-update" etc.
    }

    public static EmailTemplate getTemplate(String type) {
        return templates.get(type).clone(); // clone avoids modifying original
    }
}

public class x6_PrototypePattern {
    public static void main(String[] args) {
        EmailTemplate email1 = EmailTemplateRegistry.getTemplate("welcome");

        email1.setContent("Welcome to TUF");
        email1.send("a@gmail.com");

        EmailTemplate email2 = EmailTemplateRegistry.getTemplate("welcome");

        email2.setContent("Welcome to TUF+");
        email2.send("b@gmail.com");
    }
}
