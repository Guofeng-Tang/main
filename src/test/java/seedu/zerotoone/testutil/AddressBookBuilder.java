package seedu.zerotoone.testutil;

import seedu.zerotoone.model.ExerciseList;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ExerciseList ab = new AddressBookBuilder().withExercise("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ExerciseList exerciseList;

    public AddressBookBuilder() {
        exerciseList = new ExerciseList();
    }

    public AddressBookBuilder(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

    /**
     * Adds a new {@code Exercise} to the {@code ExerciseList} that we are building.
     */
    public AddressBookBuilder withExercise(Exercise exercise) {
        exerciseList.addExercise(exercise);
        return this;
    }

    public ExerciseList build() {
        return exerciseList;
    }
}
