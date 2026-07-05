
class JudgeAnalytics {

    private static volatile JudgeAnalytics instance;

    private JudgeAnalytics() {
    }

    public static JudgeAnalytics getInstance() {

        if (instance == null) {                  // First check

            synchronized (JudgeAnalytics.class) {

                if (instance == null) {          // Second check
                    instance = new JudgeAnalytics();
                }
            }
        }

        return instance;
    }
}

public class DCP {

    public static void main(String[] args) {

        JudgeAnalytics obj1 = JudgeAnalytics.getInstance();
        JudgeAnalytics obj2 = JudgeAnalytics.getInstance();

        System.out.println(obj1 == obj2); // true
    }
}
