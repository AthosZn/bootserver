package concurrency;

/**
 * 先获取锁的线程，对 x 赋值之后释放锁，另外一个再获取锁，一定能看到对 x 赋值的改动，就是这么简单，请小伙伴用下面命令查看上面程序，看同步块和同步方法被转换成汇编指令有何不同？
 *
 * javap -c -v Synchronizedzn
 * 这和 synchronized 的语义相关，小伙伴可以先自行了解一下，锁的内容时会做详细说明
 */
public class Happenbeforezn {
	private int x = 0;

	public void synBlock(){
		int x=1;
		int y=2;
		int z=3;
	}


}
