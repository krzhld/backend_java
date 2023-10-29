package edu.hw3;

import edu.hw3.ContactProblem.Contact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static edu.hw3.ContactProblem.ContactList.parseContacts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class ContactListTest {
    private static Arguments[] parseContactsParamsAsc() {
        return new Arguments[] {
            Arguments.of(new String[] {
                "John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"
            }, List.of(
                new Contact("Thomas", "Aquinas"),
                new Contact("Rene", "Descartes"),
                new Contact("David", "Hume"),
                new Contact("John", "Locke")
            )),
            Arguments.of(new String[] {
                "Alex Brown", "Billy Adams", "Daniel Armstrong", "Billy"
            }, List.of(
                new Contact("Billy", "Adams"),
                new Contact("Daniel", "Armstrong"),
                new Contact("Billy", null),
                new Contact("Alex", "Brown")
            )),
            Arguments.of(new String[] {
                "Paul Erdos", "Leonhard Euler", "Carl Gauss"
            }, List.of(
                new Contact("Paul", "Erdos"),
                new Contact("Leonhard", "Euler"),
                new Contact("Carl", "Gauss")
            )),
            Arguments.of(new String[0], List.of()),
            Arguments.of(null, List.of())
        };
    }

    private static Arguments[] parseContactsParamsDesc() {
        return new Arguments[] {
            Arguments.of(new String[] {
                "John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"
            }, List.of(
                new Contact("John", "Locke"),
                new Contact("David", "Hume"),
                new Contact("Rene", "Descartes"),
                new Contact("Thomas", "Aquinas")
            )),
            Arguments.of(new String[] {
                "Alex Brown", "Billy Adams", "Daniel Armstrong", "Billy"
            }, List.of(
                new Contact("Alex", "Brown"),
                new Contact("Billy", null),
                new Contact("Daniel", "Armstrong"),
                new Contact("Billy", "Adams")
            )),
            Arguments.of(new String[] {
                "Paul Erdos", "Leonhard Euler", "Carl Gauss"
            }, List.of(
                new Contact("Carl", "Gauss"),
                new Contact("Leonhard", "Euler"),
                new Contact("Paul", "Erdos")
            )),
            Arguments.of(new String[0], List.of()),
            Arguments.of(null, List.of())
        };
    }

    @ParameterizedTest
    @MethodSource("parseContactsParamsAsc") void testAscParsing(String[] input, List<Contact> expectedResult) {
        String order = "ASC";
        List<Contact> result = parseContacts(input, order);
        assertThat(result).containsExactlyElementsOf(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("parseContactsParamsDesc") void testDescParsing(String[] input, List<Contact> expectedResult) {
        String order = "DESC";
        List<Contact> result = parseContacts(input, order);
        assertThat(result).containsExactlyElementsOf(expectedResult);
    }

    @Test
    void testIncorrectOrder() {
        String order = "F";
        String[] input = new String[] {"Tolik"};
        assertThatIllegalArgumentException().isThrownBy(() -> parseContacts(input, order));
    }
}
