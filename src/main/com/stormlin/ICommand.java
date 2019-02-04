package main.com.stormlin;

public interface ICommand {

    void usage();
    void execute(String[] args);

}
