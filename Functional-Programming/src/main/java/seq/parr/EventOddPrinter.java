package seq.parr;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EventOddPrinter {
	
	private static IntPredicate evenCon=e->e%2==0;
	private static IntPredicate oddCon=e->e%2!=0;
	
	
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture.runAsync(()->EventOddPrinter.printRange(evenCon));
		CompletableFuture.runAsync(()->EventOddPrinter.printRange(oddCon));
		Thread.sleep(1000);
	}
	private static void printRange(IntPredicate predicate) {
		IntStream.range(1, 100).filter(predicate).forEach(EventOddPrinter::execute);
	}
	
	private static void execute(int x) {
		System.out.println(Thread.currentThread().getName()+" : "+x);
	}

}
