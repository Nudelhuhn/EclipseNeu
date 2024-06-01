
module modul_2
{
	exports package2;
	
//	requires modul_1;
}

/*
 * Aufgabe nicht möglich
 * - setzen des Modulpaths hatte keine Auswirkungen
 * - modul_1 requires modul_2, modul_2 requires modul_1 führt zur Fehlermeldung
 * "Cycle exists in module dependencies, Module ModulB requires itself via ModulA"
 * => jedes Modul wartet auf anderes Modul um geladen zu werden
 * - requires Anweisungen nur einzeln möglich, dh entweder M1 requires M2 oder vice versa,
 * ansonsten kann eine von den importierten Klassen nicht aufgelöst (resolved) werden 
 */