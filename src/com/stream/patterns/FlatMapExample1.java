package com.stream.patterns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlatMapExample1 {
	public static void main(String[] args) throws IOException {

		Stream<String> stream1 = Files.lines(Paths.get("files/TomSawyer1"));
		Stream<String> stream2 = Files.lines(Paths.get("files/TomSawyer2"));
		Stream<String> stream3 = Files.lines(Paths.get("files/TomSawyer3"));
		Stream<String> stream4 = Files.lines(Paths.get("files/TomSawyer4"));
//
//		System.out.println("no of lines in Stream 1 ->" + stream1.count());
//		System.out.println("no of lines in Stream 2 ->" + stream2.count());
//
//		System.out.println("no of lines in Stream 3 ->" + stream3.count());
//		System.out.println("no of lines in Stream 4 ->" + stream4.count());

		// lets merge streams

		Stream<Stream<String>> streamOfStreams = Stream.of(stream1, stream2, stream3, stream4);

		// System.out.println("Check the count ->" + streamOfStreams.count());

		// flatten everthing in one stream

		// Stream<String> streamOfLines = streamOfStreams.flatMap(mapper -> mapper);

		// better way

		Stream<String> streamOfLines2 = streamOfStreams.flatMap(Function.identity());
		// System.out.println("Check the count ->" + streamOfLines.count());
		// System.out.println("Check the count ->" + streamOfLines2.count());

		// ------ Stream of word from stream of lines and count the #

		Function<String, Stream<String>> lineSplitter = line -> Pattern.compile(" ").splitAsStream(line);
//
//		Stream<String> streamOfWords = streamOfLines2.flatMap(lineSplitter);
//
//		System.out.println("#words :" + streamOfWords.count());

		// --- count distinct words

		Stream<String> streamOfWords = streamOfLines2.flatMap(lineSplitter).map(word -> word.toLowerCase()).distinct();

		System.out.println("#words:" + streamOfWords.count());
		// note: if we don't comment above lines we will get exception od stream already
		// closed/open

	}
}
