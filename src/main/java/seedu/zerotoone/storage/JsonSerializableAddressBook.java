package seedu.zerotoone.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.model.ExerciseList;
import seedu.zerotoone.model.ReadOnlyAddressBook;
import seedu.zerotoone.model.exercise.Exercise;

/**
 * An Immutable ExerciseList that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_EXERCISE = "Exercises list contains duplicate exercise(s).";

    private final List<JsonAdaptedExercise> exercises = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given exercises.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("exercises") List<JsonAdaptedExercise> exercises) {
        this.exercises.addAll(exercises);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        exercises.addAll(source.getExerciseList().stream().map(JsonAdaptedExercise::new).collect(Collectors.toList()));
    }

    /**
     * Converts this zerotoone book into the model's {@code ExerciseList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExerciseList toModelType() throws IllegalValueException {
        ExerciseList exerciseList = new ExerciseList();
        for (JsonAdaptedExercise jsonAdaptedExercise : exercises) {
            Exercise exercise = jsonAdaptedExercise.toModelType();
            if (exerciseList.hasExercise(exercise)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EXERCISE);
            }
            exerciseList.addExercise(exercise);
        }
        return exerciseList;
    }

}
