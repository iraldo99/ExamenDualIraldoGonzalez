package iraldoexamendual;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


public class Address {
    private PublicKey PK;
    private PrivateKey SK;
    private double balance;
    public String symbol = "EZI";

    public PublicKey getPK() {
        return PK;
    }

    public void setPK(PublicKey PK) {
        this.PK = PK;
    }

    public PrivateKey getSK() {
        return SK;
    }

    public void setSK(PrivateKey SK) {
        this.SK = SK;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public void generateKeyPair(){
        KeyPair pair;
        pair = GenSig.generateKeyPair();
        setPK(pair.getPublic());
        setSK(pair.getPrivate());
    }
    
    public void transferEZI(double EZI){
        this.balance += EZI;
    }
    
    
    public void addEZI(double EZI){
        this.balance += EZI;
    }
    
    public String toString(){
        String devuelto;
        devuelto = "PK = "+PK.hashCode()+"\nBalance = "+balance+
                " "+symbol;
        return devuelto;
    }
    
    public void send(TokenContract contract, double EZI){
        contract.payable(this.PK,EZI);
        
    }
    
}
