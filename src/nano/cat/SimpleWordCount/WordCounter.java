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
	
		//ָ��Ŀ¼·�����ļ���չ��
		Path fileTree = Paths.get("src/nano/cat/SimpleWordCount/word/");	//��ȡ�ļ�Ŀ¼
		Searcher walk = new Searcher("*.txt");	//����������ʽƥ���ļ�
		//���Ҹ�Ŀ¼�����е�txt�ļ�
		
		//��������õ�ʱjava7������� FileSystem �ķ���, ��������ȵݹ��Ч
		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Files.walkFileTree(fileTree, opts, Integer.MAX_VALUE, walk);
		ArrayList<String> filePaths = walk.getFilePaths();	//��ȡ������txt�ļ���·��
		
		//����ÿ���ļ��ĵ���
		
		HashMap<String, Word> totalMap = new HashMap<String, Word>(); 	//���еĵ�����������������
		
		for(String str:filePaths)	//�����е��ļ�·�����б���
		{
			HashMap<String, Word> partMap = new FileAnalyzer(str).getWordCount(); //�����������ļ�, �����е��ʵĽ��ȫ������partMap����
			if(partMap != null && partMap.size() > 0)
			{
				combineMap(totalMap, partMap);	//��ÿ�������ļ��Ľ��, ȫ���ŵ�totalMap����, �൱�ڷ��ļ��Ľ���ŵ�ȫ���ļ��Ľ������ȥ, ���ϲ�����Map
			}
		}
		
		//����
		//Collection<Word> words = totalMap.values();
		ArrayList<Word> words = new ArrayList<Word>(totalMap.values());	//�����е��ʵ�������������
		Collections.sort(words);
		
		//���, ��������������
		System.out.println("�����");
		for(Word w : words)
		{
			System.out.println(w.getText() + ", " + w.getTimes());
		}
	}
	
	
	public static void combineMap(HashMap<String, Word> total, HashMap<String, Word> part)	//�ϲ�����HashMap
	{
		Iterator<Entry<String, Word>> iter = part.entrySet().iterator();	
		while(iter.hasNext()) {
		    Map.Entry<String, Word> entry = iter.next();
		    // ��ȡkey
		    String partKey = entry.getKey();
		    // ��ȡvalue
		    Word partWord = entry.getValue();
		    if(total.containsKey(partKey))	//���total�������key�� �������ۼ�
		    {
		    	Word totalWord = total.get(partKey);
		    	totalWord.setTimes(totalWord.getTimes() + partWord.getTimes());
		    }
		    else	//���û��
		    {
		    	total.put(partKey, partWord);
		    }
		}
	}

}
