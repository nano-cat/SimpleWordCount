package nano.cat.SimpleWordCount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileAnalyzer {		//分析文件，获取该文件的词语次数列表
	private String fileStr;
	
	public FileAnalyzer(String fileStr)		//文件统计
	{
		this.fileStr = fileStr;
	}
	

	public HashMap<String, Word> getWordCount()		//获取传入文件的单词总数返回结果
	{
		HashMap<String, Word> result = new HashMap<String, Word>();		//储存当前一个文件的单词数结果
		
		String line;
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileStr)))) {
			while ((line = in.readLine()) != null) {	//一行一行读取
				String[] words = line.split(" ");	//根据空格分解为数组
				for(String word : words)
				{
					if(null!=word && word.length()>0)	//每个数组长度大于0说明有单词, null和连续空格就会小于等于0
					{
						if(result.containsKey(word))	//如果hashMap里包含这个单词, 就+1
						{
							Word w = result.get(word);
							w.setTimes(w.getTimes() + 1);
						}
						else							
						{
							result.put(word, new Word(word, 1));
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return result;
	}
}
