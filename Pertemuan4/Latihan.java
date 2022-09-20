package Github.AlProE12022.Pertemuan4;

/*Latihan soal piramida bintang dan flowchart
* Nama : Fajar Pahmi Padilah
* Kelas : E1-PTI
* tanggal : 20-09-2022
*/
import java.util.Scanner;

public class Latihan {
        public static void main(String[] args) {
            
            int i,j,k, bintang;
           
    
            try (Scanner input = new Scanner(System.in)) {
                System.out.print("masukan baris : ");
                bintang = input.nextInt();
            }
            
            
            i=1;
                while(i<=bintang){
                k=bintang;
                
                    while(k>i){
                        System.out.print("  ");
                        k--;
                }
                j=1;
                    while(j<=i){
                    System.out.print("* ");
                    j++;
                }
                i++;
                System.out.println();
            }
                
        }
    }

