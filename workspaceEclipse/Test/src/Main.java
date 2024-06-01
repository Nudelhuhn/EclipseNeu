class CallChecker {

    public static void checkCaller(String expectedClassName, String expectedMethodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().equals(expectedClassName) && element.getMethodName().equals(expectedMethodName)) {
                return; // Der erwartete Aufrufer wurde gefunden, kehre normal zurück.
            }
        }

        // Wenn die erwartete Methode nicht gefunden wurde, werfe eine Ausnahme.
        throw new IllegalStateException("Diese Methode muss über " + expectedClassName + "." + expectedMethodName + "() aufgerufen werden.");
    }
}

class AccessControl {
    
    public void verifyAccess() {
        // Führe Zugriffsüberprüfung durch und rufe dann die sensitive Methode auf.
        SensitiveOperation sensitiveOperation = new SensitiveOperation();
        sensitiveOperation.performSensitiveTask();
    }
}

class SensitiveOperation {

    public void performSensitiveTask() {
        // Überprüfe, ob diese Methode über AccessControl.verifyAccess() aufgerufen wurde.
        CallChecker.checkCaller("AccessControl", "verifyAccess");

        // Führe die sensitive Aufgabe durch.
        System.out.println("Sensitive task performed.");
    }
}

public class Main {
    public static void main(String[] args) {
        AccessControl accessControl = new AccessControl();
        
        try {
            // Richtiger Aufruf: Kein Fehler sollte auftreten.
            accessControl.verifyAccess();
        } catch (IllegalStateException e) {
            System.err.println("Fehler: " + e.getMessage());
        }

        try {
            // Falscher Aufruf: Dies wird eine IllegalStateException werfen.
            SensitiveOperation sensitiveOperation = new SensitiveOperation();
            sensitiveOperation.performSensitiveTask();
        } catch (IllegalStateException e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }
}
