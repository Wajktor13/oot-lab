package command;

import model.Account;
import model.Transaction;

public class RemoveTransactionCommand implements Command {
    private final Transaction transactionToRemove;

    private final Account account;

    public RemoveTransactionCommand(Transaction transactionToRemove, Account account) {
        this.transactionToRemove = transactionToRemove;
        this.account = account;
    }
    
    @Override
    public void execute() {
        this.account.removeTransaction(transactionToRemove);
    }

    @Override
    public String getName() {
        return "Removed transaction: " + transactionToRemove.toString();
    }

    @Override
    public void undo() {
        this.account.addTransaction(this.transactionToRemove);
    }

    @Override
    public void redo() {
        this.account.removeTransaction(this.transactionToRemove);
    }
}
