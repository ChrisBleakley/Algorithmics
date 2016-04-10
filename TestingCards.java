import java.util.List;

public class TestingCards {

	// Creates the territories, assigning them the owners as determined in
	// arraylist.
	// List<Territory> card_list = PlayGame.buildCards();
	// Shuffles the deck of PlayingCards
	// List<Integer> cardsList = PlayGame.shuffleTheDeck();

	// PlayGame play =new PlayGame();

	public static void main(String[] args) {

		Territory card_territory = null;
		System.out.println("unshuffled deck;");
		for (int i = 0; i < 44; i++) {
			card_territory = PlayGame.buildCards().get(i);
			System.out.println(i + " Card Country: " + card_territory.getName() + " Insignia Short Name: "
					+ card_territory.getShortName() + "  Insignia: " + card_territory.getInsignia());

		}

		System.out.println("the shuffled");

		List<Integer> deck = PlayGame.shuffleTheDeck();

		int i = 0;
		while (i < 44) {

			System.out.println(deck.get(i));

			i++;

		}

		i = 0;
		while (i < 42) {
			i++;
			if (i == 2) {
				System.out.println("the shuffled card at postion  2");
				System.out.println(deck.get(i) + " is the card at postition 2  ");
			}

		}

	}

}