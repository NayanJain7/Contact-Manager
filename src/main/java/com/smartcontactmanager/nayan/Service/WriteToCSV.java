package com.smartcontactmanager.nayan.Service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.smartcontactmanager.nayan.Entity.Contact;

public class WriteToCSV {
	
	public static void writeToCsv(PrintWriter writer,List<Contact> contact) {
		
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name","Email","Phone","Work"));)
			{
			
				for (Contact c : contact) {
					
			
					List<String> data = Arrays.asList(
								c.getName(),
								c.getEmail(),
								c.getPhone(),
								c.getWork()
							);
					
					csvPrinter.printRecords(data);
				}
				
				csvPrinter.flush();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
