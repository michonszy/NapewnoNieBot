package gridwars.starter;

import cern.ais.gridwars.api.Coordinates;
import cern.ais.gridwars.api.UniverseView;
import cern.ais.gridwars.api.bot.PlayerBot;
import cern.ais.gridwars.api.command.MovementCommand;

import java.util.List;


/**
 * Simple bot that only moves into one direction
 */
public class SzachownicaBot implements PlayerBot {

    @Override
    public void getNextCommands(UniverseView universeView, List<MovementCommand> movementCommands) {
        List<Coordinates> myCells = universeView.getMyCells();
        int runda = universeView.getCurrentTurn();
        if(runda<20){
            for (Coordinates cell : myCells) {
                int population = universeView.getPopulation(cell);
                int doPodzialu = population-5;

                    if(population>5){
                        movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.RIGHT, doPodzialu/4));
                        movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.LEFT, doPodzialu/4));
                        movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.UP, doPodzialu/4));
                        movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.DOWN, doPodzialu/4));


                    }



            }
        }else if(runda<50){
            for (Coordinates cell : myCells) {
                int population = universeView.getPopulation(cell);
                int doPodzialu = population-5;

                if(population>5){
                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.UP, doPodzialu/2));

                }



            }
        }else if(runda<200) {
            for (Coordinates cell : myCells) {
                int population = universeView.getPopulation(cell);
                int pulaDoRozdania = population-5;
                if (population>7){
                    int X = cell.getX();
                    int Y = cell.getY();
                    if(Y>25){
                        MovementCommand movementCommand = new MovementCommand(cell, MovementCommand.Direction.RIGHT, pulaDoRozdania / 2);
                        movementCommands.add(movementCommand);
                    }else{
                        MovementCommand movementCommand2 = new MovementCommand(cell, MovementCommand.Direction.LEFT, pulaDoRozdania / 2 );
                        movementCommands.add(movementCommand2);
                    }
                    if(X>25){
                        MovementCommand movementCommand3 = new MovementCommand(cell, MovementCommand.Direction.DOWN, pulaDoRozdania / 2 );
                        movementCommands.add(movementCommand3);
                    }else{
                        MovementCommand movementCommand4 = new MovementCommand(cell, MovementCommand.Direction.UP, pulaDoRozdania / 2 );
                        movementCommands.add(movementCommand4);
                    }



                }

            }
        }else{

            for (Coordinates cell : myCells) {
                int population = universeView.getPopulation(cell);
                if (population > 4) {
                    boolean czyBylLepszy = false;
                    int split=0;
                    for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {
                        if (!universeView.belongsToMe(cell.getNeighbour(direction))) {
                            if (universeView.isEmpty(cell.getNeighbour(direction))) {
                                split++;
                            } else {
                                movementCommands.add(new MovementCommand(cell, direction, population));
                                czyBylLepszy = true;
                                break;
                            }
                        }
                    }
                    if (czyBylLepszy == false) {
                        for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {

                            movementCommands.add(new MovementCommand(cell, direction, population/4));

                        }
                    }

                }else if (population > 3) {

                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.RIGHT, population/3));
                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.LEFT, population/3));
                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.UP, population/3));
                } else if (population > 2) {
                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.RIGHT, population/2));
                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.LEFT, population/2));
                }
                else if (population > 1) {
                    movementCommands.add(new MovementCommand(cell, MovementCommand.Direction.UP, population));

                }



                // 714
            }
        }
    }
}

