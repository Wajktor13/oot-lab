package command;

import model.Account;
import model.Transaction;

public class AddTransactionCommand implements Command {
    private final Transaction transactionToAdd;

    private final Account account;

    public AddTransactionCommand(Transaction transactionToAdd, Account account) {
        this.transactionToAdd = transactionToAdd;
        this.account = account;
    }
    @Override
    public void execute() {
        this.account.addTransaction(transactionToAdd);
    }

    @Override
    public String getName() {
        return "New transaction: " + transactionToAdd.toString();
    }

    @Override
    public void undo() {
        this.account.removeTransaction(this.transactionToAdd);
    }

    @Override
    public void redo() {
        this.account.addTransaction(this.transactionToAdd);
    }
}
