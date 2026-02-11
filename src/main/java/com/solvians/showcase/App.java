package com.solvians.showcase;

/**
 * Hello world!
 */
public class App {
    public App(String threads, String quotes) {
        System.out.println("constructor is created");
    }

    public static void main(String[] args) {
        // the args are passed from command line
        System.out.println("length : " + args.length);

        if (args.length >= 2) {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            System.out.println("threads : " + threads);

            CertificateUpdateGenerator certificateUpdateGenerator = new CertificateUpdateGenerator(threads, quotes);
            certificateUpdateGenerator.generateQuotes();
        } else {
            throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args);
        }
    }
}
