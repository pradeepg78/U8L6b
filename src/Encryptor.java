public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int i = 0;
        for (int r = 0; r < letterBlock.length; r++)
        {
            for (int c = 0; c < letterBlock[r].length; c++)
            {
                if (i < str.length())
                {
                    letterBlock[r][c]  = str.substring(i, i + 1);
                    i++;
                }
                else
                {
                    letterBlock[r][c] = "A";
                }

            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String message = "";
        for (int c = 0; c < letterBlock[0].length; c++)
        {
            for (int r = 0; r < letterBlock.length; r++)
            {
                message += letterBlock[r][c];
            }
        }
        return message;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        int maxBoxes = numRows * numCols;
        String encryption = "";
        int firstIndex = 0;
        int length = 0;

        //no empty boxes, aka all boxes can be filled
        if(message.length() % maxBoxes == 0) length = message.length() /maxBoxes;

        //add the "+ 1"
        else length = message.length() / maxBoxes +1;

        for (int i = 0; i < length; i++)
        {
            if(firstIndex + maxBoxes < message.length())
            {
                fillBlock(message.substring(firstIndex, firstIndex + maxBoxes));
                encryption += encryptBlock();
                firstIndex = firstIndex + maxBoxes;
            }
            else
            {
                fillBlock(message.substring(firstIndex));
                encryption += encryptBlock();
            }
        }
        return encryption;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the ???encryption key??? that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        int maxBoxes = numRows * numCols;
        int length = encryptedMessage.length() / maxBoxes;
        String decryption = "";
        String str = "";
        int index = 0;

        rearrange(encryptedMessage);
        for (int i = 0; i < length; i++)
        {
            str = encryptedMessage.substring(index, index + maxBoxes);
            index += maxBoxes;
            rearrange(str);
            for (int r = 0; r < letterBlock.length; r++)
            {
                for (int c  = 0; c < letterBlock[r].length; c++)
                {
                    decryption += letterBlock[r][c];
                }
            }
        }

        int cut = decryption.length();
        for (int i = decryption.length() - 1; i > 0; i--)
        {
            if (decryption.substring(i, i + 1).equals("A")) cut--;
            else break;
            decryption = decryption.substring(0, cut);
        }
        return decryption;
    }

    // helper method to rearrange str in column - major order
    private void rearrange(String str)
    {
        int index = 0;
        String character = "";
        for (int c = 0; c < letterBlock[0].length; c++) {
            for (int r = 0; r < letterBlock.length; r++) {
                if (index + 1 <= str.length()) {
                    character = str.substring(index, index + 1);
                    letterBlock[r][c] = character;
                    index++;
                }
            }
        }
    }
}
