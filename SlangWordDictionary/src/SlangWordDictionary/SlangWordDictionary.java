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
		System.out.println("Nhap slang word muon xoa:");
		Scanner sc5 = new Scanner(System.in);
		String sw = sc5.nextLine();
		if (hm.get(sw) != null) {
			System.out.println("Ban co chac chan muon xoa khong? (1.Yes/0.No)");
			int yesno = sc5.nextInt();
			if (yesno == 0) return;
			hm.remove(sw);
			WriteDatFile(hm);
			System.out.println("Delete slang word thanh cong!");
		}
		else {
			System.out.println("Khong tim thay slang word!");
		}
	}
	public static void RandomSlangWord(HashMap<String, String> hm) {
		Object[] crunchifyKeys = hm.keySet().toArray();
		Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
		System.out.println("Random slang word: " + key + " - " + hm.get(key));
	}
	public static void DoVui1(HashMap<String, String> hm) {
		Object[] crunchifyKeys = hm.keySet().toArray();
		Object[] crunchifyValues = hm.values().toArray();
		Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
		String rightAnswer = hm.get(key);
		List<String> answers = new ArrayList<String>();
		answers.add(rightAnswer);
		for (int i = 0 ; i < 3; i++) {
			Object value = crunchifyValues[new Random().nextInt(crunchifyValues.length)];
			answers.add((String) value);
		}
		Collections.shuffle(answers);
		System.out.println("Nghia cua \"" + key + "\" la?");
		for (int i = 1; i < 5; i++) {
			System.out.println(i + ". " + answers.get(i - 1));
		}
		Scanner sc6 = new Scanner(System.in);
		int option = sc6.nextInt();
		if (answers.get(option - 1).equals(rightAnswer)) {
			System.out.println("Chuc mung ban da tra loi dung!");
		}
		else {
			System.out.println("Ban tra loi sai roi. Dap an la: " + rightAnswer);
		}
	}
	public static void DoVui2(HashMap<String, String> hm) {
		Object[] crunchifyKeys = hm.keySet().toArray();
		Object[] crunchifyValues = hm.values().toArray();
		Object value = crunchifyValues[new Random().nextInt(crunchifyValues.length)];
		Set<Map.Entry<String, String>> set = hm.entrySet();
		String rightAnswer = "";
		for (Map.Entry<String, String> me : set) {
			if (me.getValue().equals((String) value)) {
				rightAnswer = me.getKey();
				break;
			}
		}
		List<String> answers = new ArrayList<String>();
		answers.add(rightAnswer);
		for (int i = 0 ; i < 3; i++) {
			Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
			answers.add((String) key);
		}
		Collections.shuffle(answers);
		System.out.println("Slang word co nghia \"" + value + "\" la?");
		for (int i = 1; i < 5; i++) {
			System.out.println(i + ". " + answers.get(i - 1));
		}
		Scanner sc6 = new Scanner(System.in);
		int option = sc6.nextInt();
		if (answers.get(option - 1).equals(rightAnswer)) {
			System.out.println("Chuc mung ban da tra loi dung!");
		}
		else {
			System.out.println("Ban tra loi sai roi. Dap an la: " + rightAnswer);
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
			System.out.println("7. Reset danh sach slang word goc");
			System.out.println("8. Random slang word");
			System.out.println("9. Do vui 1 (random slang word)");
			System.out.println("10. Do vui 2 (random definition)");
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
			case 7:
				ReadTextFile();
				System.out.println("Reset danh sach slang word thanh cong!");
				break;
			case 8:
				RandomSlangWord(hm);
				break;
			case 9:
				DoVui1(hm);
				break;
			case 10:
				DoVui2(hm);
				break;
			}
		}
	}
}
