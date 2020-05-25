import java.util.*; 

class Verschluesselung {
	
	public static void main(String[] args) {
		String operation = args[0];
		String message = args[1];
		String key = args[2];
		char[] ausgabe =  new char[message.length()];
		switch(operation) {
			case "verschluessel":
				ausgabe = encrypt(String2Char(message),String2Char(key));
				System.out.println("Die verschlüsselte Nachricht lautet: " + Arrays.toString(ausgabe));	//Die Array.toString() methode notw., weil sonst speicheradresse ausgegeben wird!!!
				break;
			case "entschluesseln":
				ausgabe = decrypt(String2Char(message),String2Char(key));
				System.out.println("Die entschlüsselte Nachricht lautet: "Arrays.toString(ausgabe)); //Die Array.toString() methode notw., weil sonst speicheradresse ausgegeben wird!!!
				break;
			default:
				System.out.println("Verwenden Sie bitte einen korrekten Operator!");
		}
	}
	
	public static char[] String2Char(String array) {
		char[] res = new char[array.length()];
		for( int j = 0; j < array.length() ; j++) {
			res[j] = array.charAt(j);
		}
		return res;
	}
	
	
	public static char[] encrypt(char[] message, char[] key) {
		char[] res =  new char[message.length];
		for(int i=0; i < message.length; i++) {
			// bei der Addition der ASCII werte können werte über 127 auftreten!
			res[i] = (char) ((message[i] + key[i%key.length])%128);		//key[] hat nur key.length einträge deswegen key[x%key.length]
		}
		return res;
	}
	
	public static char[] decrypt(char[] message, char[] key) {
		char[] result = new char[message.length];
		for(int i=0; i < message.length; i++) {
			// bei der Subtraktion können werte unter 0 auftreten vorsicht! mit + 128 diesen negativen bereich auf den positiven abbilden
			if(((message[i] - key[i%key.length])%128) < 0) {
				result[i] = (char) (((message[i]+128) - key[i%key.length])%128);	// Wenn bei der Subtrakt. < 0 rauskommt die message statisch nach oben verschieben
			} else {
				result[i] = (char) ((message[i] - key[i%key.length])%128);
			}
		}
		return result;
	}
}  