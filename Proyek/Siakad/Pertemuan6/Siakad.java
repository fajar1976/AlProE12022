package Proyek.Siakad.Pertemuan6;

import java.util.Scanner;

public class Siakad {

    static mahasiwa[] mahasiswa = new mahasiwa[1000];
    static int jumlahData = 0;
    static boolean lanjut = true;

    // Main Method
    public static void main(String[] args) {
        clearScreen();

        Scanner input = new Scanner(System.in);
        while (lanjut) {
            System.out.println("\n-----------------");
            System.out.println("===== Siakad ====\n       Menu      ");
            System.out.println("-----------------");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Urutkan Data");
            System.out.println("4. Pencarian Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu : ");

            String menu = input.next();
            switch (menu) {
                case "1":
                    tambahData();
                    break;
                case "2":
                    tampilData();
                    break;
                case "3":
                    urutkanData();
                    break;
                case "4":
                    cariData();
                    break;
                case "5":
                    System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Silahkan pilih nomor menu yang tersedia!");
                    break;
            }

            lanjut = yat("Apakah anda ingin melanjutkan?");
        }

    }

    // Menambah Data Mahasiswa
    /**
     * 
     */
    public static void tambahData() {
        Scanner input = new Scanner(System.in);

        clearScreen();
        System.out.println("-----------------");
        System.out.println("---Tambah Data---");
        System.out.print("NIM : ");
        String nim = input.nextLine();
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.print("Prodi : ");
        String prodi = input.nextLine();
        mahasiswa[jumlahData] = new mahasiwa();
        mahasiswa[jumlahData].setNim(nim);
        mahasiswa[jumlahData].setNama(nama);
        mahasiswa[jumlahData].setProdi(prodi);
        jumlahData++;

    }

    // ya atau tidak
    public static boolean yat(String pesan) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + pesan + " (y/t) ");
        String pilih = input.next();
        while (!pilih.equalsIgnoreCase("y") && !pilih.equalsIgnoreCase("t")) {
            System.err.println("Pilihan tidak tersedia! ,pilih y atau t");
            System.out.println(pesan + " (y/t) ");
            pilih = input.next();
        }
        return pilih.equalsIgnoreCase("y");
    }

    // hapus layar
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Menampilkan Data Mahasiswa
    public static void tampilData() {
        clearScreen();
        System.out.println("-----------------");
        System.out.println("Berikut data Anda\n");
        System.out.println("NIM|  NAMA  | PRODI\n");
        int i = 0;
        while (i < jumlahData) {
            System.out.println(mahasiswa[i].getNim() + " | " +
                    mahasiswa[i].getNama() + " | " +
                    mahasiswa[i].getProdi());
            i++;
        }
    }

    // Menu Pegurutan Data Mahasiswa
    public static void urutkanData() {
        clearScreen();
        Scanner input = new Scanner(System.in);

        System.out.println("PILIH METODE PENGURUTAN");
        System.out.println("1. Exchange Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Quick Sort");
        System.out.println("4. Insertion Sort");
        System.out.println("5. Bubble Sort");
        System.out.println("6. Shell Sort");
        System.out.print("Pilih : ");
        int pilih = input.nextInt();
        switch (pilih) {
            case 1:
                exchangeSort();
                break;
            case 2:
                selectionSort();
                break;
            case 3:
                quickSort();
                break;
            case 4:
                insertionSort();
                break;
            case 5:
                bubbleSort();
                break;
            case 6:
                shellSort();
                break;
            default:
                System.err.println("\nPilihan tidak ada !\n Silahkan pilih nomor menu yang tersedia");
        }

    }

    // Exchange Sort
    public static void exchangeSort() {
        int i, j;
        mahasiwa temp;
        for (i = 0; i < jumlahData; i++) {
            for (j = i + 1; j < jumlahData; j++) {
                if (mahasiswa[i].getNim().compareTo(mahasiswa[j].getNim()) > 0) {
                    temp = mahasiswa[i];
                    mahasiswa[i] = mahasiswa[j];
                    mahasiswa[j] = temp;
                }
            }
        }
        System.out.println("Data berhasil diurutkan");
    }

    // Selection Sort
    public static void selectionSort() {
        int i, j, min;
        mahasiwa temp;
        for (i = 0; i < jumlahData - 1; i++) {
            min = i;
            for (j = i + 1; j < jumlahData; j++) {
                if (mahasiswa[j].getNim().compareTo(mahasiswa[min].getNim()) < 0) {
                    min = j;
                }
            }
            temp = mahasiswa[i];
            mahasiswa[i] = mahasiswa[min];
            mahasiswa[min] = temp;
        }
        System.out.println("Data berhasil diurutkan");
    }

    // Quick Sort
    public static void quickSort() {
        // quickSort(0, jumlahData - 1);
        // System.out.println("Data berhasil diurutkan");
    }

    // Insertion Sort
    public static void insertionSort() {
        int i, j;
        mahasiwa temp;
        for (i = 1; i < jumlahData; i++) {
            temp = mahasiswa[i];
            j = i;
            while ((j > 0) && (mahasiswa[j - 1].getNim().compareTo(temp.getNim()) > 0)) {
                mahasiswa[j] = mahasiswa[j - 1];
                j--;
            }
            mahasiswa[j] = temp;
        }
        System.out.println("Data berhasil diurutkan");
    }

    // Bubble Sort
    public static void bubbleSort() {
        int i, j;
        mahasiwa temp;
        for (i = 0; i < jumlahData - 1; i++) {
            for (j = 1; j < jumlahData - i; j++) {
                if (mahasiswa[j - 1].getNim().compareTo(mahasiswa[j].getNim()) > 0) {
                    temp = mahasiswa[j - 1];
                    mahasiswa[j - 1] = mahasiswa[j];
                    mahasiswa[j] = temp;
                }
            }
        }
        System.out.println("Data berhasil diurutkan");
    }

    // Shell Sort
    public static void shellSort() {
        int i, j, k, l;
        mahasiwa temp;
        for (i = jumlahData / 2; i > 0; i = i / 2) {
            for (j = i; j < jumlahData; j++) {
                for (k = j - i; k >= 0; k = k - i) {
                    if (mahasiswa[k + i].getNim().compareTo(mahasiswa[k].getNim()) >= 0) {
                        break;
                    } else {
                        temp = mahasiswa[k];
                        mahasiswa[k] = mahasiswa[k + i];
                        mahasiswa[k + i] = temp;
                    }
                }
            }
        }
        System.out.println("Data berhasil diurutkan");
    }

    public static void cariData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPILIH METODE PENCARIAN");
        System.out.println("1. Linear");
        System.out.println("2. Binary");
        System.out.print("Pilih : ");
        int pilih = input.nextInt();
        switch (pilih) {
            case 1:
                cariLinear();
                break;
            case 2:
                cariBinary();
                break;
            default:
                System.out.println("Silahkan pilih nomor yang ada!");
        }
    }

    public static void cariLinear() {

    }

    public static void cariBinary() {

    }
}