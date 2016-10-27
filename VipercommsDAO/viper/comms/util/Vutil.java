package viper.comms.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfWriter;

public class Vutil {

	/**
	 * Mengkonversi bilangan desimal ke karakter ASCII
	 * @param int Bilangan desimal
	 * @return Character representasi karakter ASCII
	 */
	public static Character toAsciiChar(int i) {
		return new Character((char)i);
	}

	public static void Tokenize(String s,String s2,String s3){
		StringTokenizer tok = new StringTokenizer(s, " ");
		// Cara cepat untuk mendapatkan jumlah token
		System.out.println("Jumlah token: " + tok.countTokens());

		int i = 0;
		while (tok.hasMoreTokens()) {
			System.out.println(tok.nextToken());
			i++;
		}

		// Mendapatkan jumlah token (lebih lambat)
		System.out.println("Jumlah token: " + i);



		// Karakter pemisah adalah !, ?, dan .
		StringTokenizer tok2 = new StringTokenizer(s2, "!?.");
		while (tok2.hasMoreTokens()) {
			System.out.println(tok2.nextToken());
		}

		Scanner scan = new Scanner(s3).useDelimiter(" ");
		while (scan.hasNext()) {
			System.out.println(scan.next());
		}

		while(scan.hasNextInt()) {
			// Scan sebagai int
			System.out.println(scan.nextInt());
		}

	}

	/**
	 * Menghasilkan bilangan acak antara 0-9
	 * @return short Bilangan acak
	 */
	public static short rnd() {
		return (short)(Math.floor(Math.random() * 9) + 0);
	}

	/**
	 * Menghasilkan bilangan acak antara 0-9
	 * dengan pendekatan java.util.Random
	 * @return int Bilangan acak
	 */
	public static int rnd2() {
		Random rand = new Random();
		return rand.nextInt(10);
	}


	/**
	 * Menghasilkan bilangan acak
	 * @param count Jumlah bilangan yang dihasilkan
	 * @return String Bilangan acak
	 */
	public static String rndCount(int count) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i=0; i<count; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}


	/**
	 * Menghasilkan string acak
	 * @param str String yang akan diacak
	 * @param count Jumlah string acak yang dihasilkan
	 * @return String acak sebanyak count karakter
	 */
	public static String rndString(String str, int count) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		int len = str.length();
		System.out.println(len);

		int n = 0;
		for (int i=0; i<count; i++) {
			n = rand.nextInt(len);
			// Ambil karakter string pada posisi n (acak)
			sb.append(str.charAt(n));
		}
		return sb.toString();
	}

	/**
	 * Validasi bilangan (0-9)
	 * @param str String angka yang akan divalidasi
	 * @return boolean true jika valid, sebaliknya false
	 */
	public static boolean isAngka(String str) {
		return Pattern.matches("\\d+", str);
	}

	/**
	 * Validasi huruf
	 * @param str String huruf yang akan divalidasi
	 * @return boolean true jika valid, sebaliknya false
	 */
	public static boolean isHuruf(String str) {
		return Pattern.matches("^[a-zA-Z]+$", str);
	}


	/**
	 * Validasi bilangan dan/atau huruf
	 * @param str String yang akan divalidasi
	 * @return boolean true jika valid, sebaliknya false
	 */
	public static boolean isAngkaHuruf(String str) {
		return Pattern.matches("\\w+", str);
	}


	/**
	 * Validasi URL
	 * @param str String URL yang akan divalidasi
	 * @return boolean true jika valid, sebaliknya false
	 */
	public static boolean isURL(String str) {
		return Pattern.matches(
				"http://([\\w-]+\\.)+[A-Za-z]{2,3}$", str);
	}


	/**
	 * Validasi alamat email
	 * @param str String email yang akan divalidasi
	 * @return boolean true jika valid, sebaliknya false
	 */
	public static boolean isEmail(String str) {
		return Pattern.matches(
				"^([\\w-]+)@([\\w-]+\\.)+[A-Za-z]{2,3}$", str);
	}

	/**
	 * Menggabung elemen array string
	 * @param arr_str Array string
	 * @param pemisah String atau karakter pemisah
	 * @return String
	 */
	public static String implode(String[] arr_str, String pemisah) {
		StringBuilder sb = new StringBuilder();
		for (String s : arr_str) {
			sb.append(s + pemisah);
		}
		// Hapus sisa karakter pemisah
		sb = sb.delete(sb.length() - pemisah.length(), sb.length());
		return sb.toString();
	}

	public static void TanggalDanWaktu(){
		/** Menguraikan tanggal dan waktu **/
		Calendar cal = Calendar.getInstance();

		// Informasi tanggal sekarang
		System.out.print("\nTanggal: ");
		System.out.print(cal.get(Calendar.DATE) + "/");
		// Hitungan bulan dari nol
		System.out.print(cal.get(Calendar.MONTH)+1 + "/");
		System.out.println(cal.get(Calendar.YEAR));

		// Informasi jam
		System.out.print("Jam: ");
		System.out.print(cal.get(Calendar.HOUR) + ":");
		System.out.print(cal.get(Calendar.MINUTE) + ":");
		System.out.println(cal.get(Calendar.SECOND));


		System.out.printf("Tgl: %td%n", new java.util.Date());
		System.out.printf("Bln: %tm%n", new java.util.Date());
		System.out.printf("Thn: %tY%n", new java.util.Date());


		/** GregorianCalendar */
		System.out.println("\nGregorianCalendar");
		GregorianCalendar now = new GregorianCalendar();
		System.out.println(now.getTime());
	}

	public static void InfoLocale(){
		Locale lokal = Locale.getDefault();

		System.out.println("DisplayName: " + lokal.getDisplayName());
		// Output: Bahasa Indonesia (Indonesia)
		System.out.println("Country: " + lokal.getCountry());
		// Output: ID
		System.out.println("DisplayCountry: " + lokal.getDisplayCountry());
		// Output: Indonesia
		System.out.println("Language: " + lokal.getLanguage());
		// Output: in
		System.out.println("DisplayLanguage: " + lokal.getDisplayLanguage());
		// Output: Bahasa Indonesia

		// Mendapatkan varian kode (jika ada)
		System.out.println("Variant: " + lokal.getVariant());


		System.out.println();
		System.out.printf("%tB %n", new Date());
		// Output (misal): Januari
		/** Menetapkan lokal default **/

		// Lokalisasi Itali
		Locale.setDefault(Locale.ITALY);
		System.out.printf("%tB %n", new Date());
		// Output (misal): gennaio

		// Lokalisasi Jerman
		System.out.printf(Locale.GERMANY, "%tB %n", new Date());
		// Output (misal): Januar

		// Lokalisasi Perancis
		System.out.printf(new Locale("fr"), "%tB %n", new Date());
		// Output (misal): janvier

		System.out.println();
		// List lokal (yang terinstal)
		Locale[] arr_lokal = Locale.getAvailableLocales();
		for (Locale l : arr_lokal) {
			System.out.println(l.getDisplayName());
		}

	}

	public static void InfoTimeZone(){
		TimeZone tz = TimeZone.getDefault();

		System.out.println(tz.getID());
		// Output (misal): Asia/Bangkok
		System.out.println(tz.getDisplayName());
		// Output (misal): Indochina Time

		// List Time Zone
		String[] idZona = TimeZone.getAvailableIDs();
		for (String s : idZona) {
			System.out.println(s);
		}



		TimeZone gmt8 = TimeZone.getTimeZone("GMT+08");
		tz.setDefault(gmt8);
		System.out.println(Calendar.getInstance(gmt8).getTime());


		TimeZone cst = TimeZone.getTimeZone("America/Chicago");
		tz.setDefault(cst);
		System.out.println(Calendar.getInstance(cst).getTime());
	}

	/**
	 * Untuk pemformatan tanggal
	 *
	 * @param format String format tanggal
	 * @return String current tanggal
	 */
	public static String formatTanggal(String format) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return  sdf.format(now);
	}

	public static void TestFormatTanggal(Date now){

		System.out.printf("%tA, %<td %<tB %<tY %n", now);
		// Output (misal): Jumat, 26 Januari 2007

		System.out.println();

		/** DateFormat **/
		int[] styles = {
				DateFormat.DEFAULT,
				DateFormat.SHORT,
				DateFormat.MEDIUM,
				DateFormat.LONG,
				DateFormat.FULL
		};

		// Pemformatan tanggal
		for (int i : styles) {
			DateFormat df = DateFormat.getDateInstance(i);
			System.out.println(df.format(now));
		}


		System.out.println();
		// Pemformatan Waktu/Jam
		for (int i : styles) {
			DateFormat df = DateFormat.getTimeInstance(i);
			System.out.println(df.format(now));
		}

		System.out.println();

		// Format Date dan Time
		for (int i : styles) {
			DateFormat df = DateFormat.getDateTimeInstance(i, i);
			System.out.println(df.format(now));
		}
		System.out.println();


		/** SimpleDateFormat **/
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("dd/MM/y");
		System.out.println(sdf.format(now));
		sdf = new SimpleDateFormat("E, dd MMM yyyy");
		System.out.println(sdf.format(now));
		sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy " +
		"hh:mm:ss a, zzz");
		System.out.println(sdf.format(now));
		System.out.println(formatTanggal("yyyy/MM/dd"));
		String tg = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		System.out.println(tg);
		System.out.println();


		/** DateFormatSymbols **/
		DateFormatSymbols symbols = new DateFormatSymbols();
		String days[] = symbols.getWeekdays();
		for (int i = 0; i < days.length; i++) {
			System.out.println(days[i]);
		}
	}

	/**
	 * Mendapatkan format tanggal lokal
	 * @return Array tanggal lokal
	 */
	public static String[] tglLokal() {
		String[] arrBln = {"Januari", "Februari", "Maret", "April",
				"Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
				"Nopember", "Desember"};

		String[] arrHari= {"Minggu", "Senin", "Selasa", "Rabu",
				"Kamis", "Jum'at", "Sabtu"};

		Calendar cal = Calendar.getInstance();
		int intBln = cal.get(Calendar.MONTH);
		int intHari= cal.get(Calendar.DAY_OF_WEEK)-1;

		String[] tgl = new String[4];
		tgl[0] = arrHari[intHari];
		tgl[1] = String.valueOf(cal.get(Calendar.DATE));
		tgl[2] = arrBln[intBln];
		tgl[3] = String.valueOf(cal.get(Calendar.YEAR));

		return tgl;
	}

	public static void KalkulasiDateTime(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1 = df.parse("25/01/2007");
			Date d2 = df.parse("26/01/2007");

			String s = "";
			if (d1.equals(d2)) {
				s = "sama dengan";
			} else if (d1.before(d2)) {
				s = "sebelum";
			} else if (d1.after(d2)) {
				s = "sesudah";
			}
			System.out.println(d1 + " " + s + ' ' + d2);
		} catch (Exception e) {
			System.err.println(e.toString());
		}


		Date now = new Date();
		System.out.printf("Sekarang: %tA, %<td %<tB %<tY %n", now);

		long l = now.getTime();
		// Sekarang ditambah 5 hari
		l += 5 * 24 * 60 * 60 * 1000;

		System.out.print("5 hari yang akan datang: ");
		System.out.printf("%tA, %<td %<tB %<tY %n", new Date(l));

		System.out.println();
		Calendar cal = Calendar.getInstance();

		// Tidak tepat
		int tgl = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("5 hari yang akan datang: " + (tgl+5));

		// Sekarang ditambah 5 hari
		cal.add(Calendar.DATE, 5);
		System.out.print("5 hari yang akan datang: ");
		System.out.printf("%td %<tB %<tY %n", cal.getTime());


		// Set waktu kalender = current time
		cal.setTime(new Date());

		cal.add(Calendar.DATE, -3);
		System.out.print("3 hari yang lalu: ");
		System.out.printf("%td %<tB %<tY %n", cal.getTime());



		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal1 = Calendar.getInstance();
		// Set tgl sekarang = 01 Januari 2020
		cal1.set(2020, 0, 1);
		cal1.add(Calendar.YEAR, 10);
		System.out.println("10 tahun kemudian: " + sdf.format(cal1.getTime()));


		sdf = new SimpleDateFormat("dd MMMM yyyy [H:m:s]");
		cal1.set(2000, 0, 1, 1, 0, 0); // 01 Januari 2000 01:00:00
		cal1.add(Calendar.MINUTE, -90);
		System.out.println("1,5 jam yang lalu: " + sdf.format(cal1.getTime()));
		// Output: 31 Desember 1999 [23:30:00]


		sdf = new SimpleDateFormat("dd MMMM yyyy [H:m:s]");
		cal1.setTime(new Date());
		System.out.println(sdf.format(cal1.getTime()));

		// 3 jam 30 menit 30 detik kemudian
		cal1.add(Calendar.HOUR, 3);
		cal1.add(Calendar.MINUTE, 30);
		cal1.add(Calendar.SECOND, 30);
		System.out.println(sdf.format(cal1.getTime()));
	}



	/**
	 * Validasi dan verifikasi tanggal
	 * @param tgl Tanggal yang akan dievaluasi
	 * @return boolean true jika tanggal valid dan terverifikasi
	 */
	public static boolean isTglValid(String tgl) {
		// Pola tgl(2 digit)/bulan(2 digit)/tahun(4 digit)
		String pola = "dd/MM/yyyy";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pola);
			// Tidak ada toleransi interpretasi
			sdf.setLenient(false);
			// Parsing masukan tgl berdasar pola
			Date dt = sdf.parse(tgl);
			// Jika berhasil merepresentasikan Date, berarti ok
			return true;

		} catch (ParseException ex) {
			System.err.println("Error: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.err.println("Tidak valid: " + ex.getMessage());
		}

		return false;
	}


	/**
	 * Validasi dan verifikasi waktu (jam)
	 * @param jam String jam yang akan dievaluasi
	 * @return boolean true jika jam valid dan terverifikasi
	 */
	public static boolean isJamValid(String jam) {
		// Pola jam(00-23):menit(00-59):detik(00-59)
		String pola = "H:mm:ss";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pola);
			// Tidak ada toleransi interpretasi
			sdf.setLenient(false);
			// Parsing masukan jam berdasar pola
			Date dt = sdf.parse(jam);
			// Jika berhasil merepresentasikan Date, berarti ok
			return true;

		} catch (ParseException ex) {
			System.err.println("Error: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.err.println("Tidak valid: " + ex.getMessage());
		}

		return false;
	}


	/**
	 * Penjadwalan sekali (untuk waktu tertentu)
	 * @param String tgl Tanggal dan jam dengan format
	 * dd/MM/yyyy H:mm:ss, misal: 01/12/2007 10:20:55
	 */
	public static void penjadwalSekali(String tgl) {
		// Misal task yang akan dijadwalkan
		TimerTask task = new TimerTask() {
			public void run() {
				// Simulasi task, pesan teks
				System.out.println("Tut..tut...");
				System.out.println("Selesai...");
			}
		};

		// Pola masukan tanggal dan jam
		String pola = "dd/MM/yyyy H:mm:ss";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pola);
			sdf.setLenient(false);
			// Parsing masukan jam berdasar pola
			Date dt = sdf.parse(tgl);
			System.out.println("Penjadwalan pada: " + dt);

			Timer tmr = new Timer();
			// Mengeksekusi task yang telah terjadwal
			tmr.schedule(task, dt);

		} catch (ParseException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.out.println("Tidak valid: " + ex.getMessage());
		}

	}


	/**
	 * Penjadwalan berulang
	 * @param int periode Rentang waktu eksekusi (milidetik)
	 */
	public static void penjadwalBerulang(int periode) {
		// Misal task yang akan dijadwalkan
		TimerTask task = new TimerTask() {
			public void run() {
				// Simulasi task, pesan teks
				System.out.println("dilaksanakan [" + new Date() +"]");
			}
		};

		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(task, 0, periode);
	}

	// Jumlah hari di tiap bulan, index 0 = Januari
	private static final int[] jml_hari = {
		31, 28, 31, 30, 31, 30, 31, 31,	30, 31, 30, 31
	};


	/**
	 * Konstruktor Kalender
	 */
	public static void Kalender() {
		Calendar cal = Calendar.getInstance();

		// Mendapatkan tahun dan bulan sekarang
		int thn = cal.get(Calendar.YEAR);
		int bln = cal.get(Calendar.MONTH);

		// Menciptakan objek GregorianCalendar dengan tahun dan
		// bulan saat ini
		GregorianCalendar gCal = new GregorianCalendar(thn, bln, 1);

		System.out.printf("%tB %<tY %n", new Date());
		System.out.println("====================");
		System.out.println("Mi Se Se Ra Ka Ju Sa");
		System.out.println("--------------------");

		// Jumlah kolom kosong di awal bulan
		int blank = gCal.get(Calendar.DAY_OF_WEEK)-1;

		int dMonth = jml_hari[bln];
		// Jika tahun kabisat, jumlah hari bulan Februari = 29
		if (gCal.isLeapYear(gCal.get(Calendar.YEAR)) && bln == 1) {
			++dMonth;
		}

		// Mengosongkan kolom sebelum tanggal 1
		for (int i=0; i<blank; i++) {
			System.out.print("   ");
		}

		// Mencetak tanggal sebanyak jumlah hari bulan
		for (int i=1; i<=dMonth; i++) {
			// Tambahkan satu karakter kosong jika tgl <= 9
			if (i <= 9) {
				System.out.print(" ");
			}

			System.out.print(i);

			if ((blank + i) % 7 == 0) {
				System.out.println();
			} else {
				System.out.print(" ");
			}
		}
		System.out.println("\n--------------------");

	}

	public static void Kalender2() {
		Calendar cal = Calendar.getInstance();

		// Mendapatkan tahun dan bulan sekarang
		int thn = cal.get(Calendar.YEAR);
		int bln = cal.get(Calendar.MONTH);

		// Menciptakan objek GregorianCalendar dengan tahun dan
		// bulan saat ini
		GregorianCalendar gCal = new GregorianCalendar(thn, bln, 1);

		System.out.printf("%tB %<tY %n", new Date());
		System.out.println("====================");
		System.out.println("Mi Se Se Ra Ka Ju Sa");
		System.out.println("--------------------");

		// Jumlah kolom kosong di awal bulan
		int blank = gCal.get(Calendar.DAY_OF_WEEK)-1;

		int dMonth = jml_hari[bln];
		// Jika tahun kabisat, jumlah hari bulan Februari = 29
		if (gCal.isLeapYear(gCal.get(Calendar.YEAR)) && bln == 1) {
			++dMonth;
		}

		// Mengosongkan kolom sebelum tanggal 1
		for (int i=0; i<blank; i++) {
			System.out.print("   ");
		}

		// Mencetak tanggal sebanyak jumlah hari bulan
		for (int i=1; i<=dMonth; i++) {
			// Tambahkan satu karakter kosong jika tgl <= 9
			if (i <= 9) {
				System.out.print(" ");
			}

			// Jika i = tgl sekarang, cetak sebagai karakter #
			if (i == cal.get(Calendar.DATE)) {
				System.out.print("#");
			} else {
				System.out.print(i);
			}

			if ((blank + i) % 7 == 0) {
				System.out.println();
			} else {
				System.out.print(" ");
			}
		}
		System.out.println("\n--------------------");

	}

	/**
	 * Membaca masukan data berupa password
	 * @return Array karakter password
	 */
	public static char[] getPassword() {
		Console c = System.console();
		return c.readPassword();
	}

	/**
	 * Membaca masukan data (string) dari keyboard
	 * @return String Masukan data
	 */
	public static String getInString() {
		String s = null;
		BufferedReader input = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			s = input.readLine();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return s;
	}


	/**
	 * Membaca masukan data dari keyboard (pendekatan Scanner)
	 * Pembatas adalah carriage return (enter)
	 * @return String Masukan data
	 */
	public static String getInScanner() {
		String s = "";
		Scanner input = new Scanner(System.in).useDelimiter("\r");
		s += input.next();
		return s;
	}


	/**
	 * Membaca masukan data dari keyboard (pendekatan Console)
	 * @return String Masukan data
	 */
	public static String getInConsole() {
		Console c = System.console();
		return c.readLine();
	}

	public static void OperasiMasukan(){
		// Menggunakan StreamTokenizer
		/*
	    System.out.println("Ketikkan Angka atau Huruf: ");

	    try {
	      StreamTokenizer tokenizer = new StreamTokenizer(
	        new BufferedReader(new InputStreamReader(System.in)));

	      int tipe;
	      // Membaca hingga akhir stream
	      while ((tipe = tokenizer.nextToken())
	      != StreamTokenizer.TT_EOF) {
	        switch(tipe) {
	          case StreamTokenizer.TT_NUMBER:
	            // Mencetak sebagai angka
	            System.out.println("> " + tokenizer.nval);
	            break;
	          case StreamTokenizer.TT_WORD:
	            // Misal perintah exit untuk keluar
	            if (tokenizer.sval.equals("exit")) {
	              System.out.println("Bye...");
	              System.exit(0);
	            }
	            // Mencetak sebagai string
	            System.out.println("> " + tokenizer.sval);
	            break;
	          default:
	            System.out.println("Ketikkan Angka atau Huruf");
	            break;
	        }
	      }

	    } catch (IOException ex) {
	      System.err.println(ex.getMessage());
	    }
		 */

		// Menggunakan Scanner
		Scanner input = new Scanner(System.in);
		System.out.print("Masukkan sebuah bilangan: ");

		// Jika masukan dapat diinterpretasikan sebagai int,
		// cetak masukan sebagai integer
		if (input.hasNextInt()) {
			System.out.println(input.nextInt());
		} else {
			System.out.println("Bukan bilangan");
		}

	}


	/**
	 * Mendapatkan nama file tanpa ekstensi
	 * @param s Nama file (tanpa path)
	 * @return String Nama file
	 */
	public static String getNamaFile(String s) {
		String sNama = s;
		if (s.contains(".")) {
			int dot = s.lastIndexOf(".");
			sNama = s.substring(0, dot);
		}
		return sNama;
	}

	/**
	 * Mendapatkan ekstensi file
	 * @param s Nama file
	 * @return String Ekstensi file
	 */
	public static String getEkstensi(String s) {
		int dot = s.lastIndexOf(".");
		return s.substring(dot + 1);
	}

	public static void TestInfoFile(){
		File fl = new File("test.txt");
		System.out.println(fl + (fl.exists() ? " ada" : " tidak ada"));
		System.out.println(fl + (fl.isFile() ? " adalah file"
				: " bukan file"));

		System.out.println(fl.getName());
		// Output: test.txt
		System.out.println(getNamaFile(fl.getName()));
		// Output: test
		System.out.println(getEkstensi(fl.getName()));
		// Output: txt


		System.out.println();
		//		File fNix = new File("/tmp/test.txt");
		File fWin1 = new File("D:\\java\\bab04\\test.txt");
		File fWin2 = new File("D:\\\\java\\\\bab04\\\\test.txt");
		System.out.println(fWin1.getName());
		System.out.println(fWin2.getName());

		System.out.println();

		File f1 = new File("D:\\java\\bab04\\test.txt");
		System.out.println(f1.getAbsolutePath());
		// Output: D:\java\bab04\test.txt
		System.out.println(f1.getPath());
		// Output: D:\java\bab04\test.txt
		System.out.println(f1.getParent());
		// Output: D:\java\bab04


		// Penggunaan slash di Windows
		System.out.println();

		File f2 = new File("D:/java/bab04/test.txt");
		System.out.println(f2.getAbsolutePath());
		System.out.println(f2.getPath());
		System.out.println(f2.getParent());

		System.out.println();


		// Penggunaan separator
		System.out.println();

		String sep = File.separator;
		System.out.println(sep);
		// Output: di Windows: \, di Unix/Linux: /

		File f3 = new File("D:" + sep + "java" + sep + "bab04"
				+ sep + "test.txt");
		System.out.println(f3.exists());
		// Output: true
		System.out.println(f3.getAbsolutePath());
		// Output: D:\java\bab04\test.txt
	}

	public static void AttrFile(String file){
		File fl = new File(file);

		if (fl.exists()) {
			System.out.println(fl + " canExecute: " +fl.canExecute());
			System.out.println(fl + " canRead: " + fl.canRead());
			System.out.println(fl + " can Write: " + fl.canWrite());
			System.out.println(fl + " isHidden: " + fl.isHidden());
			System.out.println("length: " + fl.length() + " byte");
			System.out.printf("lastModified: %tc %n", fl.lastModified());
		} else {
			System.out.println(fl + " tidak ditemukan");
		}

		// Menetapkan waktu modifikasi terakhir
		if (fl.setLastModified(System.currentTimeMillis())) {
			System.out.println("Modifikasi terakhir diubah");
		}

		if (fl.canWrite()) {
			// Set agar tidak dapat ditulisi (read-only)
			fl.setWritable(false);
			System.out.println(fl + " canWrite: " +fl.canWrite());
		}
	}

	public static void CreateFile(String file){

		File fl = new File(file);

		try {
			if (!fl.exists()) {
				// Mencoba menciptakan file baru
				if (fl.createNewFile()) {
					System.out.println(fl + " created");
				}
			} else {
				System.out.println(fl + " eksis");
			}

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}








	}

	public static void ReadFile(String file){
		// Membaca isi file
		try {
			BufferedReader in = new BufferedReader(
					new FileReader(file));
			String str;
			// Membaca sampai akhir stream
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			// Menutup stream dan melepaskan resource
			in.close();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

	}

	public static void CreateTempFile(String file){
		// File temporary
		try {
			File tmpFile = File.createTempFile(file, null);
			// Mendapatkan path absolut
			System.out.println(tmpFile.getAbsolutePath());
			// Hapus setelah program keluar
			tmpFile.deleteOnExit();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}


	}

	public static void DeleteFile(String file){
		// Menghapus file
		File fl = new File(file);

		if (fl.exists()) {
			if (fl.delete()) {
				System.out.println(fl + " deleted");
			} else {
				System.out.println("gagal menghapus file " + fl);
			}
		} else {
			System.out.println("File " + fl + " tidak ditemukan");
		}
	}


	public static void WriteFile(String file){
		// Menulis ke file
		try {
			BufferedWriter out = new BufferedWriter(
					new FileWriter(file));
			out.write("Halo Indonesia");
			out.write("\nBaris baru");
			// Ini juga menghasilkan baris baru
			out.newLine();
			out.write("Apa kabar?");
			// Menutup stream dan melepaskan resource
			out.close();
			System.out.println("ok");
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

	}


	/**
	 * Menyimpan file properties ke file
	 * @param p Objek Properties
	 * @param sFile String path file tujuan
	 */
	public static void saveProperties(Properties p, String sFile) {
		try {
			FileOutputStream out = new FileOutputStream(sFile);
			p.store(out, "Ini baris komentar\nFile Konfigurasi");
			System.out.println("File konfigurasi disimpan.....");
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}


	/**
	 * Me-load file properties
	 * @param sFile String path file sumber
	 * @return Objek Properties
	 */
	public static Properties loadProperties(String sFile) {
		Properties p = new Properties();
		try {
			FileInputStream in = new FileInputStream(sFile);
			p.load(in);
//			System.out.println("File berhasil di-load.....");
			in.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return p;
	}

	public static void TestProperties(){
		final String PROP_FILE= "test.properties";
//		final String PROP_FILE= "test.ini";

		Properties p = new Properties();
		// Menetapkan key dan value default
		p.setProperty("kode", "123");
		p.setProperty("nama", "Java");

		saveProperties(p, PROP_FILE);


		Properties p2 = new Properties();
		p2 = loadProperties(PROP_FILE);

		// Mendapatkan nilai key
		System.out.println("Kode= " + p2.getProperty("kode"));
		System.out.println("Nama= " + p2.getProperty("nama"));


		System.out.println();
		// Print isi file properties
		p2.list(System.out);


		System.out.println();

		// Modifikasi nilai key "nama"
		p2.setProperty("nama", "PHP");
		// Menyimpan modifikasi
		saveProperties(p2, PROP_FILE);

		// Me-load kembali
		Properties p3 = new Properties();
		p3 = loadProperties(PROP_FILE);
		System.out.println("Nama= " + p3.getProperty("nama"));

	}

	/**
	 * Serialisasi objek ke file
	 * @param o Objek yang akan diserialisasi
	 * @param sFile File tujuan
	 * @return boolean true jika berhasil
	 */
	public static boolean serialisasi(Object o, String sFile) {
		try {
			// Serialisasi objek ke file
			ObjectOutput out = new ObjectOutputStream(
					new FileOutputStream(sFile));
			out.writeObject(o);
			out.close();
			return true;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return false;
	}


	/**
	 * Deserialisasi file (serialized) ke objek
	 * @param String sFile File sumber
	 * @return Object o Objek hasil deserialisasi
	 */
	public static Object deserialisasi(String sFile) {
		Object o = null;
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(sFile));
			o = in.readObject();
			in.close();
		} catch (ClassNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return o;
	}

	public static void TestSerialisasi(){
		// Menciptakan objek JFrame
		Object o = new javax.swing.JFrame("Halo");

		// Serialisasi JFrame
		if (serialisasi(o, "test.ser")) {
			System.out.println("ok");
		} else {
			System.exit(0);
		}

		// Rekonstruksi JFrame
		javax.swing.JFrame frm =
			(javax.swing.JFrame) deserialisasi("test.ser");
		frm.setSize(200, 100);
		frm.setVisible(true);

	}

	public static void TestEncodeDecodeUTF(){
		try {
			InputStreamReader in = new InputStreamReader(
					new FileInputStream("test.txt"));
			// Mendapatkan nama encoding karakter
			System.out.println(in.getEncoding());
		} catch (Exception ex) {
			System.err.println(ex);
		}

		String str = "Halo Indonesia";

		// Menciptakan encoder dan decoder untuk karakter
		// encoding UTF-8
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		CharsetEncoder encoder = charset.newEncoder();

		try {
			// Konversi string ke UTF-8 di ByteBuffer
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(str));

			// Konversi UTF-8 ke string
			CharBuffer cbuf = decoder.decode(bbuf);
			String s = cbuf.toString();
		} catch (CharacterCodingException ex) {
			System.err.println(ex);
		}

	}

	public static void TestPDF1(){
		String s = "Mari Membangun Indonesia Tercinta...";

		// Menciptakan Document
		Document doc = new Document();

		try {
			// Menciptakan file PDF
			PdfWriter.getInstance(doc,
					new FileOutputStream("test.pdf"));
			// Membuka dokumen dan menambahkan paragraf
			doc.open();
			doc.add(new Paragraph(s));

			System.out.println("ok");

		} catch (DocumentException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		} finally {
			doc.close();
		}
	}

	public static void TestPDF2(){
		String s = "Mari Membangun Indonesia Tercinta...";
		// Menciptakan Document
		Document doc = new Document();

		try {
			// Menciptakan file PDF
			PdfWriter.getInstance(doc,
					new FileOutputStream("test2.pdf"));

			// Mendefinisikan bab
			Paragraph p1 = new Paragraph("Bab 1",
					FontFactory.getFont(FontFactory.HELVETICA,
							18, Font.BOLDITALIC, Color.BLUE));
			Chapter bab = new Chapter(p1, 1);
			bab.setNumberDepth(0);

			// Menambahkan subbab
			Paragraph p11 = new Paragraph(s,
					FontFactory.getFont(FontFactory.HELVETICA, 16,
							Font.BOLD, Color.RED));
			Section section1 = bab.addSection(p11);
			// Menambahkan isi subbab
			Paragraph teks = new Paragraph("Isi paragraf");
			section1.add(teks);
			teks = new Paragraph(s);
			section1.add(teks);

			// Membuka dokumen dan menambahkan bab
			doc.open();
			doc.add(bab);

			System.out.println("ok");

		} catch (DocumentException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		} finally {
			doc.close();
		}
	}

	public static void CopyFile(String sumber,String tujuan){
		try {
			// Menciptakan channel sumber
			FileChannel src =
				new FileInputStream(sumber).getChannel();

			// Menciptakan channel tujuan
			FileChannel dst =
				new FileOutputStream(tujuan).getChannel();

			// Copy isi file dari sumber ke tujuan
			src.transferTo(0, src.size(), dst);

			// Jika menggunakan transferFrom
			// dst.transferFrom(src, 0, src.size());

			// Menutup channel, bersihkan resource
			src.close();
			dst.close();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

	}

	public static void TulisFile(String message , String file){
		try {
			// Menciptakan file
			FileOutputStream out =
				new FileOutputStream(file);

			// Menciptakan FileChannel
			FileChannel outChannel = out.getChannel();

			ByteBuffer buf = ByteBuffer.allocate(message.length());
			// Mendapatkan array byte dari string
			byte[] bytes = message.getBytes();
			// Prepend dan flip buffer
			buf.put(bytes);
			buf.flip();
			// Menulis data ke channel
			outChannel.write(buf);
			out.close();

		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}

	}

	/**
	 * Zip array file
	 * @param sFile Array file yang akan di-zip
	 * @param sZip Nama file zip
	 */
	public static void zip(String[] sFl, String sZip) {
		// Menciptakan buffer untuk pembacaan file
		byte[] buf = new byte[1024];

		try {
			// Menciptakan file Zip
			ZipOutputStream zout =
				new ZipOutputStream(new FileOutputStream(sZip));

			// Iterasi dan kompresi file
			for (String s : sFl) {
				FileInputStream in = new FileInputStream(s);
				ZipEntry ze = new ZipEntry(s);

				// Tambahkan Zip entry ke output stream
				zout.putNextEntry(ze);

				// Transfer byte dari file ke file Zip
				int len;
				while ((len = in.read(buf)) > 0) {
					zout.write(buf, 0, len);
				}

				// Tutup entry dan bersihkan resource
				zout.closeEntry();
				System.out.println("Zip " + ze.getName() + " [ok]");
				in.close();
			}

			System.out.println("Simpan ke " + sZip + " [OK]");
			// Bersihkan resource
			zout.close();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}


	/**
	 * Unzip file Zip (terkompresi)
	 * @param sZip Nama file zip
	 */
	public static void unzip(String sZip) {
		byte[] buf = new byte[1024];

		try {
			// Membuka file Zip
			ZipInputStream zin =
				new ZipInputStream(new FileInputStream(sZip));

			ZipEntry ze;
			// Iterasi dan mendapatkan file
			while((ze=zin.getNextEntry()) != null) {
				FileOutputStream out = new FileOutputStream(ze.getName());
				int len;
				// Transfer byte dari file Zip ke output file
				while((len = zin.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Bersihkan resource
				out.close();
				System.out.println("Unzip " + ze.getName() + " [ok]");
			}

			// Bersihkan resource
			zin.close();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public static void TestZip(String file1,String file2){
		String[] sFile = {file1, file2};
		String sFileZip = "./zip/test.zip";
		// Zip file
		zip(sFile, sFileZip);

		// Unzip file Zip
		unzip(sFileZip);
	}


	/**
	 * Mencetak semua direktori dan subdirektori
	 * @param dir Path ke direktori
	 */
	public static void printAllDirs(File dir) {
		if (dir.isDirectory()) {
			System.out.println(dir);
			// Mendapatkan array string subdirektori
			String[] sub = dir.list();
			// Print rekursif tiap isi subdirektori
			for (String s : sub) {
				printAllDirs(new File(dir, s));
			}
		}
	}


	/**
	 * Mencetak semua file di direktori dan subdirektori
	 * @param dir Path ke direktori
	 */
	public static void printAllFiles(File dir) {
		if (dir.isDirectory()) {
			String[] sub = dir.list();
			for (String s : sub) {
				printAllFiles(new File(dir, s));
			}
		} else {
			System.out.println(dir);
		}
	}


	/**
	 * Menghapus direktori beserta isinya secara rekursif
	 * @param dir Path ke direktori
	 */
	public static boolean delDir(File dir) {
		if (dir.exists()) {
			// Mendapatkan objek File (representasi file atau dir)
			File[] fls = dir.listFiles();

			for (File fl : fls) {
				// Jika direktori, proses secara rekursif
				// Guna mengetahui apakah ada isinya
				if (fl.isDirectory()) {
					delDir(fl);
				} else {
					// Hapus file atau direktori tunggal
					fl.delete();
				}
			}
		}
		return dir.delete();
	}

	public static void TestDirektory(){
		File dir = new File("test");

		// Memeriksa apakah test eksis dan merupakan direktori
		if (dir.exists() && dir.isDirectory()) {
			System.out.println(dir + " adalah direktori");
		} else {
			System.out.println(dir + " tidak ditemukan");
		}


		// Membuat direktori baru (jika belum ada)
		if (dir.mkdir()) {
			System.out.println(dir + " created");
		}


		// Misal test2 belum ada, sekaligus membuat
		// direktori test2 dan subDirTest2 didalamnya
		File dir2 = new File("test2/subDirTest2");
		if (dir2.mkdirs()) {
			System.out.println(dir2 + " created");
		}


		// List direktori dan file
		File root = new File(".");
		// Mendapatkan file dan direktori
		String[] sub = root.list();
		// Ekstraksi file dan direktori
		for (String s : sub) {
			System.out.println(s);
		}


		// List direktori dan file (rekursif)
		System.out.println();
		printAllDirs(root);

		System.out.println();
		printAllFiles(root);



		// Hapus direktori kosong
		if (dir.exists()) {
			// Menghapus direktori
			if (dir.delete()) {
				System.out.println(dir + " deleted");
			} else {
				System.out.println(dir + " gagal dihapus");
			}
		} else {
			System.out.println(dir + " tidak ditemukan");
		}



		// Hapus direktori dan isinya
		File dir3 = new File("test2");

		if (delDir(dir3)) {
			System.out.println(dir3 + " deleted");
		}
	}

	public static void TestLogging() {
		// Menciptakan logger bagi kelas Logging1
		Logger log = Logger.getLogger("Logging1");

		// Mencatat informasi untuk tingkatan yang berlainan
		log.severe("Pesan kesalahan fatal");
		log.warning("Pesan peringatan");
		log.info("Pesan informasi");
		log.config("Pesan konfigurasi");
		log.fine("Pesan fine level satu");
		log.finer("Pesan fine level dua");
		log.finest("Pesan fine level tiga");
	}

	public static void TestLogging2(){
		Logger log = Logger.getLogger("Logging2");
		FileHandler fh;

		try {
			// Konfigurasi logger dengan handler file
			fh = new FileHandler("MyLog.log", true);
			log.addHandler(fh);

			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// Informasi
			log.severe("Pesan kesalahan fatal");
			log.warning("Pesan peringatan");

		} catch (SecurityException ex) {
			System.err.println(ex);
			log.warning(ex.getMessage());
		} catch (IOException ex) {
			log.warning(ex.getMessage());
		}

	}

	public void showMessage(JFrame ownerPanel,String message,String title) {
		JOptionPane.showMessageDialog(ownerPanel, message, title, JOptionPane.OK_OPTION);
	}
	
	public static void KotakDialog(JFrame jf) {

		// Kotak dialog pesan (induknya adalah frame ini)
		// Posisi dialog di tengah frame (induk)
		JOptionPane.showMessageDialog(jf, "Halo Indonesia");

		// Induk null (Normalnya posisi dialog di tengah layar)
		JOptionPane.showMessageDialog(jf, "Halo Indonesia");

		// Dialog peringatan
		JOptionPane.showMessageDialog(jf, "Pesan Peringatan",
				"Judul", JOptionPane.WARNING_MESSAGE);

		// Dialog kesalahan
		JOptionPane.showMessageDialog(jf, "Pesan Kesalahan",
				"Judul", JOptionPane.ERROR_MESSAGE);

		// Dialog konfirmasi
		int result = JOptionPane.showConfirmDialog(null,
				"Konfirmasi", "Judul", JOptionPane.YES_NO_CANCEL_OPTION);
		switch (result) {
		case JOptionPane.YES_OPTION:
			System.out.println("Pilihan: Yes");
			break;
		case JOptionPane.NO_OPTION:
			System.out.println("Pilihan: No");
			break;
		case JOptionPane.CANCEL_OPTION:
			System.out.println("Pilihan: Cancel");
			break;
			// Dialog ditutup
		case JOptionPane.CLOSED_OPTION:
			System.out.println("Dialog ditutup");
			break;
		}

		// Dialog input
		String nama = JOptionPane.showInputDialog(null,
		"Nama Anda");
		JOptionPane.showMessageDialog(null,
				"Halo " + nama);
	}


	public static void ChooserDemo(JFrame jf,Component comp) {
		JMenuItem itemOpen, itemSave, itemDir, itemColor;
		JMenuBar mnuBar = new JMenuBar();
		jf.setJMenuBar(mnuBar);
		JMenu mnu = new JMenu("File");
		mnuBar.add(mnu);

		itemOpen = new JMenuItem("Buka File");
		mnu.add(itemOpen);
		// Menangani aksi buka file.
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				// File chooser untuk membuka file
				//int opt = chooser.showOpenDialog(ChooserDemo.this);
				// Periksa apakah jadi atau batal
				//if (opt == JFileChooser.APPROVE_OPTION) {
				//  File sf = chooser.getSelectedFile();
				//  area.append("Buka File: " + sf.getName() + CRLF);
				//} else {
				//  area.append("Batal Buka File: " + CRLF);
				//}
			}
		});

		itemSave = new JMenuItem("Simpan File");
		mnu.add(itemSave);
		// Menangani aksi penyimpanan.
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				// File chooser untuk penyimpanan file
				//int opt = chooser.showSaveDialog(ChooserDemo.this);
				//if (opt == JFileChooser.APPROVE_OPTION) {
				//  area.append("Simpan File: " +
				//  ((chooser.getSelectedFile() != null) ?
				//  chooser.getSelectedFile().getName() :
				//  "nothing") + CRLF);
				//}
			}
		});


		itemDir = new JMenuItem("Buka Direktori");
		mnu.add(itemDir);
		itemDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFileChooser chooser = new JFileChooser();
				// Mode seleksi: hanya direktori
				//chooser.setFileSelectionMode(
				//  JFileChooser.DIRECTORIES_ONLY);
				//int opt = chooser.showOpenDialog(ChooserDemo.this);
				//if (opt == JFileChooser.APPROVE_OPTION) {
				//  area.append("Buka Direktori: " +
				//  ((chooser.getSelectedFile() != null)?
				//  chooser.getSelectedFile().getName() :
				//  "nothing") + CRLF);
				//}
			}
		});

		mnu.addSeparator();
		itemColor = new JMenuItem("Color Chooser");
		mnu.add(itemColor);
		// Menangani aksi seleksi warna
		itemColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Menampilkan dialog warna
				//Color c = JColorChooser.showDialog(ChooserDemo.this,
				//  "Pilih warna", area.getBackground());
				// Menetapkan warna text area
				//if (c != null) area.setBackground(c);
			}
		});

		//area = new JTextArea(5, 20);
		//add(new JScrollPane(area), "Center");

		//	setDefaultCloseOperation(EXIT_ON_CLOSE);
		//	setSize(400, 250);
		//	setVisible(true);
	}

	public static void TestVarEnvironment(){
		// Mendapatkan nilai variabel PATH
		System.out.println("PATH = " + System.getenv("PATH"));
		// Mendapatkan nilai variabel HOME
		System.out.println("HOME = " + System.getenv("HOME"));

		// Mendapatkan semua variabel environment
		Map env = System.getenv();
		Iterator it = env.entrySet().iterator();
		for (;it.hasNext();) {
			Map.Entry entry = (Map.Entry)it.next();
			System.out.println(entry.getKey() + " = " +
					entry.getValue());
		}
	}

	public static void TestPropertySystem(){
		// Mendapatkan nama Sistem Operasi
		System.out.println("OS = " + System.getProperty("os.name"));
		// Mendapatkan versi Java yang terinstal
		System.out.println("Java versi " +
				System.getProperty("java.version"));
		// Mendapatkan nama user
		System.out.println("Nama user: " +
				System.getProperty("user.name"));

		// Mendapatkan semua properti sistem
		Properties props = System.getProperties();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()){
			String s = (String) en.nextElement();
			String strVal = props.getProperty(s);
			System.out.println(s + " = " + strVal);
		}
	}

	public static void TestProcessBuilder(){
		try {
			// Menciptakan objek ProcessBuilder untuk menjalankan
			// editor notepad
			ProcessBuilder pb = new ProcessBuilder("notepad");
			// Jalankan editor notepad
			pb.start();
		} catch (java.io.IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Menjalankan program Java dengan Runtime
	 * Asumsi: File ditemukan
	 * @param fl Objek File (Path+Nama file)
	 */
	private static void runJava(File fl) {
		String s = fl.getName();
		// Mendapatkan nama file tanpa ekstensi
		String namaFile = s.contains(".") ?
				s.substring(0, s.indexOf(".")) : s;

				// Membentuk opsi -cp dan nama file .class-nya
				String arg = "-cp \"" + fl.getParent() +
				System.getProperty("path.separator") +
				System.getProperty("java.class.path") +
				"\" " + namaFile;

				try {
					// Simulasi interpreter java
					Runtime.getRuntime().exec("java " + arg);
				} catch (java.io.IOException ex) {
					System.out.println(ex.getMessage());
				}
	}


	/**
	 * Menjalankan program Java dengan ProcessBuilder
	 * Asumsi: File ditemukan
	 * @param fl Objek File (Path+Nama file)
	 */
	private static void runJavaPB(File fl) {
		String s = fl.getName();
		String namaFile = s.contains(".") ?
				s.substring(0, s.indexOf(".")) : s;

				String arg = fl.getParent() +
				System.getProperty("path.separator") +
				System.getProperty("java.class.path");

				try {
					new ProcessBuilder("java", "-cp",  arg,
							namaFile).start();
				} catch (java.io.IOException ex) {
					System.out.println(ex.getMessage());
				}
	}

	public static void TestRunJava(){
		// Semua pernyataan berikut adalah valid

		// Menjalankan file .class di direktori sama
		runJava(new File("ProgramGUI.class"));

		// Menjalankan file tanpa ekstensi .class
		runJava(new File("ProgramGUI"));

		// Menjalankan file .class di direktori lain
		runJava(new File("D:/java/ProgramGUI"));

		runJavaPB(new File("D:/java/ProgramGUI.class"));

	}

	private static String getPrintOutPB(File fl) {
		if (!fl.exists()) {
			return "File " + fl + " tidak ditemukan";
		}

		String s = fl.getName();
		String namaFile = s.contains(".") ?
				s.substring(0, s.indexOf(".")) : s;

				String arg = fl.getParent() +
				System.getProperty("path.separator") +
				System.getProperty("java.class.path");

				StringBuffer sb = new StringBuffer();

				try {
					// Menciptakan objek Process
					Process p = new ProcessBuilder("java", "-cp",  arg,
							namaFile).start();

					// Mendapatkan InputStream dan menciptakan
					// objek BufferedReader
					BufferedReader in = new BufferedReader
					(new InputStreamReader(p.getInputStream()));

					String str;
					// Membaca input dan simpan ke StringBuffer
					while ((str = in.readLine()) != null) {
						sb.append(str);
						sb.append(System.getProperty("line.separator"));
					}
					in.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return sb.toString();
	}

	public static void TestKoneksi(){
		String strURL = "http://www.republika.co.id/";

		try {
			// Menciptakan objek URL
			URL url = new URL(strURL);

			// Menciptakan objek HttpURLConnection
			HttpURLConnection httpCon =
				(HttpURLConnection) url.openConnection();

			// Membuka link komunikasi ke resource
			httpCon.connect();
			System.out.println("Terkoneksi ke " + strURL);

			// Mendapatkan kode respon
			int resp = httpCon.getResponseCode();
			if (resp != 200) {
				System.out.println("Respon: [BAD] " + resp);
			}
			System.out.println("Respon: [OK]");

		} catch (MalformedURLException ex) {
			System.out.println("URL invalid " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Koneksi gagal: " + ex);
		}

	}

	public static void TestParsingURL(){
		String strURL =
			"http://localhost:8383/index.php?q=data#top";

		try {
			URL url = new URL(strURL);
			System.out.println("Protokol: " + url.getProtocol());
			System.out.println("Host: " + url.getHost());
			System.out.println("Port: " + url.getPort());
			System.out.println("File: " + url.getFile());
			System.out.println("Query: " + url.getQuery());
			System.out.println("Reference: " + url.getRef());

		} catch (MalformedURLException ex) {
			System.out.println("URL invalid " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void TestHeaderRespon(){
		String strURL = "http://www.republika.co.id/";

		try {
			URL url = new URL(strURL);

			URLConnection urlCon = url.openConnection();
			// Koneksi menggunakan URLConnection
			urlCon.connect();

			// Mendapatkan semua header respon dari server
			for (int i=0; ; i++) {
				String headerName = urlCon.getHeaderFieldKey(i);
				String headerValue = urlCon.getHeaderField(i);

				if (headerName == null && headerValue == null) {
					// Tidak ada header
					break;
				}

				if (headerName == null) {
					// Jika null, nilai header mungkin versi HTTP
					// Jadi, langsung print isinya
					System.out.println(headerValue);
				} else {
					System.out.println(headerName + ": " + headerValue);
				}
			}

		} catch (MalformedURLException ex) {
			System.out.println("URL invalid " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void TestIPHost(){
		try{
			String ip = "167.205.3.133";
			// Mendapatkan nama host dari IP terkait
			System.out.println("Nama host: " +
					InetAddress.getByName(ip).getHostName());

			String host = "www.gmail.com";
			// Mendapatkan alamat IP dari nama host terkait
			System.out.println("Alamat IP: " +
					InetAddress.getByName(host).getHostAddress());

			// Mendapatkan nama host dan alamat IP
			System.out.println("Host/IP: " +
					InetAddress.getByName(host));


			InetAddress ia = InetAddress.getLocalHost();
			// Mendapatkan nama host lokal
			System.out.println(ia.getHostName());
			// Mendapatkan alamat IP lokal
			System.out.println(ia.getHostAddress());
			// Mendapatkan nama host dan alamat IP lokal
			System.out.println(ia);

		} catch(Exception ex) {
			System.out.println(ex);
		}

	}

	/**
	 * Tes apakah host/IP bisa dihubungi
	 * @param String host String Nama host / IP
	 * @param int timeout Batas waktu (dalam milidetik)
	 */
	public static void Ping(String host, int timeout) {
		try {
			if (InetAddress.getByName(host).isReachable(timeout)) {
				System.out.printf("%s is reachable %n", host);
			} else {
				System.out.printf("%s is unreachable %n", host);
			}

		} catch (UnknownHostException ex) {
			System.out.printf("Unknown host: %s%n", host);
		} catch(IOException ex) {
			System.out.println(ex);
		}

	}

	public static void TestKoneksiSocket(String host){
		Socket sock = null;

		try {
			sock = new Socket(host, 80);
			if (sock.isConnected()) {
				System.out.println("Terkoneksi ke " + host);
			}

		} catch (IOException ex) {
			System.err.println("Koneksi gagal " + ex);
		} finally {
			try {
				if (sock != null) {
					sock.close();
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

	}

	public static void TestParsingSocket(String host){
		String sFl = "/test.html";
		Socket sock = null;
		try {
			sock = new Socket(host, 80);

			// Mendapatkan input stream
			InputStream is = sock.getInputStream();
			BufferedReader respon = new BufferedReader(
					new InputStreamReader(is));

			// Mendapatkan output stream
			OutputStream os = sock.getOutputStream();
			PrintWriter request = new PrintWriter(
					new OutputStreamWriter(os));

			// Request file dari server
			request.print("GET " + sFl + " HTTP/1.0\r\n\r\n");
			request.flush();

			String s;
			// Menampilkan respon server
			while ((s = respon.readLine()) != null) {
				System.out.println(s);
			}

			// Membersihkan resource
			request.close();
			respon.close();

		} catch (IOException ex) {
			System.err.println("Koneksi gagal " + ex);
		} finally {
			try {
				if (sock != null) {
					sock.close();
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

	}


	/**
	 * Resize gambar
	 * @param src Nama file sumber
	 * @param width Ukuran lebar baru (pixel)
	 * @param height Ukuran tinggi baru (pixel)
	 * @param dest Nama file target
	 */
	public static void resize(String src, int width,
			int height, String dest) {
		try {
			// Membaca file gambar ke buffered image
			BufferedImage bsrc = ImageIO.read(new File(src));
			// Menciptakan buffered image target
			BufferedImage bdest =
				new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
			// Menciptakan grafik 2D
			Graphics2D g = bdest.createGraphics();
			// Melakukan transformasi skala
			AffineTransform at = AffineTransform.getScaleInstance(
					(double)width/bsrc.getWidth(),
					(double)height/bsrc.getHeight());
			// Rendering, menerapkan transformasi
			g.drawRenderedImage(bsrc,at);
			// Menyimpan buffered image ke format JPG
			ImageIO.write(bdest, "JPG", new File(dest));
			System.out.println("ok");
		} catch (java.io.IOException ex) {
			System.err.println(ex);
		}
	}


	public static void TestResize() {
		// Resize ferrari.jpg ke ukuran 160x120
		resize("ferrari.jpg", 160, 120, "ferrari2.png");
	}

	public static void TestGetDataProviderInstalled(){
		Set<String> result = new HashSet<String>();
		// Mendapatkan provider yang terinstal
		Provider[] prov = Security.getProviders();
		int num = prov.length;

		for (int i=0; i<num; i++) {
			Set keys = prov[i].keySet();
			Iterator it = keys.iterator();
			for (;it.hasNext();) {
				String key = (String)it.next();
				key = key.split(" ")[0];

				if (key.startsWith("Alg.Alias.")) {
					// Hilangkan alias
					key = key.substring(10);
				}
				int n = key.indexOf(".");
				result.add(key.substring(0, n));
			}
		}

		for (String s : result) {
			System.out.println(s);
		}
	}

	public static String md5(String s) {
		try {
			// Menciptakan MessageDigest dengan algoritma MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Update digest
			md.update(s.getBytes());
			// Mengembalikan representasi string
			return new String(md.digest());

		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return null;
	}
	
	public static byte[] md5toByte(String s) {
		try {
			// Menciptakan MessageDigest dengan algoritma MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Update digest
			md.update(s.getBytes());
			// Mengembalikan representasi string
			return  md.digest();

		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return null;
	}


	private static Cipher ecipher;
	private static Cipher dcipher;


	/**
	 * Konstruktor EnkripsiDekripsi
	 *
	 * @param pass Password untuk enkripsi/dekripsi
	 */
	public static void EnkripsiDekripsi(String pass) {
		// Salt 8 byte
//		byte[] salt =
//		{ 0x10, 0x10, 0x01, 0x04, 0x01, 0x01, 0x01, 0x02 };
		byte[] salt ="My-Viper".getBytes();
		
		// Jumlah iterasi
		int num = 7;

		try {
			// Spesifikasi key PBE (password-based encryption)
			KeySpec keySpec =
				new PBEKeySpec(pass.toCharArray(), salt, num);
			// Menciptakan secret key dengan algoritma PBEWithMD5AndDES
			SecretKey key = SecretKeyFactory.getInstance
			( "PBEWithMD5AndDES").generateSecret(keySpec);
			

			// Mengembalikan cipher untuk enkripsi dan dekripsi
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			// Mempersiapkan parameter cipher
			AlgorithmParameterSpec paramSpec =
				new PBEParameterSpec(salt, num);

			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		} catch (InvalidAlgorithmParameterException ex) {
			System.err.println(ex);
		} catch (InvalidKeySpecException ex) {
			System.err.println(ex);
		} catch (NoSuchPaddingException ex) {
			System.err.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		} catch (InvalidKeyException ex) {
			System.err.println(ex);
		}
	}


	/**
	 * Enkripsi plain text
	 *
	 * @param str String plain text
	 * @return Array byte terenkripsi
	 */
	public static byte[] encrypt(String str) {
		try {
			// Mendapatkan array byte
			byte[] in = str.getBytes();
			// Mengembalikan data terenkripsi
			return ecipher.doFinal(in);
		} catch (BadPaddingException ex) {
			System.err.println(ex);
		} catch (IllegalBlockSizeException ex) {
			System.err.println(ex);
		}
		return null;
	}


	/**
	 * Dekripsi data terenkripsi (ciphertext)
	 *
	 * @param in Array byte terenkripsi
	 * @return String plain text
	 */
	public static String decrypt(byte[] in) {
		try {
			// Dekripsi array byte terenkripsi
			byte[] dec = dcipher.doFinal(in);
			return new String(dec);
		} catch (BadPaddingException ex) {
			System.err.println(ex);
		} catch (IllegalBlockSizeException ex) {
			System.err.println(ex);
		}
		return null;
	}

	public static void TestEnkripsiDekripsi(){
		// Plaintext
		String str = "Ini merupakan text yang akan di enkrip";
//		System.out.println("Teks: " + str);

		// Menciptakan objek EnkripsiDekripsi dengan password java
		EnkripsiDekripsi("java");
		// Enkripsi string
		byte[] enkripsi = encrypt(str);
		String renteng="";
		for (byte b : enkripsi) {
			//System.out.println(unstrecth(stretch(b)));
			renteng+=stretch(b);
			
		}
		System.out.println(renteng);
		
		int jmlElemen=renteng.length()/4;
		byte[] isispesan=new byte[jmlElemen];
		int incr=0;
		for (int i = 0; i < jmlElemen *4 ; i+=4) {
			isispesan[incr]=(byte) unstrecth( renteng.substring(i,i+4));
			incr++;
		}
//		for (int i = 0; i < enkripsi.length; i++) {
//			System.out.print(enkripsi[i]);
//			System.out.println(isispesan[i]);
//		}
		System.out.println("Enkripsi: " + new String(enkripsi));
		// Dekripsi string terenkripsi
		String dekripsi = decrypt(isispesan);
		System.out.println("Dekripsi: " + dekripsi);

		System.out.println();
		// Misal dekripsi dengan password berbeda (akan error)
		EnkripsiDekripsi("java");
		System.out.println(decrypt(enkripsi));
	}
	


	public String TestEn(String message, String key){
		// Plaintext
		String rentengz="";
		String str = message;
//		System.out.println("Teks: " + str);

		// Menciptakan objek EnkripsiDekripsi dengan password java
		EnkripsiDekripsi(key);
		// Enkripsi string
		byte[] enkripsi = encrypt(str);
		
		for (byte b : enkripsi) {
			//System.out.println(unstrecth(stretch(b)));
			rentengz+=stretch(b);
			
		}
		return rentengz;
	}
	
	public String TestDec(String cipher, String key){
		int jmlElemen=cipher.length()/4;
		byte[] isispesan=new byte[jmlElemen];
		int incr=0;
		for (int i = 0; i < jmlElemen *4 ; i+=4) {
			isispesan[incr]=(byte) unstrecth( cipher.substring(i,i+4));
			incr++;
		}
//		for (int i = 0; i < enkripsi.length; i++) {
//			System.out.print(enkripsi[i]);
//			System.out.println(isispesan[i]);
//		}
//		System.out.println("Enkripsi: " + new String(enkripsi));
		// Dekripsi string terenkripsi
		EnkripsiDekripsi(key);
		String dekripsi = decrypt(isispesan);
//		System.out.println("Dekripsi: " + dekripsi);

		System.out.println();
		// Misal dekripsi dengan password berbeda (akan error)
//		EnkripsiDekripsi("java");
//		System.out.println(decrypt(enkripsi));
		return dekripsi;
	}
	public static String stretch(byte b){
		String angka=String.valueOf(b);
		int max=4-angka.length();
		if (max<4){
			for (int i = 0; i < max; i++) {
				angka = "0" +angka;
			}
		}
		return angka;
	}
	public static int unstrecth(String xb){
		if (xb.contains("-")){
			String[] str=xb.split("-");
//			System.out.println("sebelum "+xb+"  sesudah "+"-"+str[1]);
			return Integer.parseInt("-"+str[1]);
		}else{
//			System.out.println("sebelum = sesudah "+ xb);
			return Integer.parseInt(xb);
		}
	}
	public static void TestPermissionDemo(){
		String dir = "C:/tmp";
		// Menciptakan objek SecurityManager
		SecurityManager sm = new SecurityManager();

		// Memeriksa izin penulisan di direktori
		try {
			sm.checkWrite(dir);
			System.out.println("Akses Write OK");
		} catch (SecurityException ex) {
			// Tidak memiliki izin
			System.err.println(ex.getMessage());
		}


		// Menggunakan permission
		try {
			// Izin akses penulisan di direktori
			FilePermission p = new FilePermission(dir, "write");
			// Memeriksa izin
			sm.checkPermission(p);
			// Atau dengan objek AccessController
			AccessController.checkPermission(p);
			System.out.println("Akses Write OK");
		} catch (SecurityException ex) {
			System.err.println(ex.getMessage());
		}

	}

	public static void TestSecurityManager1(){
		// Jika security manager tidak aktif, operasi berikut
		// dapat dijalankan dengan baik
		System.out.println(System.getProperty("user.home"));

		try {
			// Mengaktifkan security manager
			System.setSecurityManager(new SecurityManager());
		} catch (SecurityException ex) {
			System.err.println(ex);
		}

		// Sekarang operasi ini akan ditolak
		System.out.println(System.getProperty("user.home"));
	}



}
