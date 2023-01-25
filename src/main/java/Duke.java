import java.io.*;
import java.util.*;
public class Duke {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private static final ArrayList<Task> al = new ArrayList<>();
    private static final StringBuilder sb = new StringBuilder();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void welcome() {
        String welcome = "Welcome hooman!\n" +
                "Wat u want to do today?\n";
        System.out.println(divider + welcome + divider);
    }

    public static void bye() {
        System.out.println(divider + "Ah..... okkkk nehmind. GO. BYE. :)\n" + divider);
    }

    public static void printList() {
        ListIterator<Task> li = al.listIterator();
        if (!li.hasNext()) {
            System.out.println(divider + "List is empty.......\n" + divider);
        } else {
            int count = 1;
            System.out.println(divider + "Hurry up and finish these tasks:");
            while (li.hasNext()) {
                Task curr = li.next();
                System.out.print(curr);
                count++;
            }
            System.out.println(divider);
        }
    }
    public static void printDefault(Task t) {
        System.out.println(divider + "Aite letsgetit you added:\n" + t.toString()  +
                "currently you have " + t.numberTask() + " tasks\n" + divider);
    }

    public static void mark(String i) throws Exception{
        int cint = Integer.parseInt(i) - 1;
        try {
            Task curr = al.get(cint);
            curr.mark();
            System.out.println(divider + "Congrats this has been d:\n"
                    + curr + "\n down, Leskooo!\n" + divider);
        } catch (Exception m){
            System.out.println("Number entered out of range, type the number again");
            String s = br.readLine();
            mark(s);
        }
    }

    public static void unmark(String i) throws Exception{
        int cint = Integer.parseInt(i) - 1 ;
        try {
            Task curr = al.get(cint);
            curr.unmark();
            System.out.println(divider + "Alright, new task:\n" + curr
                    + "\nWe can do dis!\n" + divider);
        } catch (Exception m) {
            System.out.println("Number entered out of range, type the number again");
            String s = br.readLine();
            unmark(s);
        }
    }

    public static void delete(String i) throws Exception{
        int cint = Integer.parseInt(i) - 1;
        try {
            Task curr = al.get(cint);
            curr.minus();
            al.remove(cint);
            System.out.println(divider + "Alright, deleted task:\n" + curr
                    + "\n" + curr.numberTask() + " tasks left!\n" + divider);
        } catch (Exception m){
            System.out.println("Number entered out of range, type the number again");
            String s = br.readLine();
            delete(s);
        }
    }

    public static Event createEvent(String[] splited) {

        boolean isName = true;
        boolean isStart = false;
        boolean isEnd = false;
        String n = " ", s = " ", e = " ";
        for (int i = 1; i < splited.length; i++) {
            if (isName) {
                if (!splited[i].equalsIgnoreCase("/from")) {
                    sb.append(splited[i]);
                } else {
                    isStart = true;
                    n = sb.toString();
                    sb.setLength(0);
                    isName = false;
                }
            } else if (isStart) {
                if (!splited[i].equalsIgnoreCase("/to")) {
                    sb.append(splited[i]);
                } else {
                    isEnd = true;
                    s = sb.toString();
                    sb.setLength(0);
                    isStart = false;
                }
            } else if (isEnd) {
                sb.append(splited[i]);
            }
            if (i + 1 != splited.length) {
                sb.append(" ");
            }
        }

        e = sb.toString();
        sb.setLength(0);
        return new Event(n, s, e);
    }

    public static Deadline createDeadline(String[] splited) {
        boolean isName = true;
        String n = " ", e = " ";
        for (int i = 1; i < splited.length; i++) {
            if (isName) {
                if (!splited[i].equalsIgnoreCase("/by")) {
                    sb.append(splited[i]);
                } else {
                    n = sb.toString();
                    sb.setLength(0);
                    isName = false;
                }
            } else {
                sb.append(splited[i]);
            }
            if (i + 1 != splited.length) {
                sb.append(" ");
            }
        }
        e = sb.toString();
        sb.setLength(0);
        return new Deadline(n, e);
    }

    public static Todo createTodo(String[] splited) throws Exception{
        if (splited.length == 1) {
            System.out.println("What do u need to do ah? u never write.");
            String s = br.readLine();
            Todo t = new Todo(s);
            sb.setLength(0);
            return t;
        } else {
            for (int i = 1; i < splited.length; i++) {
                sb.append(splited[i]);
                if (i + 1 != splited.length) {
                    sb.append(" ");
                }
            }
            Todo t = new Todo(sb.toString());
            sb.setLength(0);
            return t;
        }
    }
    public static void main(String[] args) throws Exception {
        welcome();
        String in = br.readLine();
        String[] splited = in.split(" ");
        String bye = "bye";
        while (!in.equalsIgnoreCase(bye)) {
            switch (splited[0].toUpperCase()) {
                case "LIST":
                    printList();
                    break;
                case "MARK":
                    mark(splited[1]);
                    break;
                case "UNMARK":
                    unmark(splited[1]);
                    break;
                case "TODO" :
                    Todo t = createTodo(splited);
                    al.add(t);
                    printDefault(t);
                    break;
                case "EVENT" :
                    Event e = createEvent(splited);
                    al.add(e);
                    printDefault(e);
                    break;
                case "DEADLINE" :
                    Deadline d = createDeadline(splited);
                    al.add(d);
                    printDefault(d);
                    break;
                case "DELETE" :
                    delete(splited[1]);
                    break;
                default:
                    System.out.println(divider+ "I have no idea what is going on, try again?\n" + divider);
                    break;

            }
            in = br.readLine();
            splited = in.split(" ");
        }
        bye();
    }
}
