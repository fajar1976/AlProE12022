import java.util.Scanner;

    class aritmatika{
        public static void main(String[] args){
            int x,y,hasil;

            Scanner input = new Scanner(System.in);
            System.out.print("Masukan nilai x : ");
            x = input.nextInt();
            System.out.print("Masukab nilai y : ");
            y = input.nextInt();

                //operasi penjumlahan
                hasil = x + y ;
                System.out.println("Hasil Penjumlahan " +x+ " + "+y+" = "+hasil); 
                
                //operasi Pengurangan 
                hasil = x - y ;
                System.out.println("Hasil Penjumlahan " +x+ " - "+y+" = "+hasil);
                
                //operasi perkalian
                hasil = x * y ;
                System.out.println("Hasil Penjumlahan " +x+ " * "+y+" = "+hasil);
                
                //operasi pembagian
                hasil = x / y ;
                System.out.println("Hasil Penjumlahan " +x+ " / "+y+" = "+hasil);

                //operasi modulus
                hasil = x % y ;
                System.out.println("Hasil Penjumlahan " +x+ " % "+y+" = "+hasil);
        }
    }