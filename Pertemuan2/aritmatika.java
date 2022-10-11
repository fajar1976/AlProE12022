import java.util.scanner;

    class aritmatika{
        public static void main(String[] args){
            int x,y,hasil;

            scanner input = new scanner(System.in);
            System.out.print("Masukan nilai x : ");
            x = input.nextInt();
            System.out.print("Masukab nilai y : ");
            y = input.nextInt();

                hasil = x + y ;
                System.out.printIn("Hasil Penjumlahan " +x+ " + "+y" = ");
        }
    }