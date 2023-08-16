package com.company;

import com.company.command.*;
import com.company.operator.BCommandCanvasOperator;
import com.company.operator.CCommandCanvasOperator;
import com.company.operator.LCommandCanvasOperator;
import com.company.operator.RCommandCanvasOperator;

import static com.company.Constants.*;

public class CommandManager {

    CCommand cCommand;
    LCommand lCommand;
    RCommand rCommand;
    BCommand bCommand;
    QCommand qCommand;

    CCommandCanvasOperator cCommandCanvasOperator;
    LCommandCanvasOperator lCommandCanvasOperator;
    BCommandCanvasOperator bCommandCanvasOperator;
    RCommandCanvasOperator rCommandCanvasOperator;

    Canvas canvas = new Canvas();

    public void executeCommand(String[] command) throws CustomException {
        switch (command[0]) {
            case C_COMMAND:
                executeCCommand(command);
                break;
            case L_COMMAND:
                executeLCommand(command);
                break;
            case R_COMMAND:
                executeRCommand(command);
                break;
            case B_COMMAND:
                executeBCommand(command);
                break;
            case Q_COMMAND:
                executeQCommand(command);
                break;
            default:
                throw new CustomException(ERROR_INVALID_COMMAND);
        }
    }

    private void executeCCommand(String[] command) throws CustomException {
        if (command.length == 3) {
            if (cCommand == null) {
                cCommandCanvasOperator = new CCommandCanvasOperator(canvas);
                cCommand = new CCommand(cCommandCanvasOperator);
            }
            cCommand.execute(command[1], command[2]);
        } else {
            throw new CustomException(ERROR_INVALID_NUM_OF_ARGUMENTS);
        }
    }

    private void executeLCommand(String[] command) throws CustomException {
        if (command.length == 5) {
            if (lCommand == null) {
                if (lCommandCanvasOperator == null) {
                    lCommandCanvasOperator = new LCommandCanvasOperator(canvas);
                }
                lCommand = new LCommand(lCommandCanvasOperator);
            }
            lCommand.execute(command[1], command[2], command[3], command[4]);
            lCommand.printCanvas();
        } else {
            throw new CustomException(ERROR_INVALID_NUM_OF_ARGUMENTS);
        }
    }

    private void executeRCommand(String[] command) throws CustomException {
        if (command.length == 5) {
            if (lCommand == null) {
                if (lCommandCanvasOperator == null) {
                    lCommandCanvasOperator = new LCommandCanvasOperator(canvas);
                }
                lCommand = new LCommand(lCommandCanvasOperator);
            }
            if (rCommand == null) {
                rCommandCanvasOperator = new RCommandCanvasOperator(canvas);
                rCommand = new RCommand(rCommandCanvasOperator, lCommand);
            }
            rCommand.execute(command[1], command[2], command[3], command[4]);
        } else {
            throw new CustomException(ERROR_INVALID_NUM_OF_ARGUMENTS);
        }
    }

    private void executeBCommand(String[] command) throws CustomException {
        if (command.length == 4) {
            if (bCommand == null) {
                bCommandCanvasOperator = new BCommandCanvasOperator(canvas);
                bCommand = new BCommand(bCommandCanvasOperator);
            }
            bCommand.execute(command[1], command[2], command[3]);
        } else {
            throw new CustomException(ERROR_INVALID_NUM_OF_ARGUMENTS);
        }
    }

    private void executeQCommand(String[] command) throws CustomException {
        if (command.length == 1) {
            if (qCommand == null) {
                qCommand = new QCommand();
            }
            qCommand.execute();
        } else {
            throw new CustomException(ERROR_INVALID_NUM_OF_ARGUMENTS);
        }
    }
}
