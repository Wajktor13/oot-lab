package command;

public interface Command {

	void execute();

	String getName();

	public void undo();

	public void redo();
}
