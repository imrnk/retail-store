package foldertest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
			FolderTraverse ft = new FolderTraverse();
			Path  p = Paths.get("/home/devuser/test");
			ft.top(p);

	}

}
