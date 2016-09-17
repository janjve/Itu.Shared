import java.util.function.DoubleSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Created by rrjan on 9/17/2016.
 */
public class Exercise4
{
    public static void main(String[] args)
    {
        // Exercise 4.1
        IntStream ints = IntStream.range(1, 999_999_999);
        DoubleStream doubles = ints.mapToDouble(x -> 1.0 / x);

        Timer t1 = new Timer().start();
        double sumSequential = doubles.sum();
        System.out.println("Runtime sequential = " + t1.stop().result());
        System.out.printf("Sum sequential = %20.16f%n", sumSequential);


        // Exercise 4.2
        IntStream ints2 = IntStream.range(1, 999_999_999);
        DoubleStream doubles2 = ints2.mapToDouble(x -> 1.0 / x);

        Timer t2 = new Timer().start();
        double sumParallel = doubles2.parallel().sum();
        System.out.println("Runtime parallel = " + t2.stop().result());
        System.out.printf("Sum parallel = %20.16f%n", sumParallel);


        // Exercise 4.3
        Timer t3 = new Timer().start();
        double sumImperative = 0;
        for (int i = 1; i < 1_000_000_000; i++)
        {
            sumImperative += 1.0 / i;
        }
        System.out.println("Runtime imperative = " + t3.stop().result());
        System.out.printf("Sum imperative = %20.16f%n", sumImperative);


        // Exercise 4.4

        DoubleGenerator doubleGenerator = new DoubleGenerator();
        DoubleStream doubles4 = DoubleStream.generate(doubleGenerator).limit(999_999_999);

        Timer t4 = new Timer().start();
        double sumGenerator = doubles4.sum();
        System.out.println("Runtime generator = " + t4.stop().result());
        System.out.printf("Sum generator = %20.16f%n", sumGenerator);

        // Exercise 4.5
        DoubleGenerator doubleGenerator2 = new DoubleGenerator();
        DoubleStream doubles5 = DoubleStream.generate(doubleGenerator2).limit(999_999_999);

        Timer t5 = new Timer().start();
        double sumGeneratorParallel = doubles5.parallel().sum();
        System.out.println("Runtime generator parallel = " + t5.stop().result());
        System.out.printf("Sum generator parallel = %20.16f%n", sumGeneratorParallel);
    }
}

// Silly mutable double generator.
class DoubleGenerator implements DoubleSupplier {
    private int divisor = 1;
    @Override
    public double getAsDouble()
    {
        double nextDouble = 1.0 / divisor;
        divisor += 1;
        return nextDouble;
    }
}

// Simple timer
class Timer
{
    private long start = 0;
    private long end = 0;

    public Timer start()
    {
        start = System.nanoTime();
        return this;
    }

    public Timer stop()
    {
        end = System.nanoTime();
        return this;
    }

    public long result()
    {
        return (end - start) / 1_000_000;
    }
}
