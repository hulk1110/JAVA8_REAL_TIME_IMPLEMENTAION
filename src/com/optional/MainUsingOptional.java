package com.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUsingOptional {
	public static void main(String[] args) {
		/*
		 * List<Double> result = new ArrayList<>();
		 * 
		 * ThreadLocalRandom.current().doubles(10000).boxed() .forEach(d ->
		 * NewMath.inv(d). ifPresent(inv -> NewMath.sqrt(inv) .ifPresent(sqrt ->
		 * result.add(sqrt)) ) ); System.out.println("#result-->" + result.size());
		 * 
		 * //lets achieve parallism
		 * 
		 * 
		 * List<Double> resultForParallism = new ArrayList<>();
		 * ThreadLocalRandom.current().doubles(10000).boxed().parallel() .forEach(d ->
		 * NewMath.inv(d). ifPresent(inv -> NewMath.sqrt(inv) .ifPresent(sqrt ->
		 * resultForParallism.add(sqrt)) ) ); System.out.println("#result-->" +
		 * resultForParallism.size());
		 */

// proper way of achieving parallelism

		Function<Double, Stream<Double>> flatMapper =
				d -> NewMath.inv(d)
				.flatMap(inv -> NewMath.sqrt(inv))
				.map(sqrt -> Stream.of(sqrt))
				.orElseGet(() -> Stream.empty());
				
				
				List<Double> result=	ThreadLocalRandom.current().doubles(10000).parallel()
				.map(d->d*20 -10)
				.boxed()
				.flatMap(flatMapper)
				.collect(Collectors.toList());
				
				System.out.println("#result-->" + result.size());
	}
}
