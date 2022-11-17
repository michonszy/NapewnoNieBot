package gridwars.starter;

import cern.ais.gridwars.Emulator;


/**
 * Instantiates the example bots and starts the game emulator.
 */
public class EmulatorRunner {

    public static void main(String[] args) {
        SzachownicaBot blueBot = new SzachownicaBot();
        MindOfBot redBot = new MindOfBot();

        Emulator.playMatch(blueBot, redBot);
    }
}
