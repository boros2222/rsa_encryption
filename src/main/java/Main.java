import java.math.BigInteger;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(8089);
        BigInteger q = BigInteger.valueOf(6299);

        RSA rsa = new RSA(p, q);

        System.out.println(p + " is prime: " + rsa.fermatTest(p));
        System.out.println(q + " is prime: " + rsa.fermatTest(q));

        System.out.println("p = " + rsa.getP());
        System.out.println("q = " + rsa.getQ());
        System.out.println("n = " + rsa.getN());
        System.out.println("fi(" + rsa.getN() + ") = " + rsa.getFin());
        System.out.println("e = " + rsa.getE());
        System.out.println("d = " + rsa.getD());

        System.out.println("");

        try {
            BigInteger number = BigInteger.valueOf(2019);
            BigInteger encryptedNumber = rsa.encrypt(number);
            BigInteger decryptedNumber = rsa.decrypt(encryptedNumber);
            System.out.println("Number = " + number);
            System.out.println("Number encrypted = " + encryptedNumber);
            System.out.println("Number decrypted = " + decryptedNumber);

            System.out.println("");

            String text = "Lorem ipsum dolor sit amet";
            List<BigInteger> encryptedText = rsa.encryptText(text);
            String decryptedText = rsa.decryptText(encryptedText);
            System.out.println("Text = " + text);
            System.out.println("Text encrypted = \n" + encryptedText);
            System.out.println("Text decrypted = " + decryptedText);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
