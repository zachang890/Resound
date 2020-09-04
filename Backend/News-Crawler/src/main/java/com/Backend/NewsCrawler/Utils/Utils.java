package com.Backend.NewsCrawler.Utils;

public class Utils {
    //WEB SEARCH
    public static final String EMPTY_STRING = "";
    public static final String NEW_LINE = "\n";
    public static final String PYTHON_COMMAND = "python3";
    public static final String PATH_TO_SCRIPT = "src/main/java/com/Backend/NewsAggregator/PythonScripts/rake-headlines-bs4.py";
    public static final String PREFIX_SEARCH = "https://news.google.com/search?q=";
    public static final String SUFFIX_SEARCH = "&hl=en-US&gl=US&ceid=US%3Aen";

    //ERRORS
    public static final String CONNECTION_FAILED = "IOException thrown, connection failed";
    public static final String READ_LINE_FAILED = "IOException thrown, failed to read line";
    public static final String SHORT_INPUT = "Input length too short";

    //MISC
    public static final int SPLIT_THRESHOLD = 30;
    public static final int NEWS_DETAILS_FIELDS = 3;
}
