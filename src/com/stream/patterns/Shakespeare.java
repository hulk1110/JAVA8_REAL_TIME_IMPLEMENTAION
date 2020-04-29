package com.stream.patterns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Shakespeare {

	public static void main(String[] args) throws IOException {
		Set<String> shakespeareWords = Files.lines(Paths.get("files/words_shakespeare")).map(word -> word.toLowerCase())
				.collect(Collectors.toSet());

		Set<String> scrabbleWords = Files.lines(Paths.get("files/ospd")).map(word -> word.toLowerCase())
				.collect(Collectors.toSet());

		System.out.println("# words of shakespeare : " + shakespeareWords.size());
		System.out.println("# words of scrabble : " + scrabbleWords.size());

		final int[] scrabbleENScore = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
				10 };

		Function<String, Integer> score = word -> word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum();

		ToIntFunction<String> intScore = word -> word.chars().map(letter -> scrabbleENScore[letter - 'a']).sum();

		 System.out.println("Score of Hello:" + intScore.applyAsInt("hello"));

		String bestword = shakespeareWords.stream().filter(word -> scrabbleWords.contains(word))
				.max(Comparator.comparing(score)).get().toLowerCase();

		System.out.println("Best word--->" + bestword);

		IntSummaryStatistics summaryStatistics = shakespeareWords.stream().filter(scrabbleWords::contains)
				.mapToInt(intScore).summaryStatistics();

		System.out.println("stats: " + summaryStatistics);

	}
}
