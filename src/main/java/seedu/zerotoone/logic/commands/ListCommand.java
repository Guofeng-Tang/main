package seedu.zerotoone.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.zerotoone.model.Model.PREDICATE_SHOW_ALL_EXERCISES;

import seedu.zerotoone.model.Model;

/**
 * Lists all exercises in the zerotoone book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all exercises";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
