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
            System.out.println("5. Hapus Data");
            System.out.println("6. Edit Data");
            System.out.println("7. UAS");
            System.out.println("8. Keluar");
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
                    urutkanData();
                    break;
                case "4":
                    cariData();
                    break;
                case "5":
                    hapusData();
                    break;
                case "6":
                    editData();
                    break;
                case "7":
                    UAS();
                    break;
                case "8":
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
                        primaryKey + "," + nim + "," + nama + "," + fakultas + "," + prodi + "," + tahun);
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
        System.out.print("\n" + pesan + " (y/t) ");
        String pilih = input.next();
        while (!pilih.equalsIgnoreCase("y") && !pilih.equalsIgnoreCase("t")) {
            System.err.println("Pilihan tidak tersedia! ,pilih y atau t");
            System.out.print(pesan + " (y/t) ");
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
            System.out.printf("|\t%-11s ", st.nextToken());
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
    public static void urutkanData() throws IOException {

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

    // exchange sort
    public static void exchangeSort() throws IOException {

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

        String data = buffer.readLine();
        String[] dataMahasiswa = new String[1000];
        int i = 0;
        while (data != null) {
            dataMahasiswa[i] = data;
            data = buffer.readLine();
            i++;
        }

        // sorting
        for (int j = 0; j < i; j++) {
            for (int k = j + 1; k < i; k++) {
                StringTokenizer st1 = new StringTokenizer(dataMahasiswa[j], ",");
                StringTokenizer st2 = new StringTokenizer(dataMahasiswa[k], ",");
                st1.nextToken();
                st2.nextToken();
                String nim1 = st1.nextToken();
                String nim2 = st2.nextToken();
                if (nim1.compareTo(nim2) > 0) {
                    String temp = dataMahasiswa[j];
                    dataMahasiswa[j] = dataMahasiswa[k];
                    dataMahasiswa[k] = temp;
                }
            }
        }

        // menampilkan data
        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        for (int j = 0; j < i; j++) {
            StringTokenizer st = new StringTokenizer(dataMahasiswa[j], ",");
            System.out.printf("|%2d ", j + 1);
            st.nextToken();
            System.out.printf("|\t%-11s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");

        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
    }

    // selection sort
    public static void selectionSort() throws IOException {

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

        String data = buffer.readLine();
        String[] dataMahasiswa = new String[1000];
        int i = 0;
        while (data != null) {
            dataMahasiswa[i] = data;
            data = buffer.readLine();
            i++;
        }

        // sorting
        for (int j = 0; j < i; j++) {
            int min = j;
            for (int k = j + 1; k < i; k++) {
                StringTokenizer st1 = new StringTokenizer(dataMahasiswa[min], ",");
                StringTokenizer st2 = new StringTokenizer(dataMahasiswa[k], ",");
                st1.nextToken();
                st2.nextToken();
                String nim1 = st1.nextToken();
                String nim2 = st2.nextToken();
                if (nim1.compareTo(nim2) > 0) {
                    min = k;
                }
            }
            String temp = dataMahasiswa[j];
            dataMahasiswa[j] = dataMahasiswa[min];
            dataMahasiswa[min] = temp;
        }

        // menampilkan data
        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        for (int j = 0; j < i; j++) {
            StringTokenizer st = new StringTokenizer(dataMahasiswa[j], ",");
            System.out.printf("|%2d ", j + 1);
            st.nextToken();
            System.out.printf("|\t%-11s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
    }

    // quick sort
    public static void quickSort() throws IOException {

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

        String data = buffer.readLine();
        String[] dataMahasiswa = new String[1000];
        int i = 0;
        while (data != null) {
            dataMahasiswa[i] = data;
            data = buffer.readLine();
            i++;
        }

        // sorting
        quickSort(dataMahasiswa, 0, i - 1);

        // menampilkan data
        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        for (int j = 0; j < i; j++) {
            StringTokenizer st = new StringTokenizer(dataMahasiswa[j], ",");
            System.out.printf("|%2d ", j + 1);
            st.nextToken();
            System.out.printf("|\t%-11s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void quickSort(String[] data, int kiri, int kanan) {
        if (kiri < kanan) {
            int posisi = partisi(data, kiri, kanan);
            quickSort(data, kiri, posisi - 1);
            quickSort(data, posisi + 1, kanan);
        }
    }

    public static int partisi(String[] data, int kiri, int kanan) {
        StringTokenizer st1 = new StringTokenizer(data[kiri], ",");
        StringTokenizer st2 = new StringTokenizer(data[kanan], ",");
        st1.nextToken();
        st2.nextToken();
        String pivot = st1.nextToken();
        int i = kiri;
        int j = kanan;
        while (i < j) {
            while (st1.nextToken().compareTo(pivot) <= 0 && i < kanan) {
                i++;
            }
            while (st2.nextToken().compareTo(pivot) > 0) {
                j--;
            }
            if (i < j) {
                String temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        data[kiri] = data[j];
        data[j] = pivot;
        return j;
    }

    // insertion sort
    public static void insertionSort() throws IOException {

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

        String data = buffer.readLine();
        String[] dataMahasiswa = new String[1000];
        int i = 0;
        while (data != null) {
            dataMahasiswa[i] = data;
            data = buffer.readLine();
            i++;
        }

        // sorting
        insertionSort(dataMahasiswa, i);

        // menampilkan data
        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        for (int j = 0; j < i; j++) {
            StringTokenizer st = new StringTokenizer(dataMahasiswa[j], ",");
            System.out.printf("|%2d ", j + 1);
            st.nextToken();
            System.out.printf("|\t%-11s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void insertionSort(String[] data, int n) {
        for (int i = 1; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(data[i], ",");
            StringTokenizer st2 = new StringTokenizer(data[i - 1], ",");
            st1.nextToken();
            st2.nextToken();
            String temp = st1.nextToken();
            int j = i - 1;
            while (j >= 0 && st2.nextToken().compareTo(temp) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = temp;
        }
    }

    // bubble sort
    public static void bubbleSort() throws IOException {

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

        String data = buffer.readLine();
        String[] dataMahasiswa = new String[1000];
        int i = 0;
        while (data != null) {
            dataMahasiswa[i] = data;
            data = buffer.readLine();
            i++;
        }

        // sorting
        bubbleSort(dataMahasiswa, i);

        // menampilkan data
        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(

                "---------------------------------------------------------------------------------------------------------------------------------------");
        for (int j = 0; j < i; j++) {

            StringTokenizer st = new StringTokenizer(dataMahasiswa[j], ",");
            System.out.printf("|%2d ", j + 1);
            st.nextToken();
            System.out.printf("|\t%-11s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void bubbleSort(String[] data, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                StringTokenizer st1 = new StringTokenizer(data[j], ",");
                StringTokenizer st2 = new StringTokenizer(data[j + 1], ",");
                st1.nextToken();
                st2.nextToken();
                if (st1.nextToken().compareTo(st2.nextToken()) > 0) {
                    String temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    // shell sort
    public static void shellSort() throws IOException {

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

        String data = buffer.readLine();
        String[] dataMahasiswa = new String[1000];
        int i = 0;
        while (data != null) {
            dataMahasiswa[i] = data;
            data = buffer.readLine();
            i++;
        }

        // sorting
        shellSort(dataMahasiswa, i);

        // menampilkan data
        System.out.println("-----------------");
        System.out.println("Berikut data Siakad\n");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|NO |\tNIM         |\tNAMA               |\tFAKULTAS                       |\tPROGRAM STUDI                  |\tTAHUN  |");
        System.out.println(

                "---------------------------------------------------------------------------------------------------------------------------------------");
        for (int j = 0; j < i; j++) {

            StringTokenizer st = new StringTokenizer(dataMahasiswa[j], ",");
            System.out.printf("|%2d ", j + 1);
            st.nextToken();
            System.out.printf("|\t%-11s ", st.nextToken());
            System.out.printf("|\t%-18s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-30s ", st.nextToken());
            System.out.printf("|\t%-6s ", st.nextToken());
            System.out.print("|\n");
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void shellSort(String[] data, int n) {
        int jarak = n / 2;
        while (jarak >= 1) {
            for (int i = 0; i < n; i++) {
                int j = i;
                while (j >= jarak && data[j - jarak].compareTo(data[j]) > 0) {
                    String temp = data[j];
                    data[j] = data[j - jarak];
                    data[j - jarak] = temp;
                    j = j - jarak;
                }
            }
            jarak = jarak / 2;
        }
    }

    // pencarian data
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
            File file = new File("Proyek/Siakad/UAS/datamahasiswa.txt");

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
                    System.out.printf("|\t%-11s ", st.nextToken());
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

    // hapus data
    public static void hapusData() throws IOException {
        // penyimpanan utama
        File datamahasiswa = new File("Proyek/Siakad/UAS/datamahasiswa.txt");
        FileReader inputFile = new FileReader(datamahasiswa);
        BufferedReader inputBuffer = new BufferedReader(inputFile);

        // penyimpanan sementara
        File dataTemp = new File("Proyek/Siakad/UAS/datamahasiswatemp.txt");
        FileWriter outputFile = new FileWriter(dataTemp);
        BufferedWriter outputBuffer = new BufferedWriter(outputFile);

        // tampilkan data
        System.out.println("Daftar Mahasiswa");
        tampilData();

        // input hapus data
        Scanner input = new Scanner(System.in);
        System.out.print("\nMasukan Nomor data yang akan dihapus : ");
        int hapusNomor = input.nextInt();

        // pengulangan baris data
        int dataBaris = 0;
        String data = inputBuffer.readLine();
        boolean kosong = false; // jika data tidak ada

        while (data != null) {
            dataBaris++;
            boolean hapus = false;

            StringTokenizer st = new StringTokenizer(data, ",");
            // tampilkan data yang akan dihapus
            if (hapusNomor == dataBaris) {
                System.out.println("\nBerikut data yang akan dihapus :");
                System.out.println("----------------------------------");
                System.out.println("Primary Key   : " + st.nextToken());
                System.out.println("NIM           :" + st.nextToken());
                System.out.println("Nama          :" + st.nextToken());
                System.out.println("Fakultas      :" + st.nextToken());
                System.out.println("Prodi         :" + st.nextToken());
                System.out.println("Tahun         :" + st.nextToken());
                hapus = yat("Apa anda yakin ingin menghapus data ini?");
                kosong = true;
            }
            if (hapus) {
                System.out.println("data berhasil dihapus");
            } else {
                outputBuffer.write(data);
                outputBuffer.newLine();
            }
            data = inputBuffer.readLine();
        }
        // baris kosong
        if (!kosong) {
            System.err.println("Data tidak ditemukan");
        }
        // menulis data sementara
        outputBuffer.flush();
        inputFile.close();
        inputBuffer.close();
        outputFile.close();
        outputBuffer.close();
        // hapus data
        System.gc();
        datamahasiswa.delete();
        // ganti nama file datamahasiswa
        dataTemp.renameTo(datamahasiswa);

    }

    // update data
    public static void editData() throws IOException {

        // penyimpanan utama
        File datamahasiswa = new File("Proyek/Siakad/UAS/datamahasiswa.txt");
        FileReader inputFile = new FileReader(datamahasiswa);
        BufferedReader inputBuffer = new BufferedReader(inputFile);

        // penyimpanan sementara
        File dataTemp = new File("Proyek/Siakad/UAS/datamahasiswatemp.txt");
        FileWriter outputFile = new FileWriter(dataTemp);
        BufferedWriter outputBuffer = new BufferedWriter(outputFile);

        // tampilkan data
        System.out.println("Daftar Mahasiswa");
        tampilData();

        // input edit data
        Scanner input = new Scanner(System.in);
        System.out.print("\nMasukan nomor data yang akan diedit : ");
        int editNomor = input.nextInt();

        // pengulangan baris data
        int dataBaris = 0;
        String data = inputBuffer.readLine();
        boolean kosong = false; // jika data tidak ada

        while (data != null) {
            dataBaris++;

            StringTokenizer st = new StringTokenizer(data, ",");
            // tampilkan data yang akan diedit
            if (editNomor == dataBaris) {
                System.out.println("\nBerikut data yang akan diedit :");
                System.out.println("----------------------------------");
                System.out.println("Primary Key   : " + st.nextToken());
                System.out.println("NIM           :" + st.nextToken());
                System.out.println("Nama          :" + st.nextToken());
                System.out.println("Fakultas      :" + st.nextToken());
                System.out.println("Prodi         :" + st.nextToken());
                System.out.println("Tahun         :" + st.nextToken());

                // inputan user
                String[] barisData = { "NIM", "Nama", "Fakultas", "Prodi", "Tahun" };
                String[] tempData = new String[5];

                // refresh
                st = new StringTokenizer(data, ",");
                String dataAsli = st.nextToken();

                for (int i = 0; i < barisData.length; i++) {
                    boolean edit = yat("Apa anda ingin merubah " + barisData[i] + " ?");
                    dataAsli = st.nextToken();
                    if (edit) {
                        input = new Scanner(System.in);
                        System.out.print("\nData lama (" + barisData[i] + ") Masukan yang baru : ");
                        tempData[i] = input.nextLine();
                    } else {
                        tempData[i] = dataAsli;
                    }
                }

                st = new StringTokenizer(data, ",");
                System.out.println("\nBerikut data yang sudah diedit :");
                System.out.println("----------------------------------");
                st.nextToken();
                System.out.println("NIM           :" + st.nextToken() + "->" + tempData[0]);
                System.out.println("Nama          :" + st.nextToken() + "->" + tempData[1]);
                System.out.println("Fakultas      :" + st.nextToken() + "->" + tempData[2]);
                System.out.println("Prodi         :" + st.nextToken() + "->" + tempData[3]);
                System.out.println("Tahun         :" + st.nextToken() + "->" + tempData[4]);

                boolean edit = yat("Apa anda yakin ingin mengedit data ini?");
                if (edit) {
                    // cek data baru
                    boolean tersedia = cekData(tempData, false);
                    if (tersedia) {
                        System.err.println("data sudah tersedia, edit dibatalkan");
                        outputBuffer.write(data);
                    } else {
                        String nim = tempData[0];
                        String nama = tempData[1];
                        String fakultas = tempData[2];
                        String prodi = tempData[3];
                        String tahun = tempData[4];

                        // buat primary key
                        String primary = nama.replaceAll("\\s", "").toLowerCase();
                        String primaryKey = primary + "_" + nim;

                        outputBuffer.write(
                                primaryKey + "," + nim + "," + nama + "," + fakultas + "," + prodi + "," + tahun);

                    }

                } else {
                    // copas data
                    outputBuffer.write(data);

                }
            } else {
                // copas data
                outputBuffer.write(data);

            }
            outputBuffer.newLine();
            data = inputBuffer.readLine();
        }

        // menulis data sementara
        outputBuffer.flush();
        inputFile.close();
        inputBuffer.close();
        outputFile.close();
        outputBuffer.close();
        // hapus data
        System.gc();
        datamahasiswa.delete();
        // ganti nama file datamahasiswa
        dataTemp.renameTo(datamahasiswa);
    }

    public static void UAS() throws IOException {
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
            System.out.printf("|\t%-11s ", st.nextToken());
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
}