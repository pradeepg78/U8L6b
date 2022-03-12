public class Main {
    public static void main(String[] args) {
        Encryptor encryptor1 = new Encryptor(2,9);
        String strToEncrypt1 = "I hate junior year.";
        System.out.println("Encrypted String: " + encryptor1.encryptMessage(strToEncrypt1));

        Encryptor encryptor2 = new Encryptor(3,4);
        String strToEncrypt2 = "Subway and Chipotle have the best food. ";
        System.out.println("Encrypted String: " + encryptor2.encryptMessage(strToEncrypt2));

        System.out.println("-------------------------------------------------------------------------------------------------");

        Encryptor encryptor3 = new Encryptor(2,9);
        String strToDecrypt1 = "In ihoart ey ejaur.AAAAAAAAAAAAAAAAA";
        System.out.println("Decrypted String: " + encryptor3.decryptMessage(strToDecrypt1));

    }
}
