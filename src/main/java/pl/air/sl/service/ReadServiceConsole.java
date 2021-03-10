package pl.air.sl.service;

import java.util.Scanner;

public class ReadServiceConsole implements ReadService{
	
	private Scanner in = new Scanner(System.in);

	@Override
	public String readString(String prompt) {
		System.out.println(prompt + ": ");
		String input = in.nextLine();
		
		return input;
	}

	@Override
	public Long readLong(String prompt) {
		String input = readString(prompt);
		try {
			Long data = Long.parseLong(input);
			return data;
		} catch (Exception e) {
			
			return null;
		}
	}

	@Override
	public Integer readInteger(String prompt) {
		
		String input = readString(prompt);
		try {
			Integer data = Integer.parseInt(input);
			return data;
		} catch (Exception e) {
			
			return null;
		}
	}

}
