package SlangWordDictionary;

import java.util.*;
import java.io.*;

public class SlangWordDictionary {
	public static List<String> history = new ArrayList<String>();
	public static void ReadTextFile() {
		try {
			Scanner sc = new Scanner(new FileInputStream("slang.txt"));			
			HashMap<String, String> hm = new HashMap<String, String>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] tokens = line.split("`");
				hm.put(tokens[0], tokens[1]);
			}
			WriteDatFile(hm);
			sc.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public static void WriteDatFile(HashMap<String, String> hm) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("slang.dat"));
			oos.writeObject(hm);
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
		history.add(sw);
		if (hm.get(sw) != null) {
			System.out.println("Nghia cua slang word: " + hm.get(sw));
		}
		else {
			System.out.println("Khong tim thay slang word!");
		}
	}
	public static void SearchDefinition(HashMap<String, String> hm) {
		System.out.println("Nhap definition can tim:");
		Scanner sc2 = new Scanner(System.in);
		String sw = sc2.nextLine();
		Set<Map.Entry<String, String>> set = hm.entrySet();
		boolean flag = false;
		for (Map.Entry<String, String> me : set) {
			if (me.getValue().contains(sw)) {
				if (!flag) {
					System.out.println("Slang words:");
					flag = true;
				}
				System.out.println(me.getKey() + " - " + me.getValue());
			}
		}
		if (!flag) {
			System.out.println("Khong tim thay slang word!");
		}
	}
	public static void HistorySlangWord() {
		if (history.isEmpty()) {
			System.out.println("Ban chua tim kiem slang word nao!");
			return;
		}
		System.out.println("Cac slang word da tim kiem:");
		for (String sw : history) {
			System.out.println(sw);
		}
	}
	public static void AddSlangWord(HashMap<String, String> hm) {
		System.out.println("Nhap slang word muon add:");
		Scanner sc3 = new Scanner(System.in);
		String sw = sc3.nextLine();
		System.out.println("Nhap definition:");
		String def = sc3.nextLine();
		hm.put(sw, def);
		WriteDatFile(hm);
		System.out.println("Add slang word thanh cong!");
	}
	public static void EditSlangWord(HashMap<String, String> hm) {
		System.out.println("Nhap slang word can edit:");
		Scanner sc4 = new Scanner(System.in);
		String sw = sc4.nextLine();
		if (hm.get(sw) != null) {
			System.out.println("Nhap definition moi:");
			hm.put(sw, sc4.nextLine());
			WriteDatFile(hm);
			System.out.println("Edit slang word thanh cong!");
		}
		else {
			System.out.println("Khong tim thay slang word!");
		}
	}
	public static void DeleteSlangWord(HashMap<String, String> hm) {
		System.out.println("Nhap slang word can edit:");
		Scanner sc5 = new Scanner(System.in);
		String sw = sc5.nextLine();
		if (hm.get(sw) != null) {
			hm.remove(sw);
			WriteDatFile(hm);
			System.out.println("Delete slang word thanh cong!");
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
		while (true) {
			HashMap<String, String> hm = ReadDatFile();
			if (hm == null) {
				System.out.println("Loi doc dat file!");
				return;
			}
			System.out.println("Chon chuc nang thuc hien:");
			System.out.println("0. Thoat chuong trinh");
			System.out.println("1. Tim kiem theo slang word");
			System.out.println("2. Tim kiem theo definition");
			System.out.println("3. Xem lich su tim kiem slang word");
			System.out.println("4. Add slang word");
			System.out.println("5. Edit slang word");
			System.out.println("6. Delete slang word");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();
			switch (option) {
			case 0:
				System.out.println("Chuong trinh ket thuc!");
				return;
			case 1:
				SearchSlangWord(hm);
				break;
			case 2:
				SearchDefinition(hm);
				break;
			case 3:
				HistorySlangWord();
				break;
			case 4:
				AddSlangWord(hm);
				break;
			case 5:
				EditSlangWord(hm);
				break;
			case 6:
				DeleteSlangWord(hm);
				break;
			}
		}
	}
}
