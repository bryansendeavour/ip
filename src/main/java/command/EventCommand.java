package command;

import duke.Ui;
import task.Event;
import task.TaskList;

public class EventCommand {
    private String[] inputs;
    private static StringBuilder strBuild = new StringBuilder();

    public EventCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public void create() {
        boolean isName = true;
        boolean isStart = false;
        boolean isEnd = false;
        String n = " ", s = " ", e = " ";
        for (int i = 1; i < inputs.length; i++) {
            if (isName) {
                if (!inputs[i + 1].equalsIgnoreCase("/from")) {
                    strBuild.append(inputs[i]);
                    strBuild.append(" ");
                } else {
                    isStart = true;
                    strBuild.append(inputs[i]);
                    n = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                    i++;
                }
            } else if (isStart) {
                if (!inputs[i + 1].equalsIgnoreCase("/to")) {
                    strBuild.append(inputs[i]);
                    strBuild.append(" ");
                } else {
                    isEnd = true;
                    strBuild.append(inputs[i]);
                    s = strBuild.toString();
                    strBuild.setLength(0);
                    isStart = false;
                    i++;
                }
            } else if (isEnd) {
                strBuild.append(inputs[i]);
                if (i + 1 != inputs.length) {
                    strBuild.append(" ");
                }
            }
        }
        e = strBuild.toString();
        strBuild.setLength(0);
        Event event = new Event(n, s, e, false);
        TaskList.addToList(event);
        Ui.printDefault(event);
    }
}
