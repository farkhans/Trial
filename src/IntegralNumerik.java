import java.util.*;;

public class IntegralNumerik {
    private final static Scanner in = new Scanner(System.in);
    private double stepSize, lowerBound, upperBound;
    private int subintervals;

    public IntegralNumerik() {
        System.out.println("Selamat datang di kalkulator integral numerik!");
        System.out.println("Kalkulator ini menghitung hasil dari integral tentu e^(x^2) secara numerik");
        System.out.println();

        System.out.print("Masukkan batas bawah: ");
        lowerBound = Double.parseDouble(in.nextLine());
        System.out.print("Masukkan batas atas: ");
        upperBound = Double.parseDouble(in.nextLine());
        System.out.println();
        
        System.out.println("Pilihan Metode:");
        System.out.println("1. Newton Cotes");
        System.out.println("2. Composite");
        System.out.print("Masukkan pilihan metode: ");
        String metode = in.nextLine();
        System.out.println();

        switch (metode) {
            case "1":
                System.out.println("Newton Cotes");
                System.out.println("1. Midpoint");
                System.out.println("2. Trapezoid");
                System.out.println("3. Simpson");
                System.out.print("Masukkan pilihan metode Newton Cotes: ");
                String newtonCotes = in.nextLine();
                System.out.println();

                switch (newtonCotes) {
                    case "1":
                        System.out.printf("Hasil integral tentu fungsi pada interval [%f, %f] dengan metode midpoint adalah %f%n", lowerBound, upperBound, midpoint());
                        break;
                    case "2":
                        System.out.printf("Hasil integral tentu fungsi pada interval [%f, %f] dengan metode trapezoid adalah %f%n", lowerBound, upperBound, trapezoid());
                        break;
                    case "3":
                        System.out.printf("Hasil integral tentu fungsi pada interval [%f, %f] dengan metode simpson adalah %f%n", lowerBound, upperBound, simpson());
                        break;
                    default:
                        System.out.println("Input tidak valid!");
                        break;
                }
                break;

            case "2":
                System.out.println("Composite");
                System.out.println("1. Composite Midpoint");
                System.out.println("2. Composite Trapezoid");
                System.out.println("3. Composite Simpson");
                System.out.print("Masukkan pilihan metode Composite: ");
                String composite = in.nextLine();
                System.out.println();

                System.out.print("Masukkan banyak subinterval: ");
                subintervals = Integer.parseInt(in.nextLine());
                System.out.println();

                if (subintervals <= 0) {
                    System.out.println("Input subinterval tidak valid!");
                }
                else {
                    stepSize = (upperBound - lowerBound) / subintervals;
                    switch (composite) {
                        case "1":
                            System.out.printf("Hasil integral tentu fungsi pada interval [%f, %f] dengan metode composite midpoint adalah %f%n", lowerBound, upperBound, compositeMidpoint());
                            break;
                        case "2":
                            System.out.printf("Hasil integral tentu fungsi pada interval [%f, %f] dengan metode composite trapezoid adalah %f%n", lowerBound, upperBound, compositeTrapezoid());
                            break;
                        case "3":
                            System.out.printf("Hasil integral tentu fungsi pada interval [%f, %f] dengan metode composite Simpson adalah %f%n", lowerBound, upperBound, compositeSimpson());
                            break;
                        default:
                            System.out.println("Input tidak valid!");
                            break;
                    }
                }
                break;

            default:
                System.out.println("Input tidak valid!");
                break;
        }
    }

    
    private final double f(double x) {
        return Math.exp(Math.pow(x, 2));
    }
    
    private final double midpoint() {
        return (upperBound - lowerBound) * f((upperBound + lowerBound) / 2);
    }
    
    private final double trapezoid() {
        return (f(lowerBound) + f(upperBound)) * (upperBound - lowerBound) / 2;
    }

    private final double simpson() {
        return (upperBound - lowerBound) / 6 * (f(lowerBound) + 4 * f((lowerBound + upperBound) / 2) + f(upperBound));
    }

    private final double compositeMidpoint() {
        double result = 0;
        for (int i  = 0; i < subintervals; i++) {
            result += f(lowerBound + (i + 0.5) * stepSize);
        }
        return result * stepSize;
    }

    private final double compositeTrapezoid() {
        double result = (f(lowerBound) + f(upperBound)) / 2;
        for (int i = 1; i < subintervals; i++) {
            result += f(lowerBound + i * stepSize);
        }
        return result * stepSize;
    }

    private final double compositeSimpson() {
        double result = f(lowerBound) + f (upperBound);
        for (int i = 0; i < subintervals; i++) {
            result += 4 * f(lowerBound + (i + 0.5) * stepSize);
        }
        for (int i = 1; i < subintervals; i++) {
            result += 2 * f(lowerBound + i * stepSize);
        }
        return result * stepSize / 6;
    }

    public static void main(String[] args) {
        new IntegralNumerik();
    }
}