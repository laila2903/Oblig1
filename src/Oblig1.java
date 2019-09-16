import java.security.PublicKey;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

//Oppgave 1
    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int maks (int [] a){

        if (a.length < 1) {     //sjekker at tabellen ikke er tom
            throw new NoSuchElementException("Tabellen er tom!");
        }

        int max = a[0]; // Initierer variabel med størst verdi

        for (int i = 0; i< a.length-1; i++) {  //Bruker bobblestortering til å sammenlikne to og to tall ved siden av hverandre

            if (a[i] > a[i+1]) {
                bytt(a,a[i],a[i+1]);
                max = a [i+1];
            }
            else {
                max = a[i+1];
            }
        }
        return max;
    }

    public static int ombyttinger (int [] a){
        if (a.length < 1){     //sjekker at tabellen ikke er tom
            throw new NoSuchElementException("Tabellen er tom!");
        }

        int teller = 0; //instansierer telleren

        int max = a[0]; // Initierer variabel med størst verdi

        for (int i = 0; i< a.length-1; i++) {  //Bruker bobblestortering til å sammenlikne to og to tall ved siden av hverandre

            if (a[i] > a[i+1]) {
                bytt(a,a[i],a[i+1]);
                teller++;
                max = a [i+1];
            }
            else {
                max = a[i+1];
            }
        }
        return teller;
    }

    /**
     * 1) Det blir flest ombyttinger når det største tallet er først, da den må for hver runde bli "boblet" oppover, og du får n-1 ombytter.
     * 2) Det blir færrest ombyttinger når yallene er sortert i stigende rekkefølge da if-setningen alldri er sann, og "bobling" ikke skjer.
     * 3)Setning 1.1.6 a)  I en tabell med n (n > 1) forskjellige tall er i gjennomsnitt
     1/2 + 1/3 + 1/4 + . . . . + 1/n  av dem større enn det største av de foran.
     Det er åpenbart det samme om vi bruker n forskjellige tall eller tallene fra 1 til n. Dermed har vi vist (se ovenfor) at Setning 1.1.6 a) er sann for n = 2, 3 og 4. Det å vise at den er sann for alle n krever et induksjonsbevis. Det finner du, hvis du er interessert, i Avsnitt 1.1.13.
     Vi trenger en tilnærmingsverdi for summen i Setning 1.1.6 a). Det n-te harmoniske tall er definert som summen av de inverse heltallene fra 1 til n og betegnes med Hn :
     Hn  =  1  +  1/2  +  1/3  +  1/4  +  .  .  .  .  +  1/n
     La  log(n) være den naturlige logaritmen til n (dvs. logaritmen med e som grunntall). Det er kjent at forskjellen mellom Hn og log(n) nærmer seg en grense når n vokser. Grensen kalles Eulers konstant og er med 3 desimalers nøyaktighet lik 0,577. For store n kan vi derfor sette:
     Hn  ≈  log(n) + 0,577
     Summen i Setning 1.1.6 a) mangler 1-tallet. Den blir dermed lik Hn – 1.
     Setning 1.1.6 b)  I en tabell med n forskjellige tall (n stor) er i gjennomsnitt
     log(n) – 0,423 av dem større enn det største av tallene foran.
     */

    //Oppgave 2
    public static boolean verifisersortertarray (int [] a){ //metode som sjekker om tabellen er i stigende rekkefølge

        for (int i = 0; i<a.length-1; i++){
            if (a[i]>a[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static int antallUlikeSortert1 (int [] a) {

        int distelmnt = 0; //holder på returverdi

        int n = a.length;  //sjekker om arrayet er tom
        if (n==0){
            return distelmnt;
        }

        if (verifisersortertarray(a)){

            distelmnt = 1; // for at et array skal eksistere må den ha minst en eksplisitt verdi

            for (int i = 0; i < a.length-1; i++){
                if(a[i] != a[i+1]) {
                    distelmnt++;
                }
            }
        } else {
            throw new IllegalStateException("Arrayet må være sortert i stigende rekkefølge");
        }
        return distelmnt;
    }

    //Oppgave 3
    public static int antallUlikeSortert2 (int [] a){

        int distelmnt = 0; //holder på returverdi

        int n = a.length;  //sjekker om arrayet er tom
        if (n==0){
            return distelmnt;
        }

        for (int i = 0; i < a.length-1; i++){
            if(a[i] != a[i+1]) {
                distelmnt++;
            }
        }
        return distelmnt;
    }

    //Oppgave 4

    public static int partisjonering (int a [], int vs, int hs) {
        while (true){
            while (vs<=hs && a[hs] % 2 == 0) {
                hs--;
            }
            while (vs<=hs && a[vs] % 2 == 1){
                vs++;
            }
            if(vs<hs){
                bytt(a,vs++,hs--);
            }
            return vs;
        }
    }

    public static int partition (int [] a, int vs, int hs){

       int i = vs;
       int j = hs;
       int pivot = a[(vs+hs)/2];

       while (i<=j){
           while (a[i]<pivot){
               i++;
           }

           while (a[j]>pivot){
               j--;
           }

           if (i<=j){
             bytt(a,i,j);
           }
       }
       return i; 
    }

    public static void kvikksort (int [] a, int vs, int hs){
        if (vs < hs){
            int pi = partition(a,vs,hs);
            kvikksort(a,vs,pi-1);
            kvikksort(a,hs,pi+1);
        }
        
    }

    public static void delSortering (int [] a){
         int par = 0;
         int odd = 0;

         if (a.length == 0) {
             return;
         }

         partisjonering(a,0,a.length-1);

         for (int i = 0; i <a.length; i++){
             if (Math.floorMod(a[i],2)== 0){ //Bruker floorMod da negative tall kan også tastes inn og vil ha "negativ" modulu tilbake
                par = par + 1;
             }
             else {
                 odd = odd +1;
             }
         }
         kvikksort(a,0, odd-1);
         kvikksort(a,odd,a.length-1);
    }

    //Oppgave 5
        public static void rotasjon(char[] a){

            int n = a.length;
            if ((n < 2) || (n == 0)) {
                return;
            }
                                      
            char[] b = Arrays.copyOfRange(a, n - 1, n);
            for (int i = n - 1; i >= 1; i--) {
                a[i] = a[i - 1];
            }
            System.arraycopy(b, 0, a, 0, 1);
          }

    //Oppgave 6
    public static void rotasjon (char [] a, int k) {   // denne versonen har 2(n+d) tabelakkssseser men er mer effektiv hvis k er liten i forhold til n
        int n = a.length;
        if ((n < 2) || (n == 0)) {
            return;
        }
        if ((k %= n) < 0) k += n;

        char [] b = Arrays.copyOfRange(a,n-k,n);
        for (int i = n-1; i >= k; i--){
            a[i] = a[i-k];
        }
        System.arraycopy(b,0,a,0,k);
    }   //////sjekk effektiviteten her

    public static int gcd (int a, int b){
        return b == 0 ? a: gcd(b,a%b);
    }

    public static void rotasjon1 (char [] a, int k){   /// denne versjonen har 2n tabelakksseser, færre en den første men mer arbeid knyttet til hver akssess
        int n = a.length;
        if ((n < 2) || (n == 0)) {
            return;
        }
        if ((k %= n) < 0) k += n;

        int s = gcd(n,k);

        for (int syk = 0; syk < s; syk++){
            char verdi = a[syk];

            for (int i = syk -k, j=syk; i != syk; i-=k){
                if ( i<0) {
                    i += n;
                }
                a[j] = a[i];
                j = i;
            }

           a[syk+k] = verdi;
        }
    }

    //Oppgave 7
    public static String flett (String s, String t){

    }





    public static void main(String[] args) {
        char [] a = "ABCDEFGHIJKLMNOP".toCharArray();
        System.out.println(Arrays.toString(a));
        rotasjon(a,-5);
        System.out.println(Arrays.toString(a));
        rotasjon(a,10);
        System.out.println(Arrays.toString(a));
    }
}


