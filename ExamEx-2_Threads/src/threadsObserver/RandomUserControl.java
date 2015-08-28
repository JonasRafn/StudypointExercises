package threadsObserver;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import randomperson.RandomUser;
import randomperson.RandomUserGenerator;

public class RandomUserControl extends Observable implements Runnable{

    private RandomUser user;

    public void fetchRandomUser() {

    }

    @Override
    public void run() {
        RandomUser user = null;
        try {
            user = RandomUserGenerator.getRandomUser();
        } catch (InterruptedException ex) {
            Logger.getLogger(RandomUserControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        setChanged();           //In the observer pattern, we want to automatically update the observers
        notifyObservers(user);  //therefore, instead og using a return method we use notifyobservers().
    }

}
