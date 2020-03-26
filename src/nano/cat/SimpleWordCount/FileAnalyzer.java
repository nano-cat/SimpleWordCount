package nano.cat.SimpleWordCount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FileAnalyzer {		//�����ļ�����ȡ���ļ��Ĵ�������б�
	private String fileStr;
	
	public FileAnalyzer(String fileStr)		//�ļ�ͳ��
	{
		this.fileStr = fileStr;
	}
	

	public HashMap<String, Word> getWordCount()		//��ȡ�����ļ��ĵ����������ؽ��
	{
		HashMap<String, Word> result = new HashMap<String, Word>();		//���浱ǰһ���ļ��ĵ��������
		
		String line;
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileStr)))) {
			while ((line = in.readLine()) != null) {	//һ��һ�ж�ȡ
				String[] words = line.split(" ");	//���ݿո�ֽ�Ϊ����
				for(String word : words)
				{
					if(null!=word && word.length()>0)	//ÿ�����鳤�ȴ���0˵���е���, null�������ո�ͻ�С�ڵ���0
					{
						if(result.containsKey(word))	//���hashMap������������, ��+1
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
