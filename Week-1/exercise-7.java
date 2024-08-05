public class FinancialForecasting {

    public static double predictFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return predictFutureValue(currentValue * (1 + growthRate / 100), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double currentValue = 1000.0; 
        double growthRate = 5.0;      
        int years = 10;             

        double futureValue = predictFutureValue(currentValue, growthRate, years);
        System.out.println("The predicted future value after " + years + " years is: " + futureValue);
    }
}
