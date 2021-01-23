package com.firefley.assessment.dto;

public class NumberGeneratorResultDTO {

	private String unsortedFileName;
	private String sortedFileName;
	private int generatedNumbersSize;
	
	public NumberGeneratorResultDTO(String unsortedFileName, String sortedFileName, int generatedNumbersSize) {
		super();
		this.unsortedFileName = unsortedFileName;
		this.sortedFileName = sortedFileName;
		this.generatedNumbersSize = generatedNumbersSize;
	}

	public String getUnsortedFileName() {
		return unsortedFileName;
	}

	public void setUnsortedFileName(String unsortedFileName) {
		this.unsortedFileName = unsortedFileName;
	}

	public String getSortedFileName() {
		return sortedFileName;
	}

	public void setSortedFileName(String sortedFileName) {
		this.sortedFileName = sortedFileName;
	}

	public int getGeneratedNumbersSize() {
		return generatedNumbersSize;
	}

	public void setGeneratedNumbersSize(int generatedNumbersSize) {
		this.generatedNumbersSize = generatedNumbersSize;
	}

	@Override
	public String toString() {
		return new StringBuilder().
		append("Result {")
				.append(System.lineSeparator()).append(" ".repeat(15))
				.append("unsortedFileName=").append(unsortedFileName).append(",")
				.append(System.lineSeparator()).append(" ".repeat(15))
				.append("sortedFileName=").append(sortedFileName).append(",")
				.append(System.lineSeparator()).append(" ".repeat(15))
				.append("generatedNumbersSize=").append(generatedNumbersSize)
				.append("}").toString();
	}
	
}
