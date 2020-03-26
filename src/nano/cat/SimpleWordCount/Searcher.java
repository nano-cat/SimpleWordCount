package nano.cat.SimpleWordCount;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

class Searcher implements FileVisitor {		//�Զ�����ĳ��Ŀ¼�����е��ļ�

	private final PathMatcher matcher;	//Ŀ¼ƥ��
	private ArrayList<String> filePaths = new ArrayList<String>();

	public Searcher(String ext) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + ext); //Ŀ¼ƥ��,����glob������ʽƥ��
	}

	public void judgeFile(Path file) throws IOException {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			//System.out.println("Searched file was found: " + name + " in " + file.toRealPath().toString());
			filePaths.add(file.toRealPath().toString());
		}
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		//System.out.println("Visited: " + (Path) dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		judgeFile((Path) file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	
	public ArrayList<String> getFilePaths() {	//���������������ļ��б�
		return filePaths;
	}	
	
}

