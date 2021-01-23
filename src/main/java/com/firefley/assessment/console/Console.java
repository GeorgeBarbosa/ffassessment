package com.firefley.assessment.console;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.firefley.assessment.dto.NumberGeneratorResultDTO;
import com.firefley.assessment.service.NumberGeneratorService;

@ShellComponent
public class Console {
 
	private final NumberGeneratorService numberGeneratorService;
	
	public Console(NumberGeneratorService numberGeneratorService) {
		this.numberGeneratorService = numberGeneratorService;
	}
	
	@ShellMethod(value = "Generate unsorted numbers, save in a file and then read it, sort and save in a new file.", key = {"generate", "gen"}, prefix = "Generate numbers")
	public void generateNumbers() {

		try {
			NumberGeneratorResultDTO result = this.numberGeneratorService.generateNumbers();
			printResult(result);
		} catch (Exception e) {
			printResult("Error trying to generate numbers. Please try again." + e.getMessage());
		}
	}
	
	private void printResult(Object object) {
		System.out.println(object);
	}
}
