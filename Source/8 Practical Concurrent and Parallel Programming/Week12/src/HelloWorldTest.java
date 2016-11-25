import java.io.*;
import akka.actor.*;

public class HelloWorldTest {
	public static void main(String[] args){
		 final ActorSystem system = ActorSystem.create("HelloWorldSystem");
		 final ActorRef myactor =
		 system.actorOf(Props.create(MyActor.class), "myactor");
		 myactor.tell(new MyMessage("hello"), ActorRef.noSender());
		 myactor.tell(new MyMessage("world"), ActorRef.noSender());
		 try {
		 System.out.println("Press return to terminate...");
		 System.in.read();
		 } catch(IOException e) {
		 e.printStackTrace();
		 } finally {
		 system.terminate();
		 }
	}
}

// -- MESSAGE --------------------------------------------------
class MyMessage implements Serializable { // must be Serializable:
 private static final long serialVersionUID = 123;
 public final String s;
 public MyMessage(String s) { this.s = s; }
}
// -- ACTOR --------------------------------------------------

class MyActor extends UntypedActor {
// can have (local) state
 private int count = 0; 
 public void onReceive(Object o) throws Exception { // reacting to message:
if (o instanceof MyMessage) {
 MyMessage message = (MyMessage) o;
 System.out.println(message.s + " (" + count + ")");
 count++;
}
 }
} 

