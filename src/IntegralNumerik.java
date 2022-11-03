public class IntegralNumerik {

    public double f(double x) {
        return Math.exp(Math.pow(x, 2));
    }

    public double midpoint(double upperBound, double lowerBound) {
        return (upperBound - lowerBound) * f((upperBound + lowerBound) / 2);
    }

    public double trapezoid(double upperBound, double lowerBound) {
        return (f(upperBound) + f(lowerBound)) * (upperBound - lowerBound) / 2;
    }

    public double simpson(double upperBound, double lowerBound) {
        return (upperBound - lowerBound) / 6 * (f(lowerBound) + f((lowerBound + upperBound) / 2) + f(upperBound));
    }

    public double compositeMidpoint(double upperBound, double lowerBound, int subintervals) {
        double res = 0;
        double stepSize = (upperBound - lowerBound) / subintervals;
        for (int i = 0; i < subintervals; i++) {
            res += stepSize * f(lowerBound + (i + 0.5) * stepSize);
        }
        return res;
    }

    public double compositeTrapezoid(double upperBound, double lowerBound, int subintervals) {
        double res = (f(lowerBound) + f(upperBound)) / 2;
        double stepSize = (upperBound - lowerBound) / subintervals;
        for (int i = 1; i < subintervals - 1; i++) {
            res += f(lowerBound + stepSize * i);
        }
        return res * stepSize;
    }

    public double compositeSimpson(double upperBound, double lowerBound, int subintervals) {
        double stepSize = (upperBound - lowerBound) / subintervals;
        double res = f(lowerBound) + f(upperBound);
        for (int i = 0; i < subintervals; i++) {
            res += 4 * f(lowerBound + (i + 0.5) * stepSize);
        }
        for (int i = 1; i < subintervals; i++) {
            res += 2 * f(lowerBound + i * stepSize);
        }
        return res * stepSize / 6;
    }
}
