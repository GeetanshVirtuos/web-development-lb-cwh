
// 1: EagerLoading Example

/*
    1) Class "JudgeAnalytics" is thread safe because _line(1) is executed once, when the class is initialized by the JVM and Class initialization in Java is guaranteed to be thread-safe.
    2) JVM ensures that:
        - Only one thread initializes a class
        - Other threads trying to use the class will wait until initialization is complete
*/
class JudgeAnalytics {
    // Object created at the time the class is loaded into the system
    // object is final so it can NOT be modified
    private static final JudgeAnalytics judgeAnalytics = new JudgeAnalytics(); //_line(1)

    // The constructor is private: so, no one can make an instance of this class outside
    private JudgeAnalytics() {
    }

    // Getter method
    // This is static so it can be accessed without needing to make an object of JudgeAnalytics
    public static JudgeAnalytics getInstance() {
        return judgeAnalytics;
    }
}

public class x1_EagerLoading {
    public static void main(String[] args) {
        JudgeAnalytics judgeAnalytics = JudgeAnalytics.getInstance();
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getInstance();

        // Both are just the same objects!
        System.out.println(judgeAnalytics);
        System.out.println(judgeAnalytics2);
    }
}
