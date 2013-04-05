public class TestObjects {

	public static void main(String[] args) {
		
		//testing deck creation
		System.out.println("Creating deck of 54 cards...");
		Deck testdeck = new Deck();
		System.out.println("Deck size: " + testdeck.size());
		testdeck.showDeck();
		
		//gets top card and more tests on deck
		System.out.println("Removing top card...");
		Card tempcard = testdeck.getTopCard();
		System.out.println("Deck size: " + testdeck.size());
		testdeck.showDeck();
		System.out.println("Re-adding card...");
		testdeck.addCard(tempcard);
		System.out.println("Deck size: " + testdeck.size());
		testdeck.showDeck();
		System.out.println("Shuffling deck...");
		testdeck.shuffle();
		System.out.println("Deck size: " + testdeck.size());
		testdeck.showDeck();
		System.out.println("Removing top card...");
		tempcard = testdeck.getTopCard();
		System.out.println("Deck size: " + testdeck.size());
		testdeck.showDeck();
		System.out.println("Re-adding card TO TOP...");
		testdeck.addTopCard(tempcard);
		System.out.println("Deck size: " + testdeck.size());
		testdeck.showDeck();
		
		//now for hand testing
		System.out.println("Creating 4-card hand...");
		Hand p1hand = new Hand(1);
		Hand p2hand = new Hand(2);
		System.out.println("Adding cards to hands...");
		for (int i = 0; i < 4; i++) {
			tempcard = testdeck.getTopCard();
			p1hand.addCard(tempcard);
			tempcard = testdeck.getTopCard();
			p2hand.addCard(tempcard);
		}
		System.out.println("Deck: ");
		testdeck.showDeck();
		System.out.println("Player 1 Hand: ");
		p1hand.showHand();
		System.out.println("Player 2 Hand: ");
		p2hand.showHand();
		if (p2hand.size() == 4)
		{
			System.out.println("P1Hand is proper size");
		}  else {
			System.out.println("Something's wrong with the hand.");
		}
		if (p2hand.size() == 4)
		{
			System.out.println("P2Hand is proper size");
		}  else {
			System.out.println("Something's wrong with the hand.");
		}
		System.out.println("Replacing first card in each hand with top card of deck...");
		tempcard = testdeck.getTopCard();
		Card tempcard2 = p1hand.replaceCard(0, tempcard);
		testdeck.addCard(tempcard2);
		
		tempcard = testdeck.getTopCard();
		tempcard2 = p2hand.replaceCard(0, tempcard);
		testdeck.addCard(tempcard2);
		
		System.out.println("Deck: ");
		testdeck.showDeck();
		System.out.println("Player 1 Hand: ");
		p1hand.showHand();
		System.out.println("Player 2 Hand: ");
		p2hand.showHand();
		
		//now for card
		tempcard = testdeck.getTopCard();
		System.out.print("Top card of deck: ");
		System.out.println(tempcard);
		if (tempcard.isSpecial()) {
			System.out.println("This card is a special card!");
			System.out.println(tempcard.getSpecial());
		} else {
			System.out.println("This card is an ordinary number.");
			System.out.println(tempcard.getRank());
		}
		System.out.println(tempcard.getPictureName());
		
		//lastly, Player
		Player p1 = new Player(true, 0, "Human", p1hand);
		Player p2 = new Player(false, 1, "Computer", p2hand);
		
		
		
		
	}
	
}