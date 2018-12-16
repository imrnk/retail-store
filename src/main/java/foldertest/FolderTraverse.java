package foldertest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FolderTraverse {

	public void top(Path topPath) {
		try {

			List<Path> subDirPaths = getSubDirPaths(topPath);
			long subDirCount = subDirPaths.size() - 1;
			System.out.println(subDirCount);

			// Create #count executors
			ExecutorService executors = Executors.newFixedThreadPool((int) subDirCount);

			// Stream<Future<Integer>> futures =

			 Stream<Path> pathStream = subDirPaths.stream()
					 .map(p -> executors.submit(new FolderActor(p)))
					 .flatMap(futures -> {
				Stream<Path> futureStream = null;
				try {
					futureStream =  futures.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				return futureStream;
			});
			print(pathStream);
			executors.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public void print(Stream<Path> paths) {
		paths.forEach(p -> System.out.println("!!!!"));
	}
	
	public List<Path> getSubDirPaths(Path path) throws IOException {
		return Files.walk(path, 1).collect(Collectors.toList());
	}

	class FolderActor implements Callable<Stream<Path>> {

		final private Path pp;

		FolderActor(Path p) {
			this.pp = p;
		}

		@Override
		public Stream<Path> call() throws Exception {

			try {
				System.out.println(Thread.currentThread().getName());
				System.out.println(pp.getFileName());
				System.out.println("======================");
				Stream<Path> walked = Files.list(pp);
				List<Path> lPath = walked.collect(Collectors.toList());
				List<Path> copiedPaths = new ArrayList<>();
				Collections.copy(lPath, copiedPaths);
				lPath.forEach(w -> System.out.println(w.getFileName()));
				System.out.println("=====================");
				System.out.println();
				System.out.println("=====================");
				return copiedPaths.stream();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

	}

}
