package com.example.calculator;

import java.util.Scanner;

public class App 
{

        public static void main (String[]args){
            // I have two types for take answer
            // First for the Scanner
            // Second for the System.out.println
            // If you want, you can comment Scanner logic, uncomment System.out.println logic and check it.
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    System.out.println("If you want to exit press \"exit\". Otherwise, press Enter to continue: ");
                    if (sc.nextLine().equals("exit")) {
                        break;
                    }
                    System.out.print("input: ");
                    System.out.println("output: " + Calculator.evaluate(sc.nextLine()));
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }