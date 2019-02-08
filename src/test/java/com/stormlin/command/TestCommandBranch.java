package com.stormlin.command;

import org.junit.Test;

public class TestCommandBranch {

    private CommandBranch commandBranch = new CommandBranch();

    @Test
    public void testUsage() {
        commandBranch.usage();
    }

    @Test
    public void testExecute() {
        String[] args = { "add", "titleTest", "fuckPrio" };
        commandBranch.execute(args);
    }

}
