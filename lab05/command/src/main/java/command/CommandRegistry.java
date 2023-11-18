package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.ConcurrentMap;

public class CommandRegistry {

	private final ObservableList<Command> commandStack = FXCollections
			.observableArrayList();

	private final ObservableList<Command> undoneCommandStack = FXCollections
			.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
	}

	public void redo() {
		if(!this.undoneCommandStack.isEmpty()) {
			Command latestUndoneCommand = this.undoneCommandStack.remove(this.undoneCommandStack.size() - 1);
			latestUndoneCommand.redo();
			this.commandStack.add(latestUndoneCommand);
		}
	}

	public void undo() {
		if (!this.commandStack.isEmpty()) {
			Command latestCommand = this.commandStack.remove(this.commandStack.size() - 1);
			latestCommand.undo();
			this.undoneCommandStack.add(latestCommand);
		}
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}

	public void clearUndoneCommandStack() {
		this.undoneCommandStack.clear();
	}
}
