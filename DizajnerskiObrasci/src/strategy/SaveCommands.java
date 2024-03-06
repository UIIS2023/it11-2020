package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import command.CommandExecutor;
import mvc.DrawingModel;

public class SaveCommands implements Save{

	@Override
	public void save(Object obj, File file) {
		
		CommandExecutor cmd = (CommandExecutor)obj;
		ObjectOutputStream ous = null;
		try {
			ous = new ObjectOutputStream(new FileOutputStream(file));
			ous.writeObject(cmd.getOperations());
			ous.close();
		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}

}
