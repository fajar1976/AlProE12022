package Proyek.Siakad.Pertemuan6;

import java.util.Scanner;

public class Siakad {

    static mahasiwa[] mahasiswa = new mahasiwa[1000];
    static int jumlahData = 0;

    // Main Method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menu;
        do {
            System.out.println("-----------------");
            System.out.println("===== Siakad ====\n       Menu      ");
            System.out.println("-----------------");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Urutkan Data");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu : ");
            menu = input.nextInt();
            if (menu == 1) {
                tambahData();
            } else if (menu == 2) {
                tampilData();
            } else if (menu == 3) {
                urutkanData();
            }
        } while (menu != 4);
    }

    // Menambah Data Mahasiswa
    public static void tambahData() {
        Scanner input = new Scanner(System.in);
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

    // Menampilkan Data Mahasiswa
    public static void tampilData() {
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
                System.out.println("Pilihan tidak ada");
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
}