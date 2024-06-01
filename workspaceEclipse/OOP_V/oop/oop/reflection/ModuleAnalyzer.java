package oop.reflection;

public class ModuleAnalyzer
{
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("arguments: list of class names");
            return;
        }
        for(String className: args)
        {
            analyze(className);
            System.out.println("=".repeat(40));
        }
    }

    private static void analyze(String className)
    {
        try
        {
            String headline = "Module of " + className + ":";
            System.out.println(headline);
            System.out.println("-".repeat(headline.length()));

            Class<?> c = Class.forName(className);
            Module mod = c.getModule();
            
            System.out.print("  module: ");
            System.out.println(mod.getName());
            System.out.print("  named: ");
            System.out.println(mod.isNamed());
            System.out.println("  packages: ");
            for(String pack: mod.getPackages())
            {
                String exportedString = mod.isExported(pack)? " (exportiert)" : " (nicht exportiert)";
                System.out.println("      " + pack  + exportedString);
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(className + ": not found");
        }
    }
}
