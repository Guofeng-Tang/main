package seedu.zerotoone.testutil;

import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.zerotoone.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.zerotoone.logic.commands.AddCommand;
import seedu.zerotoone.logic.commands.EditCommand;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.tag.Tag;

/**
 * A utility class for Exercise.
 */
public class ExerciseUtil {

    /**
     * Returns an add command string for adding the {@code exercise}.
     */
    public static String getAddCommand(Exercise exercise) {
        return AddCommand.COMMAND_WORD + " " + getExerciseDetails(exercise);
    }

    /**
     * Returns the part of command string for the given {@code exercise}'s details.
     */
    public static String getExerciseDetails(Exercise exercise) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + exercise.getName().fullName + " ");
        sb.append(PREFIX_PHONE + exercise.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + exercise.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + exercise.getAddress().value + " ");
        exercise.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExerciseDescriptor}'s details.
     */
    public static String getEditExerciseDescriptorDetails(EditCommand.EditExerciseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
