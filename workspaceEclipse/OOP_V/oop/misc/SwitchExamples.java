package oop.misc;

public class SwitchExamples
{
    private static String useOldSwitch(int days)
    {
        String result;
        switch(days)
        {
            case 0:
                result = "heute";
                break;
            case 1:
                result = "morgen";
                break;
            case 2:
                result = "übermorgen";
                break;
            case -1:
                result = "gestern";
                break;
            case -2:
                result = "vorgestern";
                break;
            default:
                if(days > 0)
                {
                    result = "in " + days + " Tagen";
                }
                else
                {
                    result = "vor " + -days + " Tagen";
                }
                break;
        }
        return result;
    }
    
    private static String useNewSwitch(int days)
    {
        String result;
        switch(days)
        {
            case 0 -> result = "heute";
            case 1 -> result = "morgen";
            case 2 -> result = "übermorgen";
            case -1 -> result = "gestern";
            case -2 -> result = "vorgestern";
            default -> 
            {
                if(days > 0)
                {
                    result = "in " + days + " Tagen";
                }
                else
                {
                    result = "vor " + -days + " Tagen";
                }
            }
        }
        return result;
    }

    private static String useSwitchExpression(int days)
    {
        String result = switch(days)
        {
            case 0 -> "heute";
            case 1 -> "morgen";
            case 2 -> "übermorgen";
            case -1 -> "gestern";
            case -2 -> "vorgestern";
            default -> 
            {
                if(days > 0)
                {
                    yield "in " + days + " Tagen";
                }
                else
                {
                    yield "vor " + -days + " Tagen";
                }
            }
        };
        return result;
    }
    
    public static void main(String[] args)
    {
        String daysString = useOldSwitch(-10);
        System.out.println(daysString);

        daysString = useNewSwitch(-1);
        System.out.println(daysString);
        
        daysString = useSwitchExpression(2);
        System.out.println(daysString);
    }
}
