package Proyek.Siakad.UAS;

import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Siakad {

    static boolean lanjut = true;

    // Main Method
    public static void main(String[] args) throws IOException {
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
                    tampilData();
                    break;
                case "2":
                    tampilData();
                    break;
                case "3":
                    // urutkanData();
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

    public static void tambahData() throws IOException {
        Scanner input = new Scanner(System.in);

        FileWriter writer = new FileWriter("Proyek/Siakad/UAS/datamahasiswa.txt", true);
        BufferedWriter buffer = new BufferedWriter(writer);

        System.out.println("-----------------");
        System.out.println("---Tambah Data---");
        System.out.print("NIM : ");
        String nim = input.nextLine();
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.print("Fakultas : ");
        String fakultas = input.nextLine();
        System.out.print("Prodi : ");
        String prodi = input.nextLine();
        System.out.print("Tahun : ");
        String tahun = input.nextLine();

        // cek database
        String[] katakunci = { nim + "," + nama + "," + fakultas + "," + prodi + "," + tahun };
        System.out.println(Arrays.toString(katakunci));

        boolean tersedia = cekData(katakunci, false);
        // write database
        if (!tersedia) {
            clearScreen();

            String primary = nama.replaceAll("\\s", "").toLowerCase();
            String primaryKey = primary + "_" + nim;

            System.out.println("Berikut adalah data yang akan disimpan\n");

            System.out.println("Primary Key : " + primaryKey);
            System.out.println("NIM         : " + nim);
            System.out.println("Nama        : " + nama);
            System.out.println("Fakultas    : " + fakultas);
            System.out.println("Prodi       : " + prodi);
            System.out.println("Tahun       : " + tahun);

            boolean lanjut = yat("Simpan data?");

            if (lanjut) {
                buffer.write(
                        primaryKey + "," + nim + "," + nama + "," + fakultas + "," + prodi + "," + tahun + "\n");
                buffer.newLine();
                buffer.flush();
            }
        } else {
            System.out.println("Mahasiswa sudah terdaftar!");
            cekData(katakunci, true);
        }
        buffer.close();
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

    // bersihkan layar
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Menampilkan Data Mahasiswa
    public static void tampilData() throws IOException {

        FileReader dataInput;
        BufferedReader buffer;

        // koneksi ke file
        try {
            dataInput = new FileReader("Proyek/Siakad/UAS/datamahasiswa.txt");
            buffer = new BufferedReader(dataInput);
        } catch (Exception e) {
            System.err.println("Data tidak ditemukan!\nSilahkan tambah data dulu\n");
            tambahData();
            return;
        }

        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        String data = buffer.readLine();
        int noData = 0;
        while (data != null) {
            noData++;

            StringTokenizer st = new StringTokenizer(data, ",");
            System.out.printf("|%2d ", noData);
            st.nextToken();
            System.out.printf("|\t%4s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");

            data = buffer.readLine();
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");

    }

    // Menu Pegurutan Data Mahasiswa
    // public static void urutkanData() throws IOException {
    // clearScreen();
    // Scanner input = new Scanner(System.in);

    // System.out.println("PILIH METODE PENGURUTAN");
    // System.out.println("1. Exchange Sort");
    // System.out.println("2. Selection Sort");
    // System.out.println("3. Quick Sort");
    // System.out.println("4. Insertion Sort");
    // System.out.println("5. Bubble Sort");
    // System.out.println("6. Shell Sort");
    // System.out.print("Pilih : ");
    // int pilih = input.nextInt();
    // switch (pilih) {
    // case 1:
    // exchangeSort();
    // break;
    // case 2:
    // selectionSort();
    // break;
    // case 3:
    // quickSort();
    // break;
    // case 4:
    // insertionSort();
    // break;
    // case 5:
    // bubbleSort();
    // break;
    // case 6:
    // shellSort();
    // break;
    // default:
    // System.err.println("\nPilihan tidak ada !\n Silahkan pilih nomor menu yang
    // tersedia");
    // }

    // }

    // // Exchange Sort
    // public static void exchangeSort() {
    // int i, j;
    // mahasiwa temp;
    // for (i = 0; i < jumlahData; i++) {
    // for (j = i + 1; j < jumlahData; j++) {
    // if (mahasiswa[i].getNim().compareTo(mahasiswa[j].getNim()) > 0) {
    // temp = mahasiswa[i];
    // mahasiswa[i] = mahasiswa[j];
    // mahasiswa[j] = temp;
    // }
    // }
    // }
    // System.out.println("Data berhasil diurutkan");
    // }

    // // Selection Sort
    // public static void selectionSort() {
    // int i, j, min;
    // mahasiwa temp;
    // for (i = 0; i < jumlahData - 1; i++) {
    // min = i;
    // for (j = i + 1; j < jumlahData; j++) {
    // if (mahasiswa[j].getNim().compareTo(mahasiswa[min].getNim()) < 0) {
    // min = j;
    // }
    // }
    // temp = mahasiswa[i];
    // mahasiswa[i] = mahasiswa[min];
    // mahasiswa[min] = temp;
    // }
    // System.out.println("Data berhasil diurutkan");
    // }

    // // Quick Sort
    // public static void quickSort() {
    // // quickSort(0, jumlahData - 1);
    // // System.out.println("Data berhasil diurutkan");
    // }

    // // Insertion Sort
    // public static void insertionSort() {
    // int i, j;
    // mahasiwa temp;
    // for (i = 1; i < jumlahData; i++) {
    // temp = mahasiswa[i];
    // j = i;
    // while ((j > 0) && (mahasiswa[j - 1].getNim().compareTo(temp.getNim()) > 0)) {
    // mahasiswa[j] = mahasiswa[j - 1];
    // j--;
    // }
    // mahasiswa[j] = temp;
    // }
    // System.out.println("Data berhasil diurutkan");
    // }

    // // Bubble Sort
    // public static void bubbleSort() {
    // int i, j;
    // mahasiwa temp;
    // for (i = 0; i < jumlahData - 1; i++) {
    // for (j = 1; j < jumlahData - i; j++) {
    // if (mahasiswa[j - 1].getNim().compareTo(mahasiswa[j].getNim()) > 0) {
    // temp = mahasiswa[j - 1];
    // mahasiswa[j - 1] = mahasiswa[j];
    // mahasiswa[j] = temp;
    // }
    // }
    // }
    // System.out.println("Data berhasil diurutkan");
    // }

    // // Shell Sort
    // public static void shellSort() {
    // int i, j, k, l;
    // mahasiwa temp;
    // for (i = jumlahData / 2; i > 0; i = i / 2) {
    // for (j = i; j < jumlahData; j++) {
    // for (k = j - i; k >= 0; k = k - i) {
    // if (mahasiswa[k + i].getNim().compareTo(mahasiswa[k].getNim()) >= 0) {
    // break;
    // } else {
    // temp = mahasiswa[k];
    // mahasiswa[k] = mahasiswa[k + i];
    // mahasiswa[k + i] = temp;
    // }
    // }
    // }
    // }
    // System.out.println("Data berhasil diurutkan");
    // }

    public static void cariData() throws IOException {

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
        lanjut = yat("Apakah anda ingin melanjutkan?");
    }

    public static void cariLinear() throws IOException {

        // koneksi ke file
        try {
            File file = new File("Proyek/Siakad/Pertemuan6/datamahasiswa.txt");

        } catch (Exception e) {
            System.err.println("Data tidak ditemukan!\nSilahkan tambah data dulu\n");
            tambahData();
            return;
        }
        // keyword
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan Nama Mahasiswa :");
        String cariNama = input.nextLine();

        String[] katakunci = cariNama.split("\\s+");

        // cek data
        cekData(katakunci, true);
    }

    public static void cariBinary() {

    }

    // cek data
    public static boolean cekData(String[] katakunci, boolean tampilkan) throws IOException {

        FileReader file = new FileReader("Proyek/Siakad/UAS/datamahasiswa.txt");
        BufferedReader buffer = new BufferedReader(file);
        String data = buffer.readLine();
        boolean tersedia = false;
        int totalData = 0;

        if (tampilkan) {
            System.out.println("-----------------");
            System.out.println("Berikut data Siakad\n");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(
                    "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------");
        }

        while (data != null) {
            // cek kata kunci data
            tersedia = true;
            // System.out.println(data);
            // System.out.println(Arrays.toString(katakunci));
            for (String kata : katakunci) {
                tersedia = tersedia && data.toLowerCase().contains(kata.toLowerCase());

            }

            // menampilkan hasil
            if (tersedia) {
                if (tampilkan) {
                    totalData++;
                    StringTokenizer st = new StringTokenizer(data, ",");
                    System.out.printf("|%2d ", totalData);
                    st.nextToken();
                    System.out.printf("|\t%4s ", st.nextToken());
                    System.out.printf("|\t%-18s ", st.nextToken());
                    System.out.printf("|\t%-30s ", st.nextToken());
                    System.out.printf("|\t%-30s ", st.nextToken());
                    System.out.printf("|\t%-6s ", st.nextToken());
                    System.out.print("|\n");
                } else {
                    break;
                }
            }

            data = buffer.readLine();
        }
        if (tampilkan) {
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------");
        }

        return tersedia;
    }

}