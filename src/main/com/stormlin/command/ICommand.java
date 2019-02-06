package main.com.stormlin.command;

public interface ICommand {

    void usage();
    void execute(String[] args);

}
