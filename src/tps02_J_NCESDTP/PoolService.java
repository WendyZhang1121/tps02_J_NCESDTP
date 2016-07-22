package tps02_J_NCESDTP;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PoolService { 
	private final ExecutorService pool;
	public PoolService(int poolSize) {
		pool = Executors.newFixedThreadPool(poolSize);
	}
	
	public void doSomething() throws InterruptedException, IOException { 
		pool.submit(new SocketReader("somehost", 8080));
		// ...
		List<Runnable> awaitingTasks = pool.shutdownNow();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		PoolService service = new PoolService(5);
		service.doSomething();
	}
}