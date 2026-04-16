
// 1: LazyLoading Example

// 1) Thread Unsafe Version
// Class "JudgeAnalytics" is NOT thread safe because _line(1) can be executed multiple times via different threads
// class JudgeAnalytics {
//     private static JudgeAnalytics judgeAnalytics;
//     private JudgeAnalytics() {
//     }
//     public static JudgeAnalytics getInstance() {
//         if (judgeAnalytics == null) {
//             // Object NOT created at class initialization
//             judgeAnalytics = new JudgeAnalytics(); //_line(1)
//         }
//         return judgeAnalytics;
//     }
// }

// 2) Thread safe Version but inefficient
// class JudgeAnalytics {
//     private static JudgeAnalytics judgeAnalytics;
//     private JudgeAnalytics() {
//     }
//     // "synchronized" means: only one thread can enter this method at a time
//     /*
//         Why this is inefficient: Locking happens on every call even after the object is created, like:
//             JudgeAnalytics.getInstance(); // called 1000 times
//         👉 Every call still:
//             Acquires a lock
//             Releases a lock
//         That’s unnecessary after the first initialization.
//     */
//     public static synchronized JudgeAnalytics getInstance() {
//         if (judgeAnalytics == null) {
//             // Object NOT created at class initialization
//             judgeAnalytics = new JudgeAnalytics(); 
//         }
//         return judgeAnalytics;
//     }
// }

// 3) Double-checked locking mechanism
/*
    This achieves:
        ✅ Lazy initialization (create object only when needed)
        ✅ Thread safety
        ✅ No unnecessary locking after creation
 */
// class JudgeAnalytics {
//     /*
//         Why "volatile keyword needed"
//             This line: "judgeAnalytics = new JudgeAnalytics();" is NOT atomic. It roughly does:
//                 - Allocate memory
//                 - Initialize object
//                 - Assign reference to judgeAnalytics
//             But the JVM/CPU can reorder it like:
//                 - Allocate memory
//                 - Assign reference ❗
//                 - Initialize object
//             😬 What goes wrong?
//                 Thread A:
//                     Starts creating object
//                     Assigns reference early
//                 Thread B:
//                     if (judgeAnalytics != null)
//                     → sees non-null reference
//             BUT:
//                 👉 Object is not fully constructed yet
//             That’s a nightmare bug.
//             ✅ What volatile does
//                 private static volatile JudgeAnalytics judgeAnalytics;
//             It guarantees:
//                 1. No instruction reordering
//                     Steps happen in correct order
//                 2. Visibility
//                     When one thread writes → others immediately see it.
//                     Ensures no thread is reading "stale cached value"
//     */
//     private static volatile JudgeAnalytics judgeAnalytics;
//     private JudgeAnalytics() {
//     }
//     public static JudgeAnalytics getInstance() {
//         if (judgeAnalytics == null) {          // (1) no lock; Most calls stop here once object exists
//             // "synchronized (JudgeAnalytics.class)" means: Only one thread at a time can enter this block for this class
//             synchronized (JudgeAnalytics.class) {
//                 if (judgeAnalytics == null) {  // (2) with lock; // Why again? Because multiple threads might pass the first check at the same time.
//                     judgeAnalytics = new JudgeAnalytics();
//                 }
//             }
//         }
//         return judgeAnalytics;
//     }
// }

// 4)  Bill Pugh Singleton implementation
/*
    1) This is the best Singleton Pattern implementation in Java as it's same as 3) Double-checked locking mechanism but with simpler code!
 */
class JudgeAnalytics {
    private JudgeAnalytics() {
    }

    // class "Holder" is NOT initialized when the outer "JudgeAnalytics" class is initialized,
    // It is initialized only when someone uses the "getInstance()" method for the first time (see _Line(2))
    private static class Holder {
        private static final JudgeAnalytics judgeAnalytics = new JudgeAnalytics();
    }

    /*
        why inner class "Holder" is "static"?
            🔥 Short answer: Because we don’t want it tied to an instance of the outer class.

            🔹 What if it was NOT static?
                private class Holder { ... } // ❌ non-static inner class

                Then, Every Holder object would need a JudgeAnalytics instance
                But, We are trying to create that very instance!
    */

    public static JudgeAnalytics getInstance() { // _Line(2)
        return Holder.judgeAnalytics;
    }
}

public class x2_LazyLoading {

    public static void main(String[] args) {
        JudgeAnalytics judgeAnalytics = JudgeAnalytics.getInstance();
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getInstance();

        // Both are just the same objects!
        System.out.println(judgeAnalytics);
        System.out.println(judgeAnalytics2);
    }
}
