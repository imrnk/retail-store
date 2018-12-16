package foldertest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FolderCreator {

	
	public static void createFolders() throws IOException {
		
		for(int i  = 0; i < 10; i++) {
			Files.createDirectories(Paths.get("/home/devuser/test"+i, null), null);
		}
		
	}
}
