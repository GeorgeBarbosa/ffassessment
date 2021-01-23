package com.firefley.assessment.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.firefley.assessment.dto.NumberGeneratorResultDTO;
import com.firefley.assessment.exception.CustomException;

@Service
public class NumberGeneratorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static final String UNSORTED_PREFIX = "unsorted-";
	protected static final String SORTED_PREFIX = "sorted-";
	protected static final int SIZE_RANDOM_NUMBER = 1_000_000;
	
	public NumberGeneratorResultDTO generateNumbers() throws CustomException {
		
		Stream<Integer> stream = generateRandomNumbers();		
		String unsortedFileName = this.createFile(stream, UNSORTED_PREFIX);

		Stream<Integer> sorted = this.sortNumbers(unsortedFileName);
		String sortedFileName = this.createFile(sorted, SORTED_PREFIX);
		
		return new NumberGeneratorResultDTO(
				unsortedFileName,
				sortedFileName,
				SIZE_RANDOM_NUMBER
				);
	}
	
	private Stream<Integer> sortNumbers(String fileName) throws CustomException {
		Stream<String> fileStream = readFile(fileName);
		return fileStream.map(s -> Integer.valueOf(s)).sorted();
	}
	
	private Stream<Integer> generateRandomNumbers() {
		return new Random().ints(0, SIZE_RANDOM_NUMBER).distinct().limit(SIZE_RANDOM_NUMBER).boxed();
	}
	
	private Stream<String> readFile(String fileName) throws CustomException {
		Path path = Paths.get(new File(fileName).toURI());
		try {
			return Files.lines(path);
		} catch (IOException e) {
			this.logger.error(e.getMessage());
			throw new CustomException("Unable to read file " + fileName);
		}
	}
	
	private String createFile(Stream<Integer> stream, String prefix) throws CustomException {
		String fileName = getNewFileName(prefix);
		try (PrintWriter pw = new PrintWriter(fileName, StandardCharsets.UTF_8)) {
			stream.forEachOrdered(pw::println);
		} catch (IOException e) {
			this.logger.error(e.getMessage());
			throw new CustomException("Unable to create file " + fileName);
		}
		return fileName;
	}
	
	private String getNewFileName(String prefix) {
		return String.format(
				"%s%s%s",
				prefix, 
				new SimpleDateFormat("ddMMyy-hhmmss.SSS").format(new Date()),
				".txt");
	}	
}
