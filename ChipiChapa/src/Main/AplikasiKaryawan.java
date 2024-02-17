package Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Karyawan {
    String kode;
    String nama;
    String jenisKelamin;
    String jabatan;
    double gaji;

    public Karyawan(String kode, String nama, String jenisKelamin, String jabatan, double gaji) {
        this.kode = kode;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jabatan = jabatan;
        this.gaji = gaji;
    }
}

public class AplikasiKaryawan {
    private List<Karyawan> dataKaryawan = new ArrayList<>();
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    
    
    //Input data
    private void inputDataKaryawan() {
        Scanner scanner = new Scanner(System.in);

        String kode;
        do{
            kode = generateKodeKaryawan();
        }while(isKodeKaryawanExist(kode));

        String nama;
        do{
            System.out.print("Masukkan nama karyawan (minimal 3 huruf alfabet): ");
            nama = scanner.nextLine();
        }while(nama.length() < 3 || !nama.matches("[a-zA-Z]+"));

        String jenisKelamin;
        do{
            System.out.print("Masukkan jenis kelamin (Laki-Laki / Perempuan) [Case Sensitive]: ");
            jenisKelamin = scanner.nextLine();
        }while(!jenisKelamin.equals("Laki-Laki") && !jenisKelamin.equals("Perempuan"));

        String jabatan;
        do{
            System.out.print("Masukkan jabatan (Manager / Supervisor / Admin) [Case Sensitive]: ");
            jabatan = scanner.nextLine();
        }while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));

        double gaji = 0;
        if(jabatan.equals("Manager")) {
            gaji = 8000000;
        } 
        else if(jabatan.equals("Supervisor")) {
            gaji = 6000000;
        } 
        else if(jabatan.equals("Admin")) {
            gaji = 4000000;
        }
        Karyawan karyawan = new Karyawan(kode, nama, jenisKelamin, jabatan, gaji);
        dataKaryawan.add(karyawan);
        System.out.println("Data karyawan berhasil ditambahkan.");
    }
    
    	//auto generate kode karyawan
    private String generateKodeKaryawan(){
        String hurufAcak = getRandomAlphabets(2);
        String angkaAcak = getRandomNumbers(4);
        return hurufAcak + "-" + angkaAcak;
    }

    private String getRandomAlphabets(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            char randomChar = (char) ('A' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private String getRandomNumbers(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private boolean isKodeKaryawanExist(String kode){
        for(Karyawan karyawan : dataKaryawan) {
            if(karyawan.kode.equals(kode)) {
                return true;
            }
        }
        return false;
    }

    //viewdata
    private void lihatDataKaryawan(){
        if (dataKaryawan.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
        } else {
            System.out.println("=== Data Karyawan ===");
            System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-10s |%n", "Kode", "Nama", "Jenis Kelamin", "Jabatan", "Gaji");
            System.out.println("----------------------------------------------------------------------------------------");
            for (Karyawan karyawan : dataKaryawan) {
                System.out.printf("| %-10s | %-20s | %-15s | %-10s | Rp %,.2f |%n", karyawan.kode, karyawan.nama, karyawan.jenisKelamin, karyawan.jabatan, karyawan.gaji);
            }
        }
    }

//Update data karyawan
    private void updateDataKaryawan() {
        if (dataKaryawan.isEmpty()){
            System.out.println("Belum ada data karyawan.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan kode karyawan yang ingin diperbarui: ");
        String kodeToUpdate = scanner.nextLine();

        boolean found = false;
        for(Karyawan karyawan : dataKaryawan) {
            if(karyawan.kode.equals(kodeToUpdate)) {
                found = true;
                System.out.println("Data Karyawan yang akan diperbarui:");
                System.out.println("Nama: " + karyawan.nama);
                System.out.println("Jenis Kelamin: " + karyawan.jenisKelamin);
                System.out.println("Jabatan: " + karyawan.jabatan);
                System.out.println("Gaji: Rp %,.2f" + karyawan.gaji);
                
                System.out.println("Masukkan data baru:");
                
                String newNama;
                do{
	                System.out.print("Nama baru (kosongkan jika tidak ingin diubah): ");
	                newNama = scanner.nextLine();
                }while (newNama.length() < 3 || !newNama.matches("[a-zA-Z]+"));
                if(!newNama.isEmpty()) {
                	karyawan.nama = newNama;
	            }
                
                String newJenisKelamin;
                do{
	                System.out.print("Jenis kelamin baru (Laki-Laki / Perempuan, kosongkan jika tidak ingin diubah) [Case Sensitive]: ");
	                newJenisKelamin = scanner.nextLine();
                }while (!newJenisKelamin.equals("Laki-Laki") && !newJenisKelamin.equals("Perempuan"));
	            if (!newJenisKelamin.isEmpty()){
	            	karyawan.jenisKelamin = newJenisKelamin;
	            }
                

                String newJabatan;
                do{
	                System.out.print("Jabatan baru (Manager / Supervisor / Admin, kosongkan jika tidak ingin diubah) [Case Sensitive]: ");
	                newJabatan = scanner.nextLine();
                }while(!newJabatan.equals("Manager") && !newJabatan.equals("Supervisor") && !newJabatan.equals("Admin"));
                if(!newJabatan.isEmpty()){
                    karyawan.jabatan = newJabatan;
                    if(newJabatan.equals("Manager")){
                        karyawan.gaji = 8000000;
                    } 
                    else if(newJabatan.equals("Supervisor")){
                        karyawan.gaji = 6000000;
                    } 
                    else if(newJabatan.equals("Admin")){
                        karyawan.gaji = 4000000;
                    }
                }
                System.out.println("Data karyawan berhasil diperbarui.");
                break;
            }
        }
        if (!found) {
            System.out.println("Kode karyawan tidak ditemukan.");
        }
    }

//hapus data
    private void hapusDataKaryawan() {
        if(dataKaryawan.isEmpty()){
            System.out.println("Data karyawan tidak ditemukan.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan kode karyawan yang ingin dihapus: ");
        String kodeToDelete = scanner.nextLine();

        boolean found = false;
        for (Karyawan karyawan : dataKaryawan) {
            if (karyawan.kode.equals(kodeToDelete)) {
                found = true;
                System.out.println("Data Karyawan yang akan dihapus:");
                System.out.println("Nama: " + karyawan.nama);
                System.out.println("Jenis Kelamin: " + karyawan.jenisKelamin);
                System.out.println("Jabatan: " + karyawan.jabatan);
                System.out.println("Gaji: Rp %,.2f" + karyawan.gaji);
                System.out.print("Apakah Anda yakin ingin menghapus data ini? (Y/T): ");
                String confirmation = scanner.nextLine();
                if(confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("Y")) {
                    dataKaryawan.remove(karyawan);
                    System.out.println("Data karyawan berhasil dihapus.");
                } 
                else{
                    System.out.println("Penghapusan data karyawan dibatalkan.");
                }
                break;
            }
        }

        if(!found){
            System.out.println("Kode karyawan tidak ditemukan.");
        }
    }

    public void menuUtama() {
        int pilihan;
        do {
            System.out.println("=== Aplikasi Data Karyawan ===");
            System.out.println("1. Input Data Karyawan");
            System.out.println("2. Lihat Data Karyawan");
            System.out.println("3. Update Data Karyawan");
            System.out.println("4. Hapus Data Karyawan");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-4): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    inputDataKaryawan();
                    break;
                case 2:
                    lihatDataKaryawan();
                    break;
                case 3:
                    updateDataKaryawan();
                    break;
                case 4:
                    hapusDataKaryawan();
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);
    }
    
    public static void main(String[] args) {
        AplikasiKaryawan aplikasi = new AplikasiKaryawan();
        aplikasi.menuUtama();
    }
    
}
