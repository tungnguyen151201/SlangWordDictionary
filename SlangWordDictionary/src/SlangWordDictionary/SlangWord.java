package SlangWordDictionary;

import java.io.Serializable;

public class SlangWord implements Serializable{
	private String slag;
	private String meaning;
	public String getSlag() {
		return slag;
	}
	public void setSlag(String slag) {
		this.slag = slag;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	SlangWord(String slag, String meaning) {
		this.slag = slag;
		this.meaning = meaning;
	}
	public void Print() {
		System.out.println(slag + ": " + meaning);
	}
}
