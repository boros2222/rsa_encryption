import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger fin;
    private BigInteger e;
    private BigInteger d;

    public RSA(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.fin = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        this.e = getPublicExponent();
        this.d = getPrivateExponent();
    }

    public List<BigInteger> encryptText(String str) throws IllegalArgumentException, UnsupportedEncodingException {
        List<BigInteger> encrypted = new ArrayList<>();
        byte[] stringBytes = str.getBytes("UTF-8");

        for (byte b : stringBytes) {
            encrypted.add(encrypt(BigInteger.valueOf(b+128)));
        }

        return encrypted;
    }

    public String decryptText(List<BigInteger> encrypted) {
        byte[] stringBytes = new byte[encrypted.size()];

        for (int i = 0; i < encrypted.size(); i++) {
            BigInteger decrypted = decrypt(encrypted.get(i)).subtract(BigInteger.valueOf(128));
            stringBytes[i] = decrypted.byteValue();
        }

        return new String(stringBytes);
    }

    public BigInteger encrypt(BigInteger m) throws IllegalArgumentException {
        if (BigInteger.ZERO.compareTo(m) <= 0 && m.compareTo(this.n) < 0)
            return powerModulo(m, this.e, this.n);
        else
            throw new IllegalArgumentException("Encryption input must be (0 <= x) and (x < " + this.n.toString() + ")");
    }

    public BigInteger decrypt(BigInteger c) {
        return powerModulo(c, this.d, this.n);
    }

    public BigInteger getPublicExponent() {
        BigInteger e = BigInteger.valueOf(47);
        while(!isRelativePrime(e, this.fin)) {
            e = e.add(BigInteger.TWO);
        }

        return e;
    }

    public BigInteger getPrivateExponent() {
        BigInteger d = BigInteger.valueOf(2);
        while(e.multiply(d).mod(fin).compareTo(BigInteger.ONE) != 0) {
            d = d.add(BigInteger.ONE);
        }

        return d;
    }

    public boolean fermatTest(BigInteger p) {
        BigInteger a = BigInteger.TWO;

        while(!isRelativePrime(a, p))
            a = a.add(BigInteger.ONE);

        return powerModulo(a, p.subtract(BigInteger.ONE), p).compareTo(BigInteger.ONE) == 0;
    }

    public boolean isRelativePrime(BigInteger a, BigInteger b) {
        if (a.gcd(b).compareTo(BigInteger.ONE) == 0)
            return true;
        else
            return false;
    }

    public BigInteger powerModulo(BigInteger base, BigInteger exponent, BigInteger mod) {
        List<Boolean> binaryNumber = toBinaryList(exponent);
        List<BigInteger> baseDoubled = new ArrayList<>();

        for (int i = 0; i < binaryNumber.size(); i++) {
            if (i == 0)
                baseDoubled.add(base.mod(mod));
            else {
                BigInteger previous = baseDoubled.get(i-1);
                BigInteger doubled = previous.multiply(previous).mod(mod);
                baseDoubled.add(doubled);
            }
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < binaryNumber.size(); i++) {
            if (binaryNumber.get(i))
                result = result.multiply(baseDoubled.get(i));
        }

        return result.mod(mod);
    }

    public List<Boolean> toBinaryList(BigInteger number) {
        List<Boolean> binaryNumber = new ArrayList<>();
        BigInteger num = number;

        do {
            if (num.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0)
                binaryNumber.add(false);
            else
                binaryNumber.add(true);

            num = num.divide(BigInteger.TWO);
        } while (num.compareTo(BigInteger.ZERO) != 0);

        return  binaryNumber;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getFin() {
        return fin;
    }

    public void setFin(BigInteger fin) {
        this.fin = fin;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
}
