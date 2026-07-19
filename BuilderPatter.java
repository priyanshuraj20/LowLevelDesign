import java.util.*;
public class BuilderPatter {

    // think if we want to build food delivery app : burger meal hain

    //BurgerMeal

    //BUn
    //Patty


    //mano abb optional hota : sides, toppings,cheese -> so will
    // you add these paramter in constructor as user may not pass these values  toh client ko nulll null dena padega
    //So these is prblm that builder patterns solve
//    static class BurgerMeal{
//        private static String bun;
//        private static String patty;
//
//        public static BurgerMeal(String bun, String patty){
//            this.bun = bun;
//            this.patty = patty;
//
//        }
//        public static void main(String[] args){
//            BurgerMeal bg = new BurgerMeal("wheat","veg");
//
//        }
//    }


    //so NOW:
    class BurgerMeal {
        //Required
        private final String bun;
        private final String patty;


        //no usage:
        private final boolean hasCheese;
        private final List<String> toppings;
        private final String side;
        private final String drink;

        public BurgerMeal(BurgerBuilder builder) {
            this.bun = builder.bun;
            this.patty = builder.patty;
            this.hasCheese = builder.hasCheese;
            this.toppings = builder.toppings;
            this.side = builder.side;
            this.drink = builder.drink;
        }
//we will be replacing multiple parameter with a single class
        // static inner class (inside burgerMeal)

        public static class BurgerBuilder{
            //over here we wiol be defining same parameter

            //Required
            private final String bun;
            private final String patty;


            //no usage:
            private  boolean hasCheese;
            private  List<String> toppings;
            private  String side;
            private  String drink;


            public BurgerBuilder(String bun, String patty){
                this.bun = bun;
                this.patty = patty;
            }
            //for all the optionals one we can have separate setters;
            public BurgerBuilder withCheese(boolean hasCheese){
                this.hasCheese = hasCheese;
                return this;    // it is returning BurgerBuilder class at any moment
                //that is we are returning current instance of burger builder and aghar uske baad
                // k      uch aur hoga like withDrink toh woh class hojayega
            }public BurgerBuilder withToopings(List<String> toppings){
                this.toppings = toppings;
                return this;    // it is returning BurgerBuilder class at any moment
            }public BurgerBuilder withSide(String side){
                this.side = side;
                return this;    // it is returning BurgerBuilder class at any moment
            }public BurgerBuilder withDrink(String drink){
                this.drink = drink;
                return this;    // it is returning BurgerBuilder class at any moment
            }


            //which is going to create new Burger Meal : bascially i am creating object of outer call
            //this passing is the refrence to the burgerBuilder instance
            public BurgerMeal build(){
                return new BurgerMeal(this);
            }


        }
        public static void main(String[] args){
            BurgerMeal bg = new BurgerMeal.BurgerBuilder("wheat","veg").build();
            //we arte trying to create object of static class - >outerclass dot inner clas to access
            // once created object called the build
            //So when we call build it return object of Burgermeal

            //if want cheese now : and also if drink:
            BurgerMeal bgWithCheese= new BurgerMeal.BurgerBuilder("wheat","veg").withCheese(true).withDrink("coco cola").build();
        }
    }
}
