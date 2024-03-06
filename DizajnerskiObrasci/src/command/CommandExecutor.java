package command;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor implements Serializable {

	private final List<Command> operations = new ArrayList<>();
	
	public void executeCommand(Command cmd) {
		operations.add(cmd);
		cmd.execute();
	}
	
	public List<Command> getOperations() {
		return operations;
	}
}
