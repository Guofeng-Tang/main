package seedu.zerotoone.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.UniqueExerciseList;

/**
 * Wraps all data at the zerotoone-book level
 * Duplicates are not allowed (by .isSameExercise comparison)
 */
public class ExerciseList implements ReadOnlyAddressBook {

    private final UniqueExerciseList exercises;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        exercises = new UniqueExerciseList();
    }

    public ExerciseList() {}

    /**
     * Creates an ExerciseList using the Exercises in the {@code toBeCopied}
     */
    public ExerciseList(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the exercise list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Resets the existing data of this {@code ExerciseList} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());
    }

    //// exercise-level operations

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the zerotoone book.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Adds a exercise to the zerotoone book.
     * The exercise must not already exist in the zerotoone book.
     */
    public void addExercise(Exercise p) {
        exercises.add(p);
    }

    /**
     * Replaces the given exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the zerotoone book.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in the
     * zerotoone book.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exercises.setExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code ExerciseList}.
     * {@code key} must exist in the zerotoone book.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return exercises.asUnmodifiableObservableList().size() + " exercises";
        // TODO: refine later
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseList // instanceof handles nulls
                && exercises.equals(((ExerciseList) other).exercises));
    }

    @Override
    public int hashCode() {
        return exercises.hashCode();
    }
}
