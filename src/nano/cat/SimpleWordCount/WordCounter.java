package nano.cat.SimpleWordCount;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class WordCounter {

	public static void main(String[] args) throws IOException {
	
		//指定目录路径及文件扩展名
		Path fileTree = Paths.get("src/nano/cat/SimpleWordCount/word/");	//获取文件目录
		Searcher walk = new Searcher("*.txt");	//根据正则表达式匹配文件
		//查找该目录下所有的txt文件
		
		//在这里采用的时java7新引入的 FileSystem 的方法, 这个方法比递归高效
		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Files.walkFileTree(fileTree, opts, Integer.MAX_VALUE, walk);
		ArrayList<String> filePaths = walk.getFilePaths();	//获取到所有txt文件的路径
		
		//解析每个文件的单词
		
		HashMap<String, Word> totalMap = new HashMap<String, Word>(); 	//所有的单词总数都放在这里
		
		for(String str:filePaths)	//对所有的文件路径进行遍历
		{
			HashMap<String, Word> partMap = new FileAnalyzer(str).getWordCount(); //解析进来的文件, 把所有单词的结果全部放在partMap里面
			if(partMap != null && partMap.size() > 0)
			{
				combineMap(totalMap, partMap);	//把每个单独文件的结果, 全部放到totalMap里面, 相当于分文件的结果放到全局文件的结果里面去, 即合并两个Map
			}
		}
		
		//排序
		//Collection<Word> words = totalMap.values();
		ArrayList<Word> words = new ArrayList<Word>(totalMap.values());	//将所有单词的总数进行排序
		Collections.sort(words);
		
		//输出, 按次数降序排列
		System.out.println("最后结果");
		for(Word w : words)
		{
			System.out.println(w.getText() + ", " + w.getTimes());
		}
	}
	
	
	public static void combineMap(HashMap<String, Word> total, HashMap<String, Word> part)	//合并两个HashMap
	{
		Iterator<Entry<String, Word>> iter = part.entrySet().iterator();	
		while(iter.hasNext()) {
		    Map.Entry<String, Word> entry = iter.next();
		    // 获取key
		    String partKey = entry.getKey();
		    // 获取value
		    Word partWord = entry.getValue();
		    if(total.containsKey(partKey))	//如果total包含这个key则 将次数累加
		    {
		    	Word totalWord = total.get(partKey);
		    	totalWord.setTimes(totalWord.getTimes() + partWord.getTimes());
		    }
		    else	//如果没有
		    {
		    	total.put(partKey, partWord);
		    }
		}
	}

}
