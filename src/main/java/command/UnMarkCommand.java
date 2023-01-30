package command;

import duke.Ui;
import task.Task;
import task.TaskList;

public class UnMarkCommand {
    private int number;
    public UnMarkCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    public void unmark() {
        try {
            Task curr = TaskList.get(number);
            curr.unmark();
            Ui.printUnmark(curr);
        } catch (Exception m){
            Ui.printWrongNumber();
            String s = Ui.readCommand();
            new UnMarkCommand(s).unmark();
        }
    }
}