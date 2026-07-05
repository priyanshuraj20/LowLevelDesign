//eager loading:
class judgeAnalytics{
    // Eager Loading : Instance created during class loaded
    private static final judgeAnalytics judgeAnalytics = new judgeAnalytics();


    //constructor
    private judgeAnalytics(){

    }

    public static judgeAnalytics getInstance(){
        return judgeAnalytics;
    }


}
public class eagerLoading{
    public static void main(String[] args){
    judgeAnalytics judgeAnalytics1 = judgeAnalytics.getInstance();
    judgeAnalytics judgeAnalytics2 = judgeAnalytics.getInstance();


    //you will be gettuing same instance address 
    System.out.println(judgeAnalytics1);
    System.out.println(judgeAnalytics2);

        

    }
    
}