package main.com.stormlin;

public class CommandAdd implements ICommand {

    public void usage() {
        System.out.println("Use this command to add new task.");
    }

    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Command add takes exactly 2 arguments: <title> <priority>.");
            usage();
            return;
        }

        String title = args[1];
        String priority = args[2];

        TodoList todoList = Util.readFromListFile(Const.DEFAULT_FILE_PATH);
        if (todoList == null) {
            System.out.println(String.format("Could not load the list file: %s", Const.DEFAULT_FILE_PATH));
            return;
        }
        Branch currentBranch = todoList.getCurrentBranch();
        if (currentBranch != null) {
            currentBranch.addTask(title, priority);
        }
        Util.saveToListFile(todoList);
    }

}
