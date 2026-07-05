class judgeAnalytics{
    private judgeAnalytics(){
    }


    //when class is loaded these inner clas is not loaded , so when the get instance is called than object is crerated , basically whenever first time the object cretaed clalled than these particluar class is loaded
    //beacuse it loaded it automatically gets intoialised]
    private static class Holder{
        private static final judgeAnalytics judgeAnalytics = new judgeAnalytics();

    }
    public static judgeAnalytics getInstance(){

        return Holder.judgeAnalytics;
    }
}

public class BillPagh {
    
}
