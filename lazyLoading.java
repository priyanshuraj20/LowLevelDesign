class judgeAnalytics{
    public static judgeAnalytics judgeAnalytics;
    public judgeAnalytics() {
    }

    //instance is created when required 
    public static judgeAnalytics getInstance(){
        if(judgeAnalytics == null){
            judgeAnalytics = new judgeAnalytics();

        }
        return judgeAnalytics;
    }
}

public class lazyLoading {

    public static void main(String[] args){
        judgeAnalytics judgeAnalytics1 = judgeAnalytics.getInstance();
        judgeAnalytics judgeAnalytics2 = judgeAnalytics.getInstance();


        // same instance address:
        System.out.println(judgeAnalytics1);
        System.out.println(judgeAnalytics2);

    }
}
