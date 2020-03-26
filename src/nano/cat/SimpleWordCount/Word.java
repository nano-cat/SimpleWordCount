package nano.cat.SimpleWordCount;


public class Word implements Comparable<Word> {
	private String text;  //�����ı�
	private int times;    //���ʴ���
	
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
	
	
	public int compareTo(Word a) {		//�����������ʵ�����, �������
		return (-1) * (this.getTimes() - a.getTimes());
	}
}
