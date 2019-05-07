import java.math.BigInteger;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 157 digits prime number
        BigInteger p = new BigInteger(
        "6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151"
        );

        // 183 digits prime number
        BigInteger q = new BigInteger(
        "531137992816767098689588206552468627329593117727031923199444138200403559860852242739162502265229285668889329486246501015346579337652707239409519978766587351943831270835393219031728127"
        );

        RSA rsa = new RSA(p, q);

        System.out.println(p + "\nis prime: " + rsa.fermatTest(p));
        System.out.println(q + "\nis prime: " + rsa.fermatTest(q));

        System.out.println("p = " + rsa.getP());
        System.out.println("q = " + rsa.getQ());
        System.out.println("n = " + rsa.getN());
        System.out.println("fi(n) = " + rsa.getFin());
        System.out.println("e = " + rsa.getE());
        System.out.println("d = " + rsa.getD());

        System.out.println("");

        try {
            BigInteger number = BigInteger.valueOf(2019);
            System.out.println("Number = " + number);
            BigInteger encryptedNumber = rsa.encrypt(number);
            System.out.println("Number encrypted = " + encryptedNumber);
            BigInteger decryptedNumber = rsa.decrypt(encryptedNumber);
            System.out.println("Number decrypted = " + decryptedNumber);

            System.out.println("");

            String text = "Lorem ipsum dolor sit amet";
            System.out.println("Text = " + text);
            List<BigInteger> encryptedText = rsa.encryptText(text);
            System.out.println("Text encrypted = ");
            for (BigInteger bi : encryptedText)
                System.out.println(bi);
            String decryptedText = rsa.decryptText(encryptedText);
            System.out.println("Text decrypted = " + decryptedText);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
