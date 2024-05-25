package Odev2_Cevabi_Stack_Yigit;

import java.util.Scanner;

/**
 *
 * @author Ahmet
 */
class stack {

    private String[] dizi;
    private int boyut, ustDeger;
    
    public stack(int boyut) {
        this.boyut = boyut;
        this.dizi = new String[this.boyut];
        this.ustDeger = -1;
    }

    public Boolean isFull() {
        return ustDeger == boyut - 1;
    }

    public Boolean isEmpty() {
        return ustDeger == -1;
    }

    public void push(String parantez) {
        String sonrakiDeger = null;
        if (!isFull()) {
            
            if(ustDeger!=-1){
                sonrakiDeger = dizi[ustDeger];
            }
            else if(ustDeger == -1){
                sonrakiDeger = null;
            }
                
            ustDeger++;
            dizi[ustDeger] = parantez;
            
            System.out.println(sonrakiDeger+" "+dizi[ustDeger]);
            
            if(sonrakiDeger!=null){
                if(dizi[ustDeger].equals(")") || dizi[ustDeger].equals("}") || dizi[ustDeger].equals("]"))
                    parantez_kontrol(dizi[ustDeger], sonrakiDeger);
            }     
        } else {
            System.out.println("Stack/Yığıt dolu!");
        }
    }

    public String pop() {
        if (!isEmpty()) {
            String tmpSilinen = dizi[ustDeger];
            ustDeger--;
            return tmpSilinen;
        } else {
            return null;
        }
    }

    public String top() {
        if (!isEmpty()) {
            return dizi[ustDeger];
        } else {
            return null;
        }
    }

    public void listele() {
        int i = ustDeger;
        if (!isEmpty()) {
            while (i != -1) {
                System.out.print(dizi[i] + " ");
                i--;
            }
        } else {
            System.out.println("Stack/Yığıt boş");
        }
    }

    public void parantez_kontrol(String ust, String sonraki) {

        boolean kapali_ust1 = false, kapali_ust2 = false, kapali_ust3 = false;
        boolean acik_sonraki1 = false, acik_sonraki2 = false, acik_sonraki3 = false;
        
        if (!isEmpty()) {
            switch (ust) {
                case ")":
                    kapali_ust1 = true;
                    break;
                case "}":
                    kapali_ust2 = true;
                    break;
                case "]":
                    kapali_ust3 = true;
                    break;
            }
            switch(sonraki){
                case "(":
                    acik_sonraki1 = true;
                    break;
                case "{":
                    acik_sonraki2 = true;
                    break;
                case "[":
                    acik_sonraki3 = true;
                    break;        
            }
        }
        //Eğer en üst değer ile bir sonraki değer birbirini tamamlıyorsa o iki parantez de stack den çıkarılır
        if (kapali_ust1 && acik_sonraki1 == true) {
            pop();
            pop();
        }
        if (kapali_ust2 && acik_sonraki2 == true) {
            pop();
            pop();
        }
        if (kapali_ust3 && acik_sonraki3 == true) {
            pop();
            pop();
        }
    }
    
    static Boolean karakterKontrol(char karakter){
        switch(karakter){
            case '(':
            case ')':
            case '{':
            case '}':
            case '[':
            case ']':
                return true;
            default:
                return false;
        }
    }
}

public class Soru1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean parantez_kontrol = false;
        
        System.out.println("| Sadece Parantezlerin Girişini Yapınız |");
        String parantezler = sc.nextLine();
        
        stack Stack = new stack(parantezler.length());
       
        for(char parantez : parantezler.toCharArray()){
            if(stack.karakterKontrol(parantez) == true){
                Stack.push(parantez+"");
            }
        }
        
        if(Stack.isEmpty())
            parantez_kontrol = true;
        else if(!Stack.isEmpty())
            parantez_kontrol = false;
        
        System.out.println("Parantez Doğruluk testi sonucu => "+parantez_kontrol);
        System.out.print("Stack de yer alan parantezler: ");
        Stack.listele();
        System.out.println();
    }
}
