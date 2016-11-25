import java.util.Random; import java.io.*; import akka.actor.*;

// -- MESSAGES --------------------------------------------------
class StartTransferMessage implements Serializable { 
	private final ActorRef bank;
	private final ActorRef from;
	private final ActorRef to;

	public StartTransferMessage(ActorRef bank, ActorRef from, ActorRef to){
		this.bank = bank;
		this.from = from;
		this.to = to;
	}

	public ActorRef getBank(){return bank;}
	public ActorRef getFrom(){return from;}
	public ActorRef getTo(){return to;}
}

class TransferMessage implements Serializable { 
	private final int amount;
	private final ActorRef from;
	private final ActorRef to;

	public TransferMessage(int amount, ActorRef from, ActorRef to){
		this.amount = amount;
		this.from = from;
		this.to = to;
	}

	public ActorRef getFrom(){return from;}
	public ActorRef getTo(){return to;}
	public int getAmount(){return amount;}
}

class DepositMessage implements Serializable {
	private final int amount;
	
	public DepositMessage(int amount){
		this.amount = amount;
	}

	public int getAmount(){return amount;}	
}

class PrintBalanceMessage implements Serializable { }

// -- ACTORS --------------------------------------------------
class AccountActor extends UntypedActor { 
	private int balance;

	public AccountActor(int balance){
		this.balance = balance;
	}

	public void onReceive(Object o){
		if(o instanceof DepositMessage){
			DepositMessage message = (DepositMessage)o;
			balance += message.getAmount();
		} else if(o instanceof PrintBalanceMessage){
			System.out.println(balance);
		}
	}
}

class BankActor extends UntypedActor {
	public void onReceive(Object o){
		if(o instanceof TransferMessage){
			TransferMessage message = (TransferMessage)o;
			message.getFrom().tell(new DepositMessage(-1 * message.getAmount()), ActorRef.noSender());
			message.getTo().tell(new DepositMessage(message.getAmount()), ActorRef.noSender());
		}
	}
}

class ClerkActor extends UntypedActor { 
	private Random random;

	private void ntransfers(int n, ActorRef bank, ActorRef from, ActorRef to){
		if(n == 0) return;
		else {
			int r = random.nextInt(100);
			bank.tell(new TransferMessage(r, from, to), ActorRef.noSender());
			ntransfers(n-1, bank, from, to);
		}
	}

	public void onReceive(Object o){
		if(o instanceof StartTransferMessage){
			StartTransferMessage message = (StartTransferMessage)o;
			random = new Random();
			ntransfers(100, message.getBank(), message.getFrom(), message.getTo());
		}
	}
}

// -- MAIN --------------------------------------------------
public class ABC { // Demo showing how things work:
 	public static void main(String[] args) {
		 final ActorSystem system = ActorSystem.create("ABCSystem");

		 final ActorRef c1 = system.actorOf(Props.create(ClerkActor.class), "c1");
		 final ActorRef c2 = system.actorOf(Props.create(ClerkActor.class), "c2");
		 final ActorRef b1 = system.actorOf(Props.create(BankActor.class), "b1");
		 final ActorRef b2 = system.actorOf(Props.create(BankActor.class), "b2");
		 final ActorRef a1 = system.actorOf(Props.create(AccountActor.class, 0), "a1");
		 final ActorRef a2 = system.actorOf(Props.create(AccountActor.class, 0), "a2");

		 c1.tell(new StartTransferMessage(b1, a1, a2), ActorRef.noSender());
		 c2.tell(new StartTransferMessage(b2, a2, a1), ActorRef.noSender());

		 try {
			 System.out.println("Press return to inspect...");
			 System.in.read();

			 a1.tell(new PrintBalanceMessage(), ActorRef.noSender());
			 a2.tell(new PrintBalanceMessage(), ActorRef.noSender());

			 System.out.println("Press return to terminate...");
			 System.in.read();
		 } catch(IOException e) {
		 	e.printStackTrace();
		 } finally {
		 	system.terminate();
		 }
 	}
} 