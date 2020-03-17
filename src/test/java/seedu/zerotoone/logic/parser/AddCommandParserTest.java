package seedu.zerotoone.logic.parser;

import static seedu.zerotoone.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zerotoone.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.zerotoone.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.zerotoone.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.zerotoone.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.zerotoone.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.zerotoone.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.zerotoone.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.zerotoone.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.zerotoone.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.zerotoone.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.zerotoone.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.zerotoone.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.zerotoone.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.zerotoone.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.zerotoone.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.zerotoone.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.zerotoone.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.zerotoone.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.zerotoone.testutil.TypicalExercises.AMY;
import static seedu.zerotoone.testutil.TypicalExercises.BOB;

import org.junit.jupiter.api.Test;

import seedu.zerotoone.logic.commands.AddCommand;
import seedu.zerotoone.model.exercise.Address;
import seedu.zerotoone.model.exercise.Email;
import seedu.zerotoone.model.exercise.Exercise;
import seedu.zerotoone.model.exercise.Name;
import seedu.zerotoone.model.exercise.Phone;
import seedu.zerotoone.model.tag.Tag;
import seedu.zerotoone.testutil.ExerciseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple addresses - last zerotoone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple tags - all accepted
        Exercise expectedExerciseMultipleTags = new ExerciseBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedExerciseMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Exercise expectedExercise = new ExerciseBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedExercise));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing zerotoone prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid zerotoone
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
