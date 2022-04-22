package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class WordFile {
	
	public static File file;
	public static File pointFile;
	private Vector<String> words = new Vector<String>();
	private Vector<String> points = new Vector<String>();
	

	public WordFile() {
		openFile();
	}

	public void openFile() {
		file = new File("src/textFile/words.txt");
		pointFile = new File("src/textFile/points.txt");
			try {
				boolean success = file.createNewFile();
				pointFile.createNewFile();
			} catch (IOException e) {
				return;
		}
	}
		//파일의 단어를 읽는 함수
	public void readFile1() {
		try {
			Scanner fscanner = new Scanner(new
			FileReader(file));
			while(fscanner.hasNext()) {
				String word = fscanner.nextLine();
				words.add(word.trim());
			}
			fscanner.close();
		} catch (FileNotFoundException e) {
		
			return;
		}
	}
	// 포인트 파일을 읽고 points 벡터에 저장
	public void readFile2() {
		try {
			Scanner fscanner = new Scanner(new FileReader(pointFile));
			while(fscanner.hasNext()) {
				String word = fscanner.nextLine();
				points.add(word.trim());
			}
			fscanner.close();
		} catch (FileNotFoundException e) {
			return;
		}
	}
		
	public void wirteFile(String word, File file) {
			
		FileWriter fw = null;
			
		try {			
			fw = new FileWriter(file, true);			
		} catch (IOException e1) {			
			e1.printStackTrace();			
		}
			
		try {
			
			fw.write(word + '\n');
			fw.flush();					
			fw.close();
			
		} catch (IOException e) {
			return;
		}
	}
	public Vector<String> getwords()
	{
		return this.words;
	}
	public Vector<String> getPoints()
	{
		return this.points;
	}
}
