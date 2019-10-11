public class Throw {
    public static void main(String[] args) {
        try {
            double x = sqrt(-4);
        } catch (Exception e) {
            throw new IllegalArgumentException("Exeption occurred during Risk metric calculation",e);
        }
    }

    private static double sqrt(int n) throws Exception {
        if (n < 0) {
            //throw new IllegalArgumentException("To calculate sqrt() arg must be positive, current: " + n);
            throw new Exception("To calculate sqrt() arg must be positive, current: " + n);
        }
        return 100;
    }
}
