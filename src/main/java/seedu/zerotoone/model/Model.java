package seedu.zerotoone.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.zerotoone.commons.core.GuiSettings;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getExerciseListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setExerciseListFilePath(Path exerciseListFilePath);

    /**
     * Replaces address book data with the data in {@code exerciseList}.
     */
    void setExerciseList(ReadOnlyExerciseList exerciseList);

    /** Returns the ExerciseList */
    ReadOnlyExerciseList getExerciseList();

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the address book.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Deletes the given exercise.
     * The exercise must exist in the address book.
     */
    void deleteExercise(Exercise target);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in the address book.
     */
    void addExercise(Exercise exercise);

    /**
     * Replaces the given exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the address book.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in the address book.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /** Returns an unmodifiable view of the filtered exercise list */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Updates the filter of the filtered exercise list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);
}