package oop.reflection;

import java.lang.reflect.*;
//import oop.service.Dummy;

/*Demo:
a) Klasse oop.reflection.Dummy in diesem Modul
a1) Ohne setAccessible(true) kein Zugriff => Exception
a2) Mit setAccessible(true) geht es
b) Klasse oop.service.Dummy in Modul oop.service
b1) geht nicht => erst Modulepath setzen
b2) wie a1
b3) Mit setAccessible(true) geht es auch nicht
b4) Package muss neben exports auch mit opens freigegeben werden, dann geht es
b5) Exports kann entfernt werden, wenn man das Class-Objekt über Class.forName beschafft
    und das Objekt davon entsprechend auch über Reflection erzeugt
b6) statt opens kann auch open module geschrieben werden
*/

public class PrivateAttributeChanger
{
    public static void main(String[] args)
    {
        try
        {
            Class<Dummy> classDesc = Dummy.class;
            Dummy object = new Dummy(10);
            //Class<?> classDesc = Class.forName("oop.service.Dummy");
            //Object object = classDesc.getDeclaredConstructor(int.class).newInstance(20);
            System.out.println(classDesc.getName() + " in Modul " + classDesc.getModule().getName());
            Field[] attributes = classDesc.getDeclaredFields();
            for(Field attribute: attributes)
            {
                if((attribute.getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE &&
                   attribute.getType() == int.class)
                {
                    System.out.println("privates Attribut vom Typ int gefunden: " + 
                                       attribute.getName());
                    //attribute.setAccessible(true);
                    System.out.println("Wert: " + attribute.getInt(object));
                    attribute.setInt(object, 100);
                    System.out.println("Wert jetzt: " + attribute.getInt(object));
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
