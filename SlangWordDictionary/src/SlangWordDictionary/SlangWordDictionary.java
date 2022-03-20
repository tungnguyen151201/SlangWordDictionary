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
	public static void ReadDatFile() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("slang.dat"));
			HashMap<String, String> hm = (HashMap<String, String>)ois.readObject();
			System.out.println(hm.get("#1"));
			ois.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		while (true) {
			System.out.println("Chon chuc nang thuc hien:");
			System.out.println("0. Thoat chuong trinh");
			System.out.println("1. Tim kiem");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();
			switch (option) {
			case 0:
				System.out.println("Chuong trinh ket thuc!");
				return;
			case 1:
				ReadTextFile();
				ReadDatFile();
				break;
			}
		}
	}
}
