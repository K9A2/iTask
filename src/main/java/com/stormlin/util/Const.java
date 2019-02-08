package com.stormlin.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Const {

    public static final String DEFAULT_FOLDER_PATH = "./.todo/";
    public static final String DEFAULT_FILE_PATH = "./.todo/todo.list";

    public static final String DEFAULT_BRANCH_NAME = "default";

    public static final ArrayList<String> validInputOfPriority = new ArrayList<>(
            Arrays.asList("high", "medium", "low"));
    public static final ArrayList<String> validInputOfHelp = new ArrayList<>(Arrays.asList("-h", "--help"));

}
