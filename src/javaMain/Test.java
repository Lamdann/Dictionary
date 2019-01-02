package javaMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataStructure.Dictionery;

public class Test {

	public static void main(String[] args) {
		final String fileNmae = "book.txt";
		final Dictionery dic = new Dictionery();

		final List<String> words = getTextArray(fileNmae);
		if (words != null) {
			for (final String w : words) {
				dic.addWord(w);
			}
			dic.printWordByPrefix("de");
		}

	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static List<String> getTextArray(String fileNmae) {
		final ArrayList<String> res = new ArrayList<>();
		final URL resours = Test.class.getResource(fileNmae);
		try {
			if (resours != null) {
				final String path = resours.getPath().substring(1);
			if (!Files.isReadable(Paths.get(path))) {
				System.out.println(" file is NOT readeble");
			} else {
				final byte[] sorce = Files.readAllBytes(Paths.get(path));
				final BufferedReader br = new BufferedReader(new StringReader(new String(sorce)));
				String line = "";
				while ((line = br.readLine()) != null) {
					final String[] words = line.split(" ");
					res.addAll(Arrays.asList(words));
				}
			}
			} else {
				System.out.println(" resourse didnt found");
				return null;
			}
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}
}
