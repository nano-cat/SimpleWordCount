package nano.cat.SimpleWordCount;


public class Word implements Comparable<Word> {
	private String text;  //单词文本
	private int times;    //单词次数
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}	
	public Word(String text, int times)	{
		this.text = text;
		this.times = times;
	}
	
	
	public int compareTo(Word a) {		//定义两个单词的排序, 逆序输出
		return (-1) * (this.getTimes() - a.getTimes());
	}
}
