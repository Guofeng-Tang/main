package seedu.zerotoone.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.logic.commands.CommandResult;
import seedu.zerotoone.logic.commands.exceptions.CommandException;
import seedu.zerotoone.logic.parser.exceptions.ParseException;
import seedu.zerotoone.model.ReadOnlyAddressBook;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ExerciseList.
     *
     * @see seedu.zerotoone.model.Model#getExerciseList()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of exercises */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Returns the user prefs' zerotoone book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
