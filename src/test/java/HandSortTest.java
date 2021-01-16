import edu.ib.Card;
import edu.ib.Hand;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class HandSortTest {

    @Test
    public void sortTest(){
        Hand beforeSort = new Hand();
        Hand sortedHand = new Hand();

        Card czworkaKier = new Card(0,3);
        Card damaKier = new Card(0,11);
        Card damaPik = new Card(1,11);
        Card krolKaro = new Card(2,12);
        Card dwojkaTrefl = new Card(3,1);

        //beforeSort.addCardToHand( new Card( 0, 3)); // czworka kier

        beforeSort.addCardToHand(czworkaKier);
        beforeSort.addCardToHand(dwojkaTrefl);
        beforeSort.addCardToHand(damaKier);
        beforeSort.addCardToHand(damaPik);
        beforeSort.addCardToHand(krolKaro);
        beforeSort.sort();

        sortedHand.addCardToHand(czworkaKier);
        sortedHand.addCardToHand(damaKier);
        sortedHand.addCardToHand(damaPik);
        sortedHand.addCardToHand(krolKaro);
        sortedHand.addCardToHand(dwojkaTrefl);

        assertArrayEquals( sortedHand.getCardsOnHand().toArray(),beforeSort.getCardsOnHand().toArray() );
    }
    //Hand przed sortowaniem
    //Hand po sortowaniu "ręcznym"
    //Czy doszło do posortowania kart dla beforeSort?
    //test zaliczony - karty w ręce są posortowane
}
