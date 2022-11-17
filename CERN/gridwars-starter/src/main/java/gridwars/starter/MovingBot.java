package gridwars.starter;

import cern.ais.gridwars.api.Coordinates;
import cern.ais.gridwars.api.UniverseView;
import cern.ais.gridwars.api.bot.PlayerBot;
import cern.ais.gridwars.api.command.MovementCommand;

import java.util.List;


/**
 * Simple bot that only moves into one direction
 */
public class MovingBot implements PlayerBot {

	@Override
	public void getNextCommands(UniverseView universeView, List<MovementCommand> movementCommands) {
		List<Coordinates> myCells = universeView.getMyCells();
		int tura = universeView.getCurrentTurn();
		for (Coordinates cell : myCells) {
			int population = universeView.getPopulation(cell);
			if(tura <= 2){
				MovementCommand movementCommand = new MovementCommand(cell, MovementCommand.Direction.RIGHT, 24);
				MovementCommand movementCommand2 = new MovementCommand(cell, MovementCommand.Direction.LEFT, 24);
				MovementCommand movementCommand3 = new MovementCommand(cell, MovementCommand.Direction.DOWN, 24);
				MovementCommand movementCommand4 = new MovementCommand(cell, MovementCommand.Direction.UP, 24);
				movementCommands.add(movementCommand);
				movementCommands.add(movementCommand2);
				movementCommands.add(movementCommand3);
				movementCommands.add(movementCommand4);
			}else if(population > (4.0 / (universeView.getGrowthRate() - 1))) {
				int split = 1;
				int partner = 0;
				// Check left, right, up, down for cells that don't belong to me
				for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {
					if (!universeView.belongsToMe(cell.getNeighbour(direction))) {
						split++;
					}else{
						partner++;
					}
				}

				// Expand
				for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {
					if (!universeView.belongsToMe(cell.getNeighbour(direction))) {
						movementCommands.add(new MovementCommand(cell, direction, population / split));
					}else{
						movementCommands.add(new MovementCommand(cell, direction, universeView.getPopulation(cell)));
					}
				}
			}

			//universeView.log("Tura "+universeView.getCurrentTurn()+" wartosc komorki "+cell+" "+population);
//			else if (population > 4) {
//				boolean czyBylLepszy = false;
//				// Check left, right, up, down for cells that don't belong to me
////				for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {
////					int split = 1;
////					if (!universeView.belongsToMe(cell.getNeighbour(direction))) {
////
////						if (universeView.isEmpty(cell.getNeighbour(direction))) {
////							split++;
////						} else {
////							movementCommands.add(new MovementCommand(cell, direction, population / split));
////							czyBylLepszy = true;
////							break;
////						}
////					}
////				}
//				if (czyBylLepszy == false) {
//
//					for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {
//						if (universeView.belongsToMe(cell.getNeighbour(direction))) {
//							movementCommands.add(new MovementCommand(cell, direction, population / 4));
//
//						}else{
//							movementCommands.add(new MovementCommand(cell, direction, population ));
//							break;
//						}
//
//
//					}
//					//dodac warunek czy pole nie jest nasze bo nie ma sensu sie wracac na wlasne pola
////					MovementCommand movementCommand = new MovementCommand(cell, MovementCommand.Direction.RIGHT, population / 4 - 1);
////					MovementCommand movementCommand2 = new MovementCommand(cell, MovementCommand.Direction.LEFT, population / 4 - 1);
////					MovementCommand movementCommand3 = new MovementCommand(cell, MovementCommand.Direction.DOWN, population / 4 - 1);
////					MovementCommand movementCommand4 = new MovementCommand(cell, MovementCommand.Direction.UP, population / 4 - 1);
////					movementCommands.add(movementCommand);
////					movementCommands.add(movementCommand2);
////					movementCommands.add(movementCommand3);
////					movementCommands.add(movementCommand4);
//					//universeView.log("Ruch w turze: " + universeView.getCurrentTurn());
//
//				}
//			}

		}
	}
}
