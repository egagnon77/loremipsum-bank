package client.cli;

public class CommandLine {

    private CommandLine() {}

    public static String[] cleanArguments(String... receivedArgs) {
        if (receivedArgs == null) {
            return new String[0];
        }

        String[] cleanedArgs = new String[receivedArgs.length];

        int curIndex = 0;
        for (String curElement : receivedArgs) {

            // Remove spaces before and after and repetition
            String cleanedElement = curElement.trim().replaceAll(" +", " ");
            cleanedArgs[curIndex] = cleanedElement;
            curIndex++;
        }
        return cleanedArgs;
    }

}
