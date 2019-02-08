package com.stormlin.command;

import org.junit.Test;

public class TestCommandAdd {

    @Test
    public void testUsage() {
        CommandAdd commandAdd = new CommandAdd();
        commandAdd.usage();
    }

    @Test
    public void testExecute() {
        String[] args = { "add", "testTitle", "fuck" };
        CommandAdd commandAdd = new CommandAdd();
        commandAdd.execute(args);
    }

}
