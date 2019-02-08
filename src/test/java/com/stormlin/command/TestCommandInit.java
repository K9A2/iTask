package com.stormlin.command;

import org.junit.Test;

public class TestCommandInit {

    private CommandInit commandInit = new CommandInit();

    @Test
    public void testExecute() {
        String[] args = { "init", "fuck" };
        commandInit.execute(args);
    }

}
