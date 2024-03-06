package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mvc.DrawingFrame;

public class SaveLog implements Save{

	@Override
	public void save(Object obj, File file) {
		
		DrawingFrame frame = (DrawingFrame)obj;
		BufferedWriter out = null;
		try {
			FileWriter myWriter = new FileWriter(file, true);
			out = new BufferedWriter(myWriter);
			frame.getTextArea().write(out);
			out.write("\n");
			out.close();
		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}	
		
	}

}
