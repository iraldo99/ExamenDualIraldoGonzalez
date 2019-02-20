/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iraldoexamendual;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author Dual
 */
public class TokenContract {
    private String name;
    private String symbol;
    private double totalSupply;
    private PublicKey owner;
    private double price = 5;
    
    private static ArrayList <PublicKey> balancesKeys = new ArrayList<>();
    private static ArrayList <Double> balances = new ArrayList<>();

    
    
    public TokenContract(Address address) {
        
    }

    public PublicKey getOwner() {
        return owner;
    }

    public void setOwner(PublicKey owner) {
        this.owner = owner;
    }
    
    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String symbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double totalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }
    
    public void addOwner(PublicKey PK, double cant){
        boolean esUnico = true;
        for (int i = 0; i < balances.size(); i++) {
            if(balancesKeys.get(i)==PK){
                esUnico = false;
            }
            
        }
        if(esUnico){
            setOwner(PK);
            setTotalSupply(cant);
            
            balancesKeys.add(PK);
            balances.add(cant);
        }
        
    }
    
    public int numOwners(){
        return this.balances.size();
    }
    
    public double balanceOf(PublicKey owner){
        int IDowner = getId(owner);
        
        return balances.get(IDowner);
        
        
    }
    
    public int getId(PublicKey pkey){
        
        for (int i = 0; i < balances.size(); i++) {
            if(balancesKeys.get(i)==pkey){
                return i;
            }
            
        }
        balancesKeys.add(pkey);
        balances.add(0d);
        
        return balances.size()-1;
    }
    
    public void transfer(PublicKey recipient, Double units){
        int IDrecipient = getId(recipient);
        int IDsender = getId(this.owner);
        
        if(balances.get(IDsender) >= units){
            balances.set(IDrecipient,(balances.get(IDrecipient) + units));
            balances.set(IDsender, (balances.get(IDsender) - units));
        
        }
        
    }
    
    public void transfer(PublicKey sender, PublicKey recipient, Double units){
        int IDsender = getId(sender);
        int IDrecipient = getId(recipient);
        
        if(balances.get(IDsender) >= units){
            balances.set(IDrecipient,(balances.get(IDrecipient) + units));
            balances.set(IDsender, (balances.get(IDsender) - units));
        
        }
        
    }
    
    public void owners(){
        for (int i = 0; i < balancesKeys.size(); i++) {
            if(i!=getId(this.owner)){
                System.out.println("Owner: "+balancesKeys.get(i).hashCode()+
                    " "+balances.get(i)+" "+symbol);
            }
            
            
        }
    }
    
    public double totalTokensSold(){
        return this.totalSupply-balances.get(getId(this.owner));
    }
    
    public void payable(PublicKey recipient, Double enziniums){
        int IDsender = getId(this.owner);
        int IDrecipient = getId(recipient);
        
        if(this.price>enziniums){
            //NADA PORQUE NO LLEGA A LA CANTIDAD NECESARIA
        }else{
            //num % num = resto
            Double cantComprada = (enziniums/this.price);
            int cant = cantComprada.intValue();
            
            balances.set(IDrecipient,balances.get(IDrecipient)+(cantComprada-enziniums%this.price));
            double sobrante = enziniums - cant*this.price;
            
            
            
        }
            
        
    }
    
}
