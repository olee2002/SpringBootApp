package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {
	public static void main(String args[]) {

		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Spring API is running successfully!");

		Point point1 = new Point(1,1);
		Point point2 = point1;
		System.out.println("**********");
		System.out.println(point2);
		System.out.println("**********");

//		String[] fruits = {"Apple", "Mongo", "Orange"};

		int numbers[] = {1,2,3,4};

		for(int i=0; i<numbers.length; i++)
			System.out.println(numbers[i]);

		for(Integer num: numbers)
			System.out.println(num);

//		Scanner input = new Scanner(System.in);
//
//		byte userInput = input.nextByte();
//		if(userInput % 3 == 0 && userInput % 5 == 0){
//			System.out.println("fizzbuzz");
//		} else if (userInput % 5 == 0){
//			System.out.println("buzz");
//		} else if (userInput % 3 == 0){
//			System.out.println("fizz");
//		} else {
//			System.out.println(userInput);
//		}
//
//		double principle = input.nextDouble();
//		String currencyFormat = NumberFormat.getCurrencyInstance().format(principle);
//		double monthly = input.nextDouble()/100/12;
//		double duration = input.nextDouble() * 12;
//		System.out.println("Principle: "+currencyFormat);
//		System.out.println("Interest: "+monthly);
//		System.out.println("Duration: "+duration);
//		double mortgage = principle * (monthly * Math.pow((1+monthly),duration))/(Math.pow((1+monthly),duration)-1);
//		System.out.println("Your Mortgage is $" + Math.round(mortgage) +"/month");
	}
}
