package SlangWordDictionary;

import java.util.*;
import java.io.*;

public class SlangWordDictionary {
	public static void ReadTextFile() {
		try {
			Scanner sc = new Scanner(new FileInputStream("slang.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("slang.dat"));
			HashMap<String, String> hm = new HashMap<String, String>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] tokens = line.split("`");
				hm.put(tokens[0], tokens[1]);
			}	
			oos.writeObject(hm);
			sc.close();
			oos.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public static HashMap<String, String> ReadDatFile() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("slang.dat"));
			HashMap<String, String> hm = (HashMap<String, String>)ois.readObject();			
			ois.close();
			return hm;
		} catch (IOException e) {			
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void SearchSlangWord(HashMap<String, String> hm) {
		System.out.println("Nhap slang word can tim:");
		Scanner sc1 = new Scanner(System.in);
		String sw = sc1.nextLine();
		if (hm.get(sw) != null) {
			System.out.println("Nghia cua slang word: " + hm.get(sw));
		}
		else {
			System.out.println("Khong tim thay slang word!");
		}
	}
	public static void main(String[] args) {
		File f = new File("slang.dat");
		if (!f.exists()) {
			ReadTextFile();
		}
		HashMap<String, String> hm = ReadDatFile();
		if (hm == null) {
			System.out.println("Loi doc dat file!");
			return;
		}
		while (true) {
			System.out.println("Chon chuc nang thuc hien:");
			System.out.println("0. Thoat chuong trinh");
			System.out.println("1. Tim kiem theo slang word");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();
			switch (option) {
			case 0:
				System.out.println("Chuong trinh ket thuc!");
				return;
			case 1:
				SearchSlangWord(hm);
				break;
			}
		}
	}
}
