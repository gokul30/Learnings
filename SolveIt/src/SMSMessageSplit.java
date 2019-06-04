import java.util.Arrays;
import java.util.List;

/*
Given a character limit and a message, split the message up into chunks without cutting words and calculate the number of message 
need to be sent,

For example when sending the SMS
"This is a real short message"
with char limit 12

Output : 3

The message is splitted into 
This is a
real short
message
*/

public class SMSMessageSplit {

    public static void main(final String[] args) {

        //oddValueOut();
        System.out.println(getMessageCount("This is a real short message", 12));
    }
    
    public static Integer getMessageCount(final String message, final Integer messageLength) {
        Integer result = 0;
        final List<String> inputMessageList = Arrays.asList(message.split(" "));
        String tempMessage = "";
        String tempMessage1 = null;
        Integer count = 0;
        for (final String inputMessage : inputMessageList) {
            count++;
            if (inputMessage.length() > messageLength) {
                result = -1;
                break;
            } else {
                tempMessage = "".equals(tempMessage) ? inputMessage : tempMessage.concat(" ").concat(inputMessage);
                if (tempMessage.length() == messageLength) {
                    result++;
                    tempMessage = "";
                    tempMessage1 = null;
                } else if (tempMessage.length() > messageLength) {
                    result++;
                    tempMessage = inputMessage;
                    tempMessage1 = null;
                }
                if (inputMessageList.size() == count) {
                    tempMessage1 = tempMessage;
                }
            }
            System.out.println(tempMessage);
            System.out.println(tempMessage1);
        }
        System.out.println(tempMessage1);
        result = tempMessage1 != null && !"".equals(tempMessage1.trim()) ? result + 1 : result;

        return result;
    }
}
