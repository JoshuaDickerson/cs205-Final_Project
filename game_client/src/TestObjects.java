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
		System.out.println("Player 1 Hand: ");
		p2hand.showHand();
		
		
		
		
		
	}
	
}