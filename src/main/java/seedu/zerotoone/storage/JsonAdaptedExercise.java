package seedu.zerotoone.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.zerotoone.commons.exceptions.IllegalValueException;
import seedu.zerotoone.model.exercise.Address;
import seedu.zerotoone.model.exercise.Email;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.Name;
import seedu.zerotoone.model.exercise.Phone;
import seedu.zerotoone.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exercise's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given exercise details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("zerotoone") String address,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exercise.
     */
    public Exercise toModelType() throws IllegalValueException {
        final List<Tag> exerciseTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            exerciseTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(exerciseTags);
        return new Exercise(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }

}
